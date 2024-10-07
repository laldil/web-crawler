package kz.edu.astanait.web_crawler.scheduler;

import kz.edu.astanait.web_crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlerScheduler {

    private final CrawlerService crawlerService;

    @Scheduled(cron = "0 0 * * * *")
    public void runCrawler() {
        log.info("Crawler started");
        crawlerService.crawl(100);
    }

}
