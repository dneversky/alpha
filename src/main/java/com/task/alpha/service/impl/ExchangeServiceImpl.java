package com.task.alpha.service.impl;

import com.task.alpha.clients.ExchangeFeignClient;
import com.task.alpha.model.Currency;
import com.task.alpha.model.Gif;
import com.task.alpha.service.ExchangeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.LocalDate;

@Component
public class ExchangeServiceImpl implements ExchangeService {

    @Value("${openexchangerates.appId}")
    private String appId;

    @Value("${openexchangerates.url}")
    private String url;

    private final ExchangeFeignClient exchangeFeignClient;
    private final GifServiceImpl gifService;

    public ExchangeServiceImpl(ExchangeFeignClient exchangeFeignClient, GifServiceImpl gifService) {
        this.exchangeFeignClient = exchangeFeignClient;
        this.gifService = gifService;
    }

    public String compareRatesAndGetGifUrlByCode(String code) {
        Gif gif;
        if(compareDayRatesByCode(code)) {
            gif = gifService.getRichGif();
        } else {
            gif = gifService.getBrokeGif();
        }
        return gif.getEmbed_url();
    }

    @Override
    public boolean compareDayRatesByCode(String code) {
        return getTodayCurrencyRateByCode(code) > getYesterdayCurrencyRateByCode(code);
    }

    @Override
    public double getTodayCurrencyRateByCode(String code) {
        Currency currency = exchangeFeignClient.getLatest();
        return currency.getRates().get(code);
    }

    @Override
    public double getYesterdayCurrencyRateByCode(String code) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        URI baseUrl = URI.create(url + "/historical/" + yesterday + ".json?app_id=" + appId);
        Currency currency = exchangeFeignClient.getYesterday(baseUrl);
        return currency.getRates().get(code);
    }
}
