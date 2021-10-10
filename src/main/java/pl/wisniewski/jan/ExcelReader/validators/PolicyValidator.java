package pl.wisniewski.jan.ExcelReader.validators;

import org.springframework.stereotype.Service;
import pl.wisniewski.jan.ExcelReader.enums.PolicyErrors;
import pl.wisniewski.jan.ExcelReader.enums.PolicyTypes;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PolicyValidator {
    public List<String> getValidationErrors(PolicyDTO policyDTO) {
        if (Objects.isNull(policyDTO)) {
            throw new IllegalArgumentException("Policy DTO objects is null");
        }
        List<String> errors = new ArrayList<>();

        if (!isEveryColumnNotNull(policyDTO)) {
            errors.add(PolicyErrors.EMPTY_COLUMN.getMessage());
        }
        if (!isCorrectPolicySum(policyDTO.getSum())) {
            errors.add(PolicyErrors.INCORRECT_SUM.getMessage());
        }
        if (!isCorrectInsuredItem(policyDTO.getObject())) {
            errors.add(PolicyErrors.INCORRECT_ITEM.getMessage());
        }
        if (!isCorrectPolicyType(policyDTO.getType())) {
            errors.add(PolicyErrors.INCORRECT_TYPE.getMessage());
        }
        return errors;
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

    private boolean isCorrectPolicySum(String policySum) {
        if (Objects.isNull(policySum) || !policySum.matches("\\d+")) {
            return false;
        }
        return BigDecimal.valueOf(Double.parseDouble(policySum)).compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isCorrectInsuredItem(String item) {
        if (Objects.isNull(item)) {
            return false;
        }
        return item.matches("[a-zA-Z0-9][a-zA-Z0-9 ]*");
    }

    private boolean isCorrectPolicyType(String type) {
        return Arrays.stream(PolicyTypes.values()).map(PolicyTypes::getName).anyMatch(e -> e.equalsIgnoreCase(type));
    }

}
