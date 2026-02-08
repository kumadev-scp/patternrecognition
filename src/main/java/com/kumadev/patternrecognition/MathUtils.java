package com.kumadev.patternrecognition;

public class MathUtils {

    public final static int LINE_MIN_POINT_NUMBER = 2;

    /**
     * Get the great common divisor for two integer
     *
     * @param a
     * @param b
     * @return
     */
    public static int greatCommonDivisor(int a, int b) {
        return b == 0 ? a : greatCommonDivisor(b, a % b);
    }
}
