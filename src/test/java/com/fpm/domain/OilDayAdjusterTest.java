package com.fpm.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by Kirill on 3/16/2017.
 */
public class OilDayAdjusterTest {

    private Trade trade;
    @Before
    public void setUp(){
        trade = new TradeBuilder()
                .setEntity("aa")
                .setInstructionDate(LocalDate.of(2017, 3, 12))
                .setSettlementDate(LocalDate.of(2017, 3, 17))
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
        weekend = new OilDayAdjuster().isWeekend(LocalDate.of(2017, 3, 17));
        Assert.assertTrue(weekend);
        weekend = new OilDayAdjuster().isWeekend(LocalDate.of(2017, 3, 18));
        Assert.assertTrue(weekend);
        weekend = new OilDayAdjuster().isWeekend(LocalDate.of(2017, 3, 19));
        Assert.assertFalse(weekend);
    }

    @Test
    public void nextWorking() throws Exception {
        Trade adjust = new OilDayAdjuster().adjust(trade);
        Assert.assertFalse(adjust.equals(trade));
        Assert.assertEquals(LocalDate.of(2017, 3, 19), adjust.settlementDate);
        Assert.assertFalse(new OilDayAdjuster().isWeekend(adjust.settlementDate));
    }

}