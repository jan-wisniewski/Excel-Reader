package pl.wisniewski.jan.ExcelReader.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "policies")
@Entity
public class Policy {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long number;

    @Column
    private String type;

    @Column
    private String sum;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String object;

    @Column
    private Boolean valid;

    @Column
    private String description;

    public Policy(Long id, Long number, String type, String sum, String name, String surname, String object, Boolean valid, String description) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.sum = sum;
        this.name = name;
        this.surname = surname;
        this.object = object;
        this.valid = valid;
        this.description = description;
    }

    public Policy() {
    }

    public Policy(Long number, String type, String sum, String name, String surname, String object, Boolean valid, String description) {
        this.number = number;
        this.type = type;
        this.sum = sum;
        this.name = name;
        this.surname = surname;
        this.object = object;
        this.valid = valid;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(id, policy.id) && Objects.equals(number, policy.number) && Objects.equals(type, policy.type) && Objects.equals(sum, policy.sum) && Objects.equals(name, policy.name) && Objects.equals(surname, policy.surname) && Objects.equals(object, policy.object) && Objects.equals(valid, policy.valid) && Objects.equals(description, policy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, sum, name, surname, object, valid, description);
    }
}
