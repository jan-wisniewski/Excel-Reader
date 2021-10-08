package pl.wisniewski.jan.ExcelReader.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
}
