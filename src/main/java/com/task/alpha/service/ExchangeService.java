package com.task.alpha.service;

import com.task.alpha.model.Currency;

public interface ExchangeService {

    /** Compare today's and yesterday's currency rate.
     *  Return true if today's rate is bigger than another. **/
    boolean compareDayRatesByCode(String code);
    double getTodayCurrencyRateByCode(String code);
    double getYesterdayCurrencyRateByCode(String code);
}
