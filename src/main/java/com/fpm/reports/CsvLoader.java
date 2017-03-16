package com.fpm.reports;

import com.fpm.domain.Trade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;
import java.util.stream.Stream;


/**
 * Created by Kirill on 3/15/2017.
 * loads trades from csv files
 */
public class CsvLoader implements TradeLoader {

    private final Reader reader;
    private final Function<String[], Trade> mapper;
    private final String separator = ",";
    private final boolean skipHeader = true;

    public CsvLoader(Reader reader, Function<String[], Trade> mapper) {
        this.reader = reader;
        this.mapper = mapper;
    }

    public Stream<Trade> load() throws IOException {
        BufferedReader in = new BufferedReader(reader);

        return in.lines()
                .skip(skipHeader?1:0)
                .map(line -> mapper.apply(line.split(separator)));

    }


}
