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
        return new Policy (
                policyDTO.getNumber(),
                policyDTO.getType(),
                policyDTO.getSum(),
                policyDTO.getName(),
                policyDTO.getSurname(),
                policyDTO.getObject(),
                policyDTO.getValid()
        );
    }

    public PolicyDTO toDTO(Policy policy) {
        if (Objects.isNull(policy)) {
            return new PolicyDTO();
        }
        return new PolicyDTO (
                policy.getNumber(),
                policy.getType(),
                policy.getSum(),
                policy.getName(),
                policy.getSurname(),
                policy.getObject(),
                policy.getValid()
        );
    }
}
