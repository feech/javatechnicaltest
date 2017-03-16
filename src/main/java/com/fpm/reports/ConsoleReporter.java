package com.fpm.reports;

import com.fpm.domain.InputDayAdjuster;
import com.fpm.domain.Trade;
import com.fpm.domain.TradeBuilder;
import com.fpm.exceptions.DataQualityException;
import com.fpm.exceptions.InternalException;
import com.fpm.reports.domain.ReportLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Kirill on 3/15/2017.
 */

@Component
@ConfigurationProperties("mapper")
public class ConsoleReporter implements CommandLineRunner {

    @Value("${input-file-name}")
    private String inputFileName;

    @Value("${date-format:d-MMM-yyyy}")
    private String dateFormat;

    private Map<String, Integer> csvMapper;

    public Map<String, Integer> getCsvMapper() {
        return csvMapper;
    }

    public void setCsvMapper(Map<String, Integer> csvMapper) {
        this.csvMapper = csvMapper;
    }

    @Override
    public void run(String... strings) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        Function<String, LocalDate> str2Date = s -> LocalDate.parse(s, formatter);
        FileReader fileReader = new FileReader(inputFileName);
        InputDayAdjuster inputDayAdjuster = new InputDayAdjuster();

        TradeLoader tradeLoader = new CsvLoader(fileReader, (String[] strings1) -> {
            try {
                return new TradeBuilder()
                        .setEntity(strings1[csvMapper.get("entity")])
                        .setInstructionDate(str2Date.apply(strings1[csvMapper.get("instructiondate")]))
                        .setSettlementDate(str2Date.apply(strings1[csvMapper.get("settlementdate")]))
                        .setFlag(BuySellFlagLoader.valueOf(strings1[csvMapper.get("buysellfl")]))
                        .setAgreedFx(Double.parseDouble(strings1[csvMapper.get("agreedfx")]))
                        .setUnits(Double.parseDouble(strings1[csvMapper.get("units")]))
                        .setPricePerUnit(Double.parseDouble(strings1[csvMapper.get("priceperunit")]))
                        .setCurrency(strings1[csvMapper.get("currency")])
                        .build();
            } catch (DataQualityException e) {
                throw new InternalException(e);
            }
        });


        Stream<Trade> tradeStream = tradeLoader
                .load()
                .map(inputDayAdjuster::adjust);

        ReportBuilder reportBuilder = new ReportBuilder(tradeStream);

        List<ReportLine> report = reportBuilder.generate();

        ReportConsolePrinter reportConsolePrinter = new ReportConsolePrinter(report);

        reportConsolePrinter.print(System.out);


    }
}
