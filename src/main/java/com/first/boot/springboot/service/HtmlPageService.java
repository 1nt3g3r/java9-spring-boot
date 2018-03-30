package com.first.boot.springboot.service;

import com.first.boot.springboot.domain.HtmlPage;
import com.first.boot.springboot.repository.PageRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmlPageService {
    @Autowired
    private PageRepository pageRepository;

    public void savePageFromDocument(Document doc) {
        String url = doc.location();
        String content = doc.html();
        if (content == null) {
            content = "";
        }
        addPage(url, content);

        String baseDomain = getDomain(url);

        Elements elements = doc.select("a");
        for(Element e: elements) {
            String link = e.absUrl("href");
            if (getDomain(link).equals(baseDomain)) {
                addPage(link, null);
            }
        }
    }

    private String getDomain(String url) {
        String withoutProtocol = url.split("//")[1];
        return withoutProtocol.split("/")[0];
    }

    private void addPage(String url, String content) {
        url = cleanLink(url);
        if (pageRepository.existsById(url)) {
            HtmlPage existingPage = pageRepository.getOne(url);
            existingPage.setContent(content);
            pageRepository.save(existingPage);

            return;
        }

        HtmlPage page = new HtmlPage();
        page.setUrl(url);
        page.setContent(content);
        pageRepository.save(page);
    }

    private String cleanLink(String url) {
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }

    public void clean() {
        pageRepository.deleteAll();
    }

    public int getTotalPageCount() {
        return (int) pageRepository.count();
    }

    public HtmlPage getUnparsedPage() {
        List<HtmlPage> pages =  pageRepository.getUnparsedPages();
        if (pages.size() <= 0) {
            return null;
        }

        return pages.get(0);
    }

    public void addSeed(String url) {
        addPage(url, null);
    }

    public int getUnparsedPageCount() {
        return pageRepository.getUnparsedPageCount();
    }
}
