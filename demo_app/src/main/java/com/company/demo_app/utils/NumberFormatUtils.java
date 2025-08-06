package com.company.demo_app.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@UtilityClass
@Slf4j
public class NumberFormatUtils {

    public static BigInteger createBigIntegerFromString(String number) {
        try {
            return new BigInteger(number);
        } catch(NumberFormatException nfe) {
            throw new NumberFormatException("Cannot parse number: " + number);
        }
    }

}
