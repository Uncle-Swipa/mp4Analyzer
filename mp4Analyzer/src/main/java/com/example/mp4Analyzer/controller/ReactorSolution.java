package com.example.mp4Analyzer.controller;

import com.example.mp4Analyzer.model.BoxInfo;
import com.example.mp4Analyzer.util.Mp4AnalyserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ReactorSolution {
    @GetMapping("/analyze")
    public Mono<ResponseEntity<List<BoxInfo>>> analyseMp4(@RequestParam String url) {
        return Mono.fromCallable(() -> {
                    return new URL(url);
                }).subscribeOn(Schedulers.boundedElastic())
                .flatMap(mediaUrl -> {
                    HttpClient client = HttpClient.create();
                    try {
                        return client.get()
                                .uri(mediaUrl.toURI())
                                .responseContent()
                                .aggregate()
                                .asByteArray()
                                .map(bytes -> {
                                    List<BoxInfo> boxList = new ArrayList<>();
                                    int i = 0;
                                    while (i < bytes.length) {
                                        byte[] boxHeader = Arrays.copyOfRange(bytes, i, i + 8);
                                        long boxSize = Mp4AnalyserUtil.readBoxSize(boxHeader);
                                        String boxType = Mp4AnalyserUtil.readBoxType(boxHeader);
                                        boxList.add(new BoxInfo(boxType, boxSize));
                                        if (boxSize == 0) {
                                            break;
                                        }
                                        i += boxSize;
                                    }
                                    return new ResponseEntity<>(boxList, HttpStatus.OK);
                                });
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
