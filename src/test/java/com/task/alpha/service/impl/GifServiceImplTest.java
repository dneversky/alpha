package com.task.alpha.service.impl;

import com.task.alpha.clients.GifFeignClient;
import com.task.alpha.model.Gif;
import com.task.alpha.model.GifWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class GifServiceImplTest {

    @MockBean
    GifFeignClient feignClient;

    @Autowired
    GifServiceImpl service;

    @Test
    void getBrokeGif() {
        GifWrapper wrapper = new GifWrapper();
        wrapper.getData().add(new Gif());

        when(feignClient.getBrokeGif()).thenReturn(wrapper);
        Gif gif = service.getBrokeGif();

        assertTrue(wrapper.getData().contains(gif));
    }

    @Test
    void getRichGif() {
        GifWrapper wrapper = new GifWrapper();
        wrapper.getData().add(new Gif());

        when(feignClient.getRichGif()).thenReturn(wrapper);
        Gif gif = service.getRichGif();

        assertTrue(wrapper.getData().contains(gif));
    }
}