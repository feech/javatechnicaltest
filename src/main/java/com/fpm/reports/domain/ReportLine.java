package com.fpm.reports.domain;

import java.time.LocalDate;

/**
 * Created by Kirill on 3/15/2017.
 */
public class ReportLine {
    public final LocalDate date;
    public final Double amountIncome;
    public final Double amountOutcome;
    public final String firstRanked;

    public ReportLine(LocalDate date, Double amountIncome, Double amountOutcome, String firstRaned) {
        this.date = date;
        this.amountIncome = amountIncome;
        this.amountOutcome = amountOutcome;
        this.firstRanked = firstRaned;
    }
}
