package com.first.boot.springboot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HtmlLoadService {
    public Document parse(String url) {
        try {
            return  Jsoup.
                    connect(url).
                    userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0").
                    get();
        } catch (IOException e) {
            return null;
        }
    };
}
