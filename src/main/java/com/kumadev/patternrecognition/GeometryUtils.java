package com.kumadev.patternrecognition;

public class GeometryUtils {

    public static int LINE_MIN_POINT_NUMBER = 2;

    public static int greatCommonDivisor(int a, int b) {
        return b == 0 ? a : greatCommonDivisor(b, a % b);
    }
}
