package com.first.boot.springboot.domain;

import javax.persistence.*;

@Entity
@Table(name = "HtmlPageTable")
public class HtmlPage {
    @Id
    private String url;

    @Column(length = 100000)
    @Lob
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        HtmlPage htmlPage = (HtmlPage) o;

        if (url != null ? !url.equals(htmlPage.url) : htmlPage.url != null) return false;
        return content != null ? content.equals(htmlPage.content) : htmlPage.content == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
