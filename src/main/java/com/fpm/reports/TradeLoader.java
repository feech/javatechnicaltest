package com.fpm.reports;

import com.fpm.domain.Trade;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Created by Kirill on 3/15/2017.
 */
public interface TradeLoader {
    Stream<Trade> load() throws IOException;
}
