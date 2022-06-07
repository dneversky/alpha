package com.task.alpha.clients;

import com.task.alpha.model.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(url = "${openexchangerates.url}", name = "USER-EXCHANGE")
public interface ExchangeFeignClient {

    @GetMapping("/latest.json?app_id=${openexchangerates.appId}")
    Currency getLatest();

    @GetMapping
    Currency getYesterday(URI baseUrl);
}
