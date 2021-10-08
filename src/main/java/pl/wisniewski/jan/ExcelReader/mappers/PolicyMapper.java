package pl.wisniewski.jan.ExcelReader.mappers;

import org.springframework.stereotype.Service;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;

import java.util.Objects;

@Service
public class PolicyMapper {

    public Policy toEntity(PolicyDTO policyDTO) {
        if (Objects.isNull(policyDTO)) {
            return new Policy();
        }
        return Policy
                .builder()
                .id(policyDTO.getNumber())
                .number(policyDTO.getNumber())
                .name(policyDTO.getName())
                .type(policyDTO.getType())
                .surname(policyDTO.getSurname())
                .sum(policyDTO.getSum())
                .object(policyDTO.getObject())
                .build();
    }
}
