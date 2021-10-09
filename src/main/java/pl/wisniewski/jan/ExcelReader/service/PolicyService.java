package pl.wisniewski.jan.ExcelReader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.mappers.PolicyMapper;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;
import pl.wisniewski.jan.ExcelReader.repositories.PolicyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PolicyService {

    private final ExcelService excelService;
    private final PolicyMapper policyMapper;

    @Autowired
    private PolicyRepository policyRepository;

    public PolicyService(ExcelService excelService, PolicyMapper policyMapper) {
        this.excelService = excelService;
        this.policyMapper = policyMapper;
    }

    public List<PolicyDTO> findAll() {
        return policyRepository.findAll().stream().map(policyMapper::toDTO).collect(Collectors.toList());
    }

    public List<PolicyDTO> readXlsxFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File object is null");
        }
        List<PolicyDTO> policiesDTO = new ArrayList<>();
        excelService.getAllRowsFromFile(file).stream().forEach(cell -> {
            if (cell.getRowNum() > 0) {
                policiesDTO.add(
                        new PolicyDTO (
                                (Long) excelService.getProperValueFromCell(cell.getCell(0)),
                                (String) excelService.getProperValueFromCell(cell.getCell(1)),
                                String.valueOf(excelService.getProperValueFromCell(cell.getCell(2))),
                                (String) excelService.getProperValueFromCell(cell.getCell(3)),
                                (String) excelService.getProperValueFromCell(cell.getCell(4)),
                                (String) excelService.getProperValueFromCell(cell.getCell(5))
                        )
                );
            }
        });

        List<Policy> collect = policiesDTO.stream().map(policyMapper::toEntity).collect(Collectors.toList());
        policyRepository.saveAll(collect);
        return policiesDTO;
    }

}
