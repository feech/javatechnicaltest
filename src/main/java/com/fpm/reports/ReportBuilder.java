package com.fpm.reports;

import com.fpm.domain.BuySellFlag;
import com.fpm.domain.Trade;
import com.fpm.domain.TradeAmount;
import com.fpm.reports.domain.ReportLine;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Kirill on 3/15/2017.
 */
public class ReportBuilder {

    private final Stream<Trade> trades;
    private final Map<LocalDate, DayInfo> dayInfoMap = new HashMap<>();
    private final TradeAmount tradeAmount = new TradeAmount();

    public ReportBuilder(Stream<Trade> load) {

        this.trades = load;
    }


    private static class DayInfo {
        double income = 0;
        double outcome = 0;
        double fistRankedValue = 0;
        String firstRanked;

    }

    public List<ReportLine> generate() {
        trades.forEach(this::accum);

        return dayInfoMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(a -> new ReportLine(a.getKey(), a.getValue().income, a.getValue().outcome, a.getValue().firstRanked))
                .collect(Collectors.toList());

    }

    private void accum(Trade trade) {
        DayInfo dayInfo = dayInfoMap.get(trade.settlementDate);

        if (dayInfo == null) {
            dayInfo = new DayInfo();
            dayInfoMap.put(trade.settlementDate, dayInfo);
        }

        double entityAmount = tradeAmount.apply(trade);
        if (trade.flag == BuySellFlag.BUY_FLAG) {
            dayInfo.outcome += entityAmount;
            if (dayInfo.fistRankedValue < entityAmount) {
                dayInfo.fistRankedValue = entityAmount;
                dayInfo.firstRanked = trade.entity;
            }
        } else {
            dayInfo.income += entityAmount;
        }
    }
}
