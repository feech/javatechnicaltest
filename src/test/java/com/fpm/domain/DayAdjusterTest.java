package com.fpm.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by Kirill on 3/16/2017.
 */
public class DayAdjusterTest {

    private Trade trade;
    @Before
    public void setUp(){
        trade = new TradeBuilder()
                .setEntity("aa")
                .setInstructionDate(LocalDate.of(2017, 3, 12))
                .setSettlementDate(LocalDate.of(2017, 3, 18))
                .setFlag(BuySellFlag.BUY_FLAG)
                .setAgreedFx(1.)
                .setUnits(2.)
                .setPricePerUnit(23.)
                .setCurrency("USD")
                .build();
    }
    @Test
    public void isWeekend() throws Exception {
        boolean weekend;
        weekend = new DayAdjuster().isWeekend(LocalDate.of(2017, 3, 17));
        Assert.assertFalse(weekend);
        weekend = new DayAdjuster().isWeekend(LocalDate.of(2017, 3, 18));
        Assert.assertTrue(weekend);
        weekend = new DayAdjuster().isWeekend(LocalDate.of(2017, 3, 19));
        Assert.assertTrue(weekend);
    }

    @Test
    public void nextWorking() throws Exception {
        Trade adjust = new DayAdjuster().adjust(trade);
        Assert.assertFalse(adjust.equals(trade));
        Assert.assertEquals(LocalDate.of(2017, 3, 20), adjust.settlementDate);
        Assert.assertFalse(new DayAdjuster().isWeekend(adjust.settlementDate));
    }

}