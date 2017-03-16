package com.fpm.domain;

/**
 * Created by Kirill on 3/15/2017.
 */
final public class TradeAmount {

    public double apply(Trade trade) {
        return trade.agreedFx * trade.units * trade.pricePerUnit;
    }
}
