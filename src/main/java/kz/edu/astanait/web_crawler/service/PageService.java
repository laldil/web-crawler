package kz.edu.astanait.web_crawler.service;

import kz.edu.astanait.web_crawler.dto.PageDto;
import kz.edu.astanait.web_crawler.model.PageEntity;

public interface PageService {

    void savePage(PageDto pageDto);

    PageEntity getLastPage();
}
