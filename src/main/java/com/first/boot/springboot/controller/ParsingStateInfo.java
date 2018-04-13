package com.first.boot.springboot.controller;

import com.first.boot.springboot.service.HtmlPageService;

public class ParsingStateInfo {
    private int totalPageCount;
    private int parsedPageCount;

    public ParsingStateInfo(HtmlPageService htmlPageService) {
        parsedPageCount = htmlPageService.getTotalPageCount() - htmlPageService.getUnparsedPageCount();
        totalPageCount = htmlPageService.getTotalPageCount();
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getParsedPageCount() {
        return parsedPageCount;
    }
}
