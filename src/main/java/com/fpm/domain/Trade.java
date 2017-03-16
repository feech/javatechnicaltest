package com.fpm.domain;

import java.time.LocalDate;

/**
 * Created by Kirill on 3/15/2017.
 */
final public class Trade {
    public final String entity;
    public final LocalDate instructionDate;
    public final LocalDate settlementDate;
    public final BuySellFlag flag;
    public final double agreedFx;
    public final double units;
    public final double pricePerUnit;
    public final String currency;

    public Trade(String entity, LocalDate instructionDate, LocalDate settlementDate, BuySellFlag flag, double agreedFx, double units, double pricePerUnit, String currency) {
        this.entity = entity;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.flag = flag;
        this.agreedFx = agreedFx;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.currency = currency;
    }

}
