package dev.j3rzy.aott.utils;

public class MathUtils {
    /**
     *
     * @param roundTo round to
     * @param input number to round
     * @return rounded number
     */
    public static double roundDownToMultipleOf(double roundTo, double input) {
        return input - (input % roundTo);
    }
}
