package com.task.alpha.api.v1;

import com.task.alpha.service.impl.ExchangeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/exchange")
public class ExchangeController {

    private final ExchangeServiceImpl exchangeServiceImpl;

    public ExchangeController(ExchangeServiceImpl exchangeServiceImpl) {
        this.exchangeServiceImpl = exchangeServiceImpl;
    }

    @GetMapping
    public ResponseEntity<String> getValue(@RequestParam String code) {
        return ResponseEntity.ok()
                .body("<iframe src='" + exchangeServiceImpl
                        .compareRatesAndGetGifUrlByCode(code) + "' height='100%' width='100%'></iframe>");
    }
}