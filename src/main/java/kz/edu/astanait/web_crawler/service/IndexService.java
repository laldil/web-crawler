package kz.edu.astanait.web_crawler.service;

import kz.edu.astanait.web_crawler.dto.PageDto;
import kz.edu.astanait.web_crawler.model.elastic.PageIndex;

import java.util.List;

public interface IndexService {

    void indexPage(PageDto pageDto);

    List<PageIndex> findByContent(String content);
}
