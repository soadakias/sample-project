package com.company.demo_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SportType {
    FOOTBALL("FOOTBALL"),
    BASKETBALL("BASKETBALL"),
    VOLLEYBALL("VOLLEYBALL"),
    BASEBALL("BASEBALL");

    private String value;
}
