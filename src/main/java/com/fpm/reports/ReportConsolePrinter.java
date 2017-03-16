package com.fpm.reports;

import com.fpm.reports.domain.ReportLine;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by Kirill on 3/15/2017.
 */
public class ReportConsolePrinter {

    final private List<ReportLine> report;

    public ReportConsolePrinter(List<ReportLine> report) {
        this.report = report;
    }


    public void print(PrintStream printStream) {
        report.forEach(x -> print(x, printStream));
    }

    private void print(ReportLine x, PrintStream printStream) {
        printStream.printf("%s ", x.date);
        printStream.printf("%.3f ", x.amountIncome);
        printStream.printf("%.3f ", x.amountOutcome);
        printStream.printf("%s", x.firstRanked);
        printStream.printf("%n");
    }
}
