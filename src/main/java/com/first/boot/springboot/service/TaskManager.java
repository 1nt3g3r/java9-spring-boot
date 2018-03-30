package com.first.boot.springboot.service;

import com.first.boot.springboot.domain.HtmlPage;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {
    @Autowired
    private HtmlLoadService htmlLoadService;

    @Autowired
    private HtmlPageService pageService;

    @Scheduled(fixedDelay = 500)
    public void run() {
        if (pageService.getTotalPageCount() == 0) {
            pageService.addSeed("https://habrahabr.ru");
        }

        HtmlPage unparsed = pageService.getUnparsedPage();

        if (unparsed != null) {
            Document doc = htmlLoadService.parse(unparsed.getUrl());

            if (doc != null) {
                pageService.savePageFromDocument(doc);
            }
        } else {
            System.out.println("End of parsing");
        }

        int totalPageCount = pageService.getTotalPageCount();
        int unparsedPageCount = pageService.getUnparsedPageCount();
        int percent = 100 * unparsedPageCount / totalPageCount;
        System.out.println("Total, unparsed: " + pageService.getTotalPageCount() + ", " + pageService.getUnparsedPageCount() + ", " + percent + "%");
//        System.out.println("URL Count: " + pageService.getTotalPageCount());
    }
}
