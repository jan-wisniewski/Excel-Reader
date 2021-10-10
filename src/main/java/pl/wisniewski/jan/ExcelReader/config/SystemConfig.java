package pl.wisniewski.jan.ExcelReader.config;

import java.util.List;

public abstract class SystemConfig {
    private static final List<String> excelColumnsStructure = List.of("Nr polisy", "Typ polisy", "Suma ubezpieczenia", "Imię ubiezpieczonego", "Nazwisko ubezpieczonego", "Przedmiot ubezpieczonego");

    public static List<String> getExcelColumnsStructure() {
        return excelColumnsStructure;
    }
}
