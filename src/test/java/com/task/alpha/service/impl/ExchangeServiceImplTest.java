package com.task.alpha.service.impl;

import com.task.alpha.clients.ExchangeFeignClient;
import com.task.alpha.clients.GifFeignClient;
import com.task.alpha.model.Currency;
import com.task.alpha.model.GifWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExchangeServiceImplTest {

    Currency currency;

    @MockBean
    ExchangeFeignClient feignClient;

    @Autowired
    ExchangeServiceImpl service;

    @BeforeEach
    void init() {
        currency = new Currency();
        currency.getRates().put("RUB", 1.0);
    }

    @Test
    void compareDayRatesByCode() {
        when(feignClient.getYesterday(any())).thenReturn(currency);
        double rate = service.getYesterdayCurrencyRateByCode("RUB");

        currency.getRates().put("RUB", 1.22);

        when(feignClient.getLatest()).thenReturn(currency);
        double rate1 = service.getTodayCurrencyRateByCode("RUB");

        assertTrue(rate < rate1);
    }

    @Test
    void getTodayCurrencyRateByCode() {
        when(feignClient.getLatest()).thenReturn(currency);
        double rate = service.getTodayCurrencyRateByCode("RUB");
        assertEquals(1.0, rate);
    }

    @Test
    void getYesterdayCurrencyRateByCode() {
        when(feignClient.getYesterday(any())).thenReturn(currency);
        double rate = service.getYesterdayCurrencyRateByCode("RUB");
        assertEquals(1.0, rate);
    }
}