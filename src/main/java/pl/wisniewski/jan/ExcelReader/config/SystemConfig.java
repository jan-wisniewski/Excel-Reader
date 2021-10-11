package pl.wisniewski.jan.ExcelReader.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemConfig {

    private final List<String> excelColumnsStructure = List.of("Nr polisy", "Typ polisy", "Suma ubezpieczenia", "Imię ubezpieczonego", "Nazwisko ubezpieczonego", "Przedmiot ubezpieczonego");

    public List<String> getExcelColumnsStructure() {
        return excelColumnsStructure;
    }
}
