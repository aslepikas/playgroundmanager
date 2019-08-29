package com.some.playground.util;

import java.time.LocalTime;

public class TimeUtil {

    private static final int WORKING_HOURS_START = 7;
    private static final int WORKING_HROUS_END = 19;

    public static boolean isWorkingHours() {
        int hour = LocalTime.now().getHour();

        return hour >= WORKING_HOURS_START && hour < WORKING_HROUS_END;
    }

}
