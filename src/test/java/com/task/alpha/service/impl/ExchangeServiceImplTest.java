package com.task.alpha.service.impl;

import com.task.alpha.clients.ExchangeFeignClient;
import com.task.alpha.exception.IncorrectCodeException;
import com.task.alpha.model.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExchangeServiceImplTest {

    @MockBean
    ExchangeFeignClient feignClient;

    @Autowired
    ExchangeServiceImpl service;

    @Test
    void getTodayCurrencyRateByCode() {
        Currency currency = new Currency();
        currency.getRates().put("RUB", 74.80);
        String code = "RUB";

        when(feignClient.getLatest()).thenReturn(currency);
        double currencyRate = service.getTodayCurrencyRateByCode(code);

        assertEquals(currency.getRates().get(code), currencyRate);

        String incorrectCode = "CODE";

        assertThrows(IncorrectCodeException.class, () -> service.getTodayCurrencyRateByCode(incorrectCode));
    }

    @Test
    void getYesterdayCurrencyRateByCode() {
        Currency currency = new Currency();
        currency.getRates().put("RUB", 74.80);
        String code = "RUB";
        URI baseUrl = URI.create("https://openexchangerates.org/api" + "/historical/" + LocalDate.now().minusDays(1) + ".json?app_id=" + "79d35d7560cc4bf5a1b89c89599b7fbe");

        when(feignClient.getYesterday(baseUrl)).thenReturn(currency);
        double currencyRate = service.getYesterdayCurrencyRateByCode(code);

        verify(feignClient).getYesterday(baseUrl);
        assertEquals(currency.getRates().get(code), currencyRate);

        String incorrectCode = "CODE";

        assertThrows(IncorrectCodeException.class, () -> service.getYesterdayCurrencyRateByCode(incorrectCode));
    }
}