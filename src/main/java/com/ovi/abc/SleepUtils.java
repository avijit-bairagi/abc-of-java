package com.ovi.abc;

public class SleepUtils {

    public static void sleep(long l) {

        try {
            Thread.sleep(1000 * l);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
