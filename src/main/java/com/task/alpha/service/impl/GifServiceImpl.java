package com.task.alpha.service.impl;

import com.task.alpha.clients.GifFeignClient;
import com.task.alpha.model.Gif;
import com.task.alpha.service.GifService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class GifServiceImpl implements GifService {

    private final GifFeignClient gifFeignClient;

    public GifServiceImpl(GifFeignClient gifFeignClient) {
        this.gifFeignClient = gifFeignClient;
    }

    @Override
    public Gif getBrokeGif() {
        List<Gif> gifs = gifFeignClient.getBrokeGif().getData();
        int randomIndex = new Random().nextInt(gifs.size());
        return gifs.get(randomIndex);
    }

    @Override
    public Gif getRichGif() {
        List<Gif> gifs = gifFeignClient.getRichGif().getData();
        int randomIndex = new Random().nextInt(gifs.size());
        return gifs.get(randomIndex);
    }
}
