package pl.wisniewski.jan.ExcelReader.model;

import java.util.Objects;

public class PolicyDTO {
    private Long number;
    private String type;
    private String sum;
    private String name;
    private String surname;
    private String object;
    private Boolean valid;

    public PolicyDTO(Long number, String type, String sum, String name, String surname, String object, Boolean valid) {
        this.number = number;
        this.type = type;
        this.sum = sum;
        this.name = name;
        this.surname = surname;
        this.object = object;
        this.valid = valid;
    }

    public PolicyDTO() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDTO policyDTO = (PolicyDTO) o;
        return Objects.equals(number, policyDTO.number) && Objects.equals(type, policyDTO.type) && Objects.equals(sum, policyDTO.sum) && Objects.equals(name, policyDTO.name) && Objects.equals(surname, policyDTO.surname) && Objects.equals(object, policyDTO.object) && Objects.equals(valid, policyDTO.valid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, sum, name, surname, object, valid);
    }
}
