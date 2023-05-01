package com.example.mp4Analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Mp4AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mp4AnalyzerApplication.class, args);
	}

}
