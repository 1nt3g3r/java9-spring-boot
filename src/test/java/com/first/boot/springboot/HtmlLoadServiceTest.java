package com.first.boot.springboot;

import com.first.boot.springboot.service.HtmlLoadService;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HtmlLoadServiceTest {
    @Autowired
    private HtmlLoadService htmlLoadService;

    @Test
    public void testLoadNonExisting() {
        String nonExistingUrl = "https://no-exists-bra-bra.bah";
        Assert.assertNull(htmlLoadService.parse(nonExistingUrl));
    }

    @Test
    public void testLoadGoogle() {
        Document doc = htmlLoadService.parse("https://google.com");

        Assert.assertNotNull(doc);
        Assert.assertTrue(doc.text().toLowerCase().contains("google"));
    }
}
