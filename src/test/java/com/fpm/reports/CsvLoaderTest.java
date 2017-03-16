package com.fpm.reports;

import com.fpm.domain.BuySellFlag;
import com.fpm.domain.Trade;
import com.fpm.domain.TradeBuilder;
import com.fpm.exceptions.DataQualityException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 3/15/2017.
 */
public class CsvLoaderTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    @Test
    public void load() throws IOException {
        try (InputStream resourceAsStream = CsvLoaderTest.class.getResourceAsStream("/input.csv")) {

            Assert.assertNotNull(resourceAsStream);

            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);


            CsvLoader csvLoader = new CsvLoader(inputStreamReader,
                    strings -> {
                        try {
                            return new TradeBuilder()
                                .setEntity(strings[0])
                                .setFlag(BuySellFlagLoader.convert(strings[1]))
                                    .setAgreedFx(Double.parseDouble(strings[2]))
                                    .setCurrency(strings[3])
                                    .setInstructionDate(LocalDate.parse(strings[4], formatter))
                                    .setSettlementDate(LocalDate.parse(strings[5], formatter))
                                    .setUnits(Double.parseDouble(strings[6]))
                                    .setPricePerUnit(Double.parseDouble(strings[7]))
                                    .build();
                        } catch (DataQualityException e) {
                            throw new RuntimeException(e);
                        }

                    });

            List<Trade> collect = csvLoader.load().collect(Collectors.toList());

            Assert.assertEquals(2, collect.size());
            Assert.assertEquals(BuySellFlag.BUY_FLAG, collect.get(0).flag);
        }

    }

}