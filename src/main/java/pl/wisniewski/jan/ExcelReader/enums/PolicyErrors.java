package pl.wisniewski.jan.ExcelReader.enums;

public enum PolicyErrors {
    EMPTY_COLUMN("Nie wszystkie kolumny zostały uzupełnione"),
    INCORRECT_SUM("Niepoprawna wartość ubezpieczenia"),
    INCORRECT_ITEM("Niepoprawny przedmiot ubezpieczenia"),
    INCORRECT_TYPE("Niepoprawny typ ubezpiecznia");

    private String message;

    PolicyErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
