package pl.wisniewski.jan.ExcelReader.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    public Policy(Long number, String type, String sum, String name, String surname, String object) {
        this.number = number;
        this.type = type;
        this.sum = sum;
        this.name = name;
        this.surname = surname;
        this.object = object;
    }
}
