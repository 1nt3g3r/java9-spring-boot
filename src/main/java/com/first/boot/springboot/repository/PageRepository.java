package com.first.boot.springboot.repository;

import com.first.boot.springboot.domain.HtmlPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<HtmlPage, String> {
    @Query("from HtmlPage p where p.content is null")
    List<HtmlPage> getUnparsedPages();

    @Query("select count(url) from HtmlPage p where p.content is null")
    int getUnparsedPageCount();
}
