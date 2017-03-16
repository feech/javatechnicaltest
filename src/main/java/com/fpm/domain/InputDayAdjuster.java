package com.fpm.domain;

import static java.util.Arrays.asList;

/**
 * Created by Kirill on 3/16/2017.
 */
public class InputDayAdjuster implements CommonDayAdjuster {
    private DayAdjuster dayAdjuster = new DayAdjuster();
    private OilDayAdjuster oilDayAdjuster = new OilDayAdjuster();


    @Override
    public Trade adjust(Trade trade) {
        if (asList("AED", "SAR").contains(trade.currency)) {
            return oilDayAdjuster.adjust(trade);
        }
        return dayAdjuster.adjust(trade);
    }
}
