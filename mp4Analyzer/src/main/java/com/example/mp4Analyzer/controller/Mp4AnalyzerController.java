package com.example.mp4Analyzer.controller;

import com.example.mp4Analyzer.model.BoxInfo;
import com.example.mp4Analyzer.service.Mp4AnalyserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Mp4AnalyzerController {

    @GetMapping("/analyse")
    public ResponseEntity<List<BoxInfo>> analyseMp4(@RequestParam String url) throws IOException {
        List<BoxInfo> boxList = Mp4AnalyserService.analyseMp4(url);
        return new ResponseEntity<>(boxList, HttpStatus.OK);
    }

}

