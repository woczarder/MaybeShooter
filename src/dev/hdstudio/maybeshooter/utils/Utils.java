package dev.hdstudio.maybeshooter.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static int drawNumbersBetween(int x, int y) {
        return ThreadLocalRandom.current().nextInt(x, y + 1);
    }

    public static double roundOff(double a) {
        return Math.round(a * 100.0) / 100.0;
    }

}
