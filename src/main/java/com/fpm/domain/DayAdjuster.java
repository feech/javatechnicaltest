package com.fpm.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Kirill on 3/15/2017.
 */
public class DayAdjuster implements CommonDayAdjuster {

    @Override
    public Trade adjust(Trade trade) {
        if (isWeekend(trade.settlementDate)) {
            return TradeBuilder.build(trade, nextWorking(trade.settlementDate));
        }
        return trade;
    }

    protected boolean isWeekend(LocalDate date) {
        DayOfWeek i = date.getDayOfWeek();
        return i == DayOfWeek.SUNDAY || i == DayOfWeek.SATURDAY;
    }

    protected LocalDate nextWorking(LocalDate date) {
        if (!isWeekend(date)) {
            return date;
        }
        TemporalAdjuster next = TemporalAdjusters.next(DayOfWeek.MONDAY);
        return date.with(next);
    }

}
