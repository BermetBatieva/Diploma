package com.example.tametable.enums;

public enum WeekType {
    NUMERAL("Числитель"),
    DENOMINATOR("Знаменатель");

    private final String name;

    WeekType(String name) {
        this.name = name;
    }

    public String getTypeRus() {
        return name;
    }
}
