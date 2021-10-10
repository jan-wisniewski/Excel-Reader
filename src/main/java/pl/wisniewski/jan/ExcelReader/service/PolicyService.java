package pl.wisniewski.jan.ExcelReader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.mappers.PolicyMapper;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;
import pl.wisniewski.jan.ExcelReader.repositories.PolicyRepository;
import pl.wisniewski.jan.ExcelReader.validators.PolicyValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PolicyService {

    private final ExcelService excelService;
    private final PolicyMapper policyMapper;
    private final PolicyValidator policyValidator;

    @Autowired
    private PolicyRepository policyRepository;

    public PolicyService(ExcelService excelService, PolicyMapper policyMapper, PolicyValidator policyValidator) {
        this.excelService = excelService;
        this.policyMapper = policyMapper;
        this.policyValidator = policyValidator;
    }

    public List<PolicyDTO> findAll() {
        return policyRepository.findAll().stream().map(policyMapper::toDTO).collect(Collectors.toList());
    }

    public List<PolicyDTO> deleteAll() {
        policyRepository.deleteAll();
        return new ArrayList<>();
    }

    public List<PolicyDTO> readXlsxFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File object is null");
        }
        List<PolicyDTO> policiesDTO = new ArrayList<>();
        excelService.getAllRowsFromFile(file).stream().forEach(cell -> {
            if (cell.getRowNum() > 0) {
                Object policyNumber = excelService.getProperValueFromCell(cell.getCell(0));
                Long parsedNumber = null;
                if (policyNumber.getClass().equals(Long.class)) {
                    parsedNumber = (Long) policyNumber;
                }
                policiesDTO.add(
                        new PolicyDTO (
                                parsedNumber,
                                (String) excelService.getProperValueFromCell(cell.getCell(1)),
                                String.valueOf(excelService.getProperValueFromCell(cell.getCell(2))),
                                (String) excelService.getProperValueFromCell(cell.getCell(3)),
                                (String) excelService.getProperValueFromCell(cell.getCell(4)),
                                (String) excelService.getProperValueFromCell(cell.getCell(5)),
                                false,
                                ""
                        )
                );
            }
        });

        List<PolicyDTO> validatedPolicies = validatePolicies(policiesDTO);
        List<Policy> collect = validatedPolicies.stream().map(policyMapper::toEntity).collect(Collectors.toList());
        policyRepository.saveAll(collect);
        return policiesDTO;
    }

    private List<PolicyDTO> validatePolicies (List<PolicyDTO> policyDTOS) {
        return policyDTOS.stream().map(policyDTO -> {
            List<String> validationErrors = policyValidator.getValidationErrors(policyDTO);
            boolean valid = validationErrors.isEmpty();
            if (!valid) {
                policyDTO.setDescription(String.join(", ", validationErrors));
            }
            policyDTO.setValid(valid);
            return policyDTO;
        }).collect(Collectors.toList());
    }

}
