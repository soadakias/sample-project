package com.company.demo_app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SpecifierType {
    RESULT_X("X", "Draw"),
    RESULT_1("1", "Home Win"),
    RESULT_2("2", "Away Win");

    private String dbValue;
    private String description;

    @JsonCreator
    public static SpecifierType fromDbValue(String value) {
        for (SpecifierType type : SpecifierType.values()) {
            if (type.dbValue.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }


}
