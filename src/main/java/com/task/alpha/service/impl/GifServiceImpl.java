package com.task.alpha.service.impl;

import com.task.alpha.clients.GifFeignClient;
import com.task.alpha.model.Gif;
import com.task.alpha.service.GifService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GifServiceImpl implements GifService {

    @Value("${giphy.limit}")
    private int limit;

    private final GifFeignClient gifFeignClient;

    public GifServiceImpl(GifFeignClient gifFeignClient) {
        this.gifFeignClient = gifFeignClient;
    }

    @Override
    public Gif getBrokeGif() {
        int randomIndex = new Random().nextInt(limit);
        return gifFeignClient.getBrokeGif().getData().get(randomIndex);
    }

    @Override
    public Gif getRichGif() {
        int randomIndex = new Random().nextInt(limit);
        return gifFeignClient.getRichGif().getData().get(randomIndex);
    }
}
