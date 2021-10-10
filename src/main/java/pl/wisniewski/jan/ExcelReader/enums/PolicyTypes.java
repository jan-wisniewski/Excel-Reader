package pl.wisniewski.jan.ExcelReader.enums;

public enum PolicyTypes {

    COMMUNICATION ("Komunikacyjna"), PROPERTY ("Majątkowa"), PERSONAL ("Osobowa");

    private String name;

    PolicyTypes() {
    }

    PolicyTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
