package com.task.alpha.service.impl;

import com.task.alpha.clients.GifFeignClient;
import com.task.alpha.model.Gif;
import com.task.alpha.model.GifWrapper;
import com.task.alpha.service.GifService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GifServiceImplTest {

    Gif gif = new Gif();
    GifWrapper wrapper = new GifWrapper();

    @MockBean
    GifFeignClient feignClient;

    @Autowired
    GifServiceImpl service;

    @BeforeEach
    void init() {
        wrapper.getData().add(gif);
        wrapper.getData().add(gif);
        wrapper.getData().add(gif);
    }

    @Test
    void getBrokeGif() {
        when(feignClient.getBrokeGif()).thenReturn(wrapper);
        Gif gif = service.getBrokeGif();
        assertEquals(gif, wrapper.getData().get(0));
    }

    @Test
    void getRichGif() {
        when(feignClient.getRichGif()).thenReturn(wrapper);
        Gif gif = service.getRichGif();
        assertEquals(gif, wrapper.getData().get(0));
    }
}