package com.first.boot.springboot;

import com.first.boot.springboot.service.HtmlLoadService;
import com.first.boot.springboot.service.HtmlPageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HtmlPageServiceTest {
    @Autowired
    private HtmlLoadService htmlLoadService;

    @Autowired
    private HtmlPageService htmlPageService;

    @Before
    public void before() {
        htmlPageService.clean();
    }

    @Test
    public void testParseTextmania() {
        htmlPageService.savePageFromDocument(
                htmlLoadService.parse("http://textmania.ru")
        );

        int pageCount = htmlPageService.getTotalPageCount();
        Assert.assertEquals(6, pageCount);
    }
}
