package com.fpm.reports;

import com.fpm.domain.BuySellFlag;
import com.fpm.domain.Trade;
import com.fpm.reports.domain.ReportLine;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Kirill on 3/16/2017.
 */
public class ReportBuilderTest {

    @Test
    public void processTest() {
        List<Trade> trades = asList(
                new Trade("qw", LocalDate.of(2017, 3, 17), LocalDate.of(2017, 3, 19), BuySellFlag.BUY_FLAG, 10., 200., 12, "USD"),
                new Trade("qw", LocalDate.of(2017, 3, 19), LocalDate.of(2017, 3, 19), BuySellFlag.BUY_FLAG, 101., 200., 12, "USD"),
                new Trade("qw", LocalDate.of(2017, 3, 20), LocalDate.of(2017, 3, 20), BuySellFlag.SELL_FLAG, 1., 200., 12, "USD"),
                new Trade("qw", LocalDate.of(2017, 3, 21), LocalDate.of(2017, 3, 21), BuySellFlag.BUY_FLAG, 15., 200., 12, "EUR"),
                new Trade("qw", LocalDate.of(2017, 3, 22), LocalDate.of(2017, 3, 22), BuySellFlag.SELL_FLAG, 150., 200., 12, "USD"),
                new Trade("aa", LocalDate.of(2017, 3, 19), LocalDate.of(2017, 3, 22), BuySellFlag.SELL_FLAG, 120., 200., 12, "UER"),
                new Trade("aa", LocalDate.of(2017, 3, 20), LocalDate.of(2017, 3, 22), BuySellFlag.BUY_FLAG, 110., 200., 12, "USD")
                );

        List<ReportLine> generate = new ReportBuilder(trades.stream()).generate();

        Assert.assertEquals(4, generate.size());
        Assert.assertEquals("qw", generate.get(0).firstRanked);
        Assert.assertEquals("aa", generate.get(3).firstRanked);

    }

}