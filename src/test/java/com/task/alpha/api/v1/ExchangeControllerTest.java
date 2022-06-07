package com.task.alpha.api.v1;

import com.task.alpha.service.impl.ExchangeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeController.class)
@AutoConfigureMockMvc
class ExchangeControllerTest {

    @MockBean
    ExchangeServiceImpl exchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getValue() throws Exception {
        String code = "RUB";
        when(exchangeService.compareRatesAndGetGifUrlByCode(code)).thenReturn("url");

        mockMvc.perform(get("/v1/exchange?code=" + code)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("<iframe src='url' height='100%' width='100%'></iframe>"));
    }
}