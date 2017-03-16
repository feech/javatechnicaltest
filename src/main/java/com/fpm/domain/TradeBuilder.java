package com.fpm.domain;

import com.fpm.exceptions.InternalException;

import java.time.LocalDate;

/**
 * Created by Kirill on 3/15/2017.
 */
public class TradeBuilder {

    public String entity;
    public LocalDate instructionDate;
    public LocalDate settlementDate;
    public BuySellFlag flag;
    public Double agreedFx;
    public Double units;
    public Double pricePerUnit;
    public String currency;


    public TradeBuilder setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public TradeBuilder setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
        return this;
    }

    public TradeBuilder setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
        return this;
    }

    public TradeBuilder setFlag(BuySellFlag flag) {
        this.flag = flag;
        return this;
    }

    public TradeBuilder setAgreedFx(Double agreedFx) {
        this.agreedFx = agreedFx;
        return this;
    }

    public TradeBuilder setUnits(Double units) {
        this.units = units;
        return this;
    }

    public TradeBuilder setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public TradeBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Trade build() throws InternalException {
        if (entity == null) throw new InternalException("trade must have name");
        if (instructionDate == null) throw new InternalException("trade must have instruction date");
        if (settlementDate == null) throw new InternalException("trade must have settlement date");
        if (flag == null) throw new InternalException("trade must have flag");
        if (agreedFx == null) throw new InternalException("trade must have exchange rate ");
        if (agreedFx <= 0) throw new InternalException("exchange rate is positive");
        if (units == null) throw new InternalException("units should be assigned");
        if (units <= 0) throw new InternalException("units is positive");
        if (currency == null) throw new InternalException("currency must be set");

        return new Trade(entity, instructionDate, settlementDate, flag, agreedFx, units, pricePerUnit, currency);
    }

    public static Trade build(Trade trade, LocalDate settlementDate) {

        return new Trade(trade.entity,
                trade.instructionDate,
                settlementDate,
                trade.flag,
                trade.agreedFx,
                trade.units,
                trade.pricePerUnit,
                trade.currency);
    }
}
