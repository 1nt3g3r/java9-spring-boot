package com.first.boot.springboot.service;

import com.first.boot.springboot.domain.HtmlPage;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {
    private boolean running = true;

    @Autowired
    private HtmlLoadService htmlLoadService;

    @Autowired
    private HtmlPageService pageService;

    @Scheduled(fixedDelay = 1000)
    public void run() {
        if (running) {
            parseNextPage();
        }
    }

    private void parseNextPage() {

        HtmlPage unparsed = pageService.getUnparsedPage();

        if (unparsed != null) {
            Document doc = htmlLoadService.parse(unparsed.getUrl());

            if (doc != null) {
                pageService.savePageFromDocument(doc);
            }
        } else {
            System.out.println("End of parsing");
        }

    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
