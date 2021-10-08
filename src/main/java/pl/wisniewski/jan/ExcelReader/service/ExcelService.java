package pl.wisniewski.jan.ExcelReader.service;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelService {

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
        return getTotalRowsFromSheetsList(allSheets);
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
