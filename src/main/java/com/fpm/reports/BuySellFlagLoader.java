package com.fpm.reports;

import com.fpm.domain.BuySellFlag;
import com.fpm.exceptions.DataQualityException;

/**
 * Created by Kirill on 3/16/2017.
 */
public class BuySellFlagLoader {
    static BuySellFlag convert(String val) throws DataQualityException {
        switch (val.toUpperCase()) {
            case "B":
                return BuySellFlag.BUY_FLAG;
            case "S":
                return BuySellFlag.SELL_FLAG;
        }
        throw new DataQualityException();
    }

    static BuySellFlag valueOf(String val) throws DataQualityException {
        return convert(val);
    }
}
