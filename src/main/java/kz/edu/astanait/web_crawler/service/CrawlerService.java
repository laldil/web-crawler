package kz.edu.astanait.web_crawler.service;

public interface CrawlerService {

    void crawl(int maxPages);

    void crawl(String url, int maxPage);

}
