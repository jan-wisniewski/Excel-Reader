package pl.wisniewski.jan.ExcelReader.validators;

import org.springframework.stereotype.Service;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;

import java.util.Objects;

@Service
public class PolicyValidator {
    public boolean isValid(PolicyDTO policyDTO) {
        return isEveryColumnNotNull(policyDTO) && !Objects.isNull(policyDTO);
    }

    private boolean isEveryColumnNotNull(PolicyDTO policyDTO) {
        boolean isvalid = Objects.nonNull(policyDTO)
                && (Objects.nonNull(policyDTO.getName()) && !policyDTO.getName().equals(""))
                && (Objects.nonNull(policyDTO.getNumber()) && policyDTO.getNumber() > 0)
                && (Objects.nonNull(policyDTO.getSum()) && !policyDTO.getSum().equals(""))
                && (Objects.nonNull(policyDTO.getType()) && !policyDTO.getType().equals(""))
                && (Objects.nonNull(policyDTO.getSurname()) && !policyDTO.getSurname().equals(""))
                && (Objects.nonNull(policyDTO.getObject()) && !policyDTO.getObject().equals(""));
        return isvalid;
    }

}
