package pl.wisniewski.jan.ExcelReader.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Policy {
    private Long id;
    private Long number;
    private String type;
    private String sum;
    private String name;
    private String surname;
    private String object;
}
