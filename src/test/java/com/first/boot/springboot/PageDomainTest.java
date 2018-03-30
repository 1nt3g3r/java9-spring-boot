package com.first.boot.springboot;

import com.first.boot.springboot.domain.HtmlPage;
import com.first.boot.springboot.repository.PageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageDomainTest {
    @Autowired
    private PageRepository pageRepository;

    @Test
    public void testSave() {
        HtmlPage page = randomPage();
        String url = page.getUrl();

        pageRepository.save(page);

        HtmlPage anotherPage = pageRepository.getOne(url);

        Assert.assertEquals(page.getUrl(), anotherPage.getUrl());
        Assert.assertEquals(page.getContent(), anotherPage.getContent());
    }

    private HtmlPage randomPage() {
        Random r = new Random();

        HtmlPage page = new HtmlPage();
        page.setUrl(Float.toString(r.nextFloat()));
        page.setContent(Float.toString(r.nextFloat()));

        return page;
    }
}
