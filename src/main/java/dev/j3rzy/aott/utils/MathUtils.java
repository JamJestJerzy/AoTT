package dev.j3rzy.aott.utils;

public class MathUtils {
    public static double roundDownToMultipleOf(double roundTo, double input) {
        return input - (input % roundTo);
    }
}
