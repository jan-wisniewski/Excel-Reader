package pl.wisniewski.jan.ExcelReader.service;

import com.google.gson.Gson;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.config.SystemConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExcelService {

    private final Gson gson = new Gson();

    public ExcelService(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    private final SystemConfig systemConfig;

    public Object getProperValueFromCell(XSSFCell xssfCell) {
        if (Objects.isNull(xssfCell)) {
            return "";
        }
        switch (xssfCell.getCellType()) {
            case STRING -> {
                return xssfCell.getStringCellValue();
            }
            case NUMERIC -> {
                return Double.valueOf(xssfCell.getNumericCellValue()).longValue();
            }
            case BOOLEAN -> {
                return String.valueOf(xssfCell.getBooleanCellValue());
            }
            case BLANK -> {
                return "";
            }
        }
        return "";
    }

    public List<XSSFRow> getAllRowsFromFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File object is null");
        }
        List<XSSFSheet> allSheets = getAllSheets(readXlSXFile(file));
        if (allSheets.size() > 0) {
           if (!verifyExcelStructure(allSheets.get(0))) {
               throw new IllegalArgumentException("Incorrect excel structure");
           }
        }
        return getTotalRowsFromSheetsList(allSheets);
    }

    private List<String> getNamesOfColumns(List<XSSFCell> xssfCells) {
        if (Objects.isNull(xssfCells)) {
            throw new IllegalArgumentException("xssfCells row obj is null");
        }
        return xssfCells.stream().filter(Objects::nonNull)
                .map(XSSFCell::getStringCellValue)
                .collect(Collectors.toList());
    }

    private boolean verifyExcelStructure(XSSFSheet firstPage) {
        if (Objects.isNull(firstPage) || Objects.isNull(firstPage.getRow(0))) {
            return false;
        }
        List<XSSFCell> cellsInFirstRow = new ArrayList<>();
        XSSFRow firstRow = firstPage.getRow(0);
        int numberOfCells = firstPage.getPhysicalNumberOfRows();
        for (int i = 0; i < numberOfCells; i++) {
            cellsInFirstRow.add(firstRow.getCell(i));
        }
        return systemConfig.getExcelColumnsStructure().containsAll(getNamesOfColumns(cellsInFirstRow));
    }

    public XSSFWorkbook readXlSXFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to read xlsx file");
        }
    }

    private List<XSSFSheet> getAllSheets(XSSFWorkbook xssfWorkbook) {
        if (Objects.isNull(xssfWorkbook)) {
            throw new IllegalArgumentException("XSSFWorkbook object is null");
        }
        List<XSSFSheet> xssfSheets = new ArrayList<>();
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            xssfSheets.add(xssfWorkbook.getSheetAt(i));
        }
        return xssfSheets;
    }

    private List<XSSFRow> getAllRowsFromCurrentSheet(XSSFSheet xssfSheet) {
        if (Objects.isNull(xssfSheet)) {
            throw new IllegalArgumentException("xssfSheet object is null or has incorrect size");
        }
        List<XSSFRow> allRowsFromSheet = new ArrayList<>();
        int allRows = xssfSheet.getPhysicalNumberOfRows();
        for (int i = 0; i < allRows; i++) {
            allRowsFromSheet.add(xssfSheet.getRow(i));
        }
        return allRowsFromSheet;
    }

    private List<XSSFRow> getTotalRowsFromSheetsList(List<XSSFSheet> xssfSheets) {
        if (Objects.isNull(xssfSheets) || xssfSheets.size() < 1) {
            throw new IllegalArgumentException("xssfSheets object is null or has incorrect size");
        }
        List<XSSFRow> xssfRows = new ArrayList<>();
        xssfSheets.stream().forEach(xssfSheet -> xssfRows.addAll(getAllRowsFromCurrentSheet(xssfSheet)));
        return xssfRows;
    }

}
