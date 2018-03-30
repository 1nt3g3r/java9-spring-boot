package com.first.boot.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class HtmlPage {
    @Id
    @Column
    private String url;

    @Column
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HtmlPage{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
