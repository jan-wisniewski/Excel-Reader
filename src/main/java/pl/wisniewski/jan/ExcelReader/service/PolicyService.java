package pl.wisniewski.jan.ExcelReader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PolicyService {

    private final ExcelService excelService;

    public PolicyService(ExcelService excelService) {
        this.excelService = excelService;
    }

    public List<PolicyDTO> findAll() {
        return List.of(new PolicyDTO());
    }

    public List<PolicyDTO> readXlsxFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File object is null");
        }
        List<PolicyDTO> policies = new ArrayList<>();
        excelService.getAllRowsFromFile(file).stream().forEach(cell -> {
            if (cell.getRowNum() > 0) {
                policies.add(
                        PolicyDTO
                                .builder()
                                .number((Long) excelService.getProperValueFromCell(cell.getCell(0)))
                                .type((String) excelService.getProperValueFromCell(cell.getCell(1)))
                                .sum(String.valueOf(excelService.getProperValueFromCell(cell.getCell(2))))
                                .name((String) excelService.getProperValueFromCell(cell.getCell(3)))
                                .surname((String) excelService.getProperValueFromCell(cell.getCell(4)))
                                .object((String) excelService.getProperValueFromCell(cell.getCell(5)))
                                .build()
                );
            }
        });
        log.debug("::::::");
        return policies;
    }

}
