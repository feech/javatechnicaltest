package com.fpm.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Kirill on 3/15/2017.
 */
public class OilDayAdjuster extends DayAdjuster {

    @Override
    protected boolean isWeekend(LocalDate date) {
        DayOfWeek i = date.getDayOfWeek();
        return i == DayOfWeek.FRIDAY || i == DayOfWeek.SATURDAY;
    }

    @Override
    protected LocalDate nextWorking(LocalDate date) {
        if (!isWeekend(date)) {
            return date;
        }
        TemporalAdjuster next = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        return date.with(next);
    }
}
