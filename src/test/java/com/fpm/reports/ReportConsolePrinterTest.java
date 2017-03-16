package com.fpm.reports;

import com.fpm.reports.domain.ReportLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Kirill on 3/16/2017.
 */
public class ReportConsolePrinterTest {
    @Test
    public void print() throws Exception {
        ReportLine reportLine = new ReportLine(LocalDate.of(2017, 3, 12), 101., 301., "foo");


        ReportConsolePrinter reportConsolePrinter = new ReportConsolePrinter(Arrays.asList(reportLine,reportLine));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        reportConsolePrinter.print(new PrintStream(byteArrayOutputStream));

        Assert.assertEquals("2017-03-12 101,000 301,000 foo" +
                "2017-03-12 101,000 301,000 foo", byteArrayOutputStream.toString("UTF-8").replace("\r\n",""));

    }

}