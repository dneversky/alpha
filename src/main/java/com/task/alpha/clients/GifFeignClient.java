package com.task.alpha.clients;

import com.task.alpha.model.GifWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${giphy.url}", name = "GIF-CLIENT")
public interface GifFeignClient {

    @GetMapping("?api_key=${giphy.apiKey}&q=rich&limit=${giphy.limit}")
    GifWrapper getRichGif();

    @GetMapping("?api_key=${giphy.apiKey}&q=broke&limit=${giphy.limit}")
    GifWrapper getBrokeGif();
}
