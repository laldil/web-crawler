package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.dto.PageDto;
import kz.edu.astanait.web_crawler.model.PageEntity;
import kz.edu.astanait.web_crawler.service.CrawlerService;
import kz.edu.astanait.web_crawler.service.HtmlParserService;
import kz.edu.astanait.web_crawler.service.IndexService;
import kz.edu.astanait.web_crawler.service.PageService;
import kz.edu.astanait.web_crawler.utils.WebGraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class CrawlerServiceImpl implements CrawlerService {

    private final HtmlParserService htmlParserService;
    private final PageService pageService;
    private final IndexService indexService;

    @Async
    public void crawl(int maxPages) {
        var page = pageService.getLastPage();
        crawl(page.getUrl(), maxPages);
    }

    @Async
    public void crawl(String startUrl, int maxPages) {
        var graph = new WebGraph();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startUrl);
        visited.add(startUrl);
        graph.addPage(startUrl);

        while (!queue.isEmpty() && maxPages > 0) {
            var currentUrl = queue.poll();

            try {
                log.info("VISIT: {}", currentUrl);
                var document = htmlParserService.fetchPage(currentUrl);
                var title = htmlParserService.extractTitle(document);
                var content = htmlParserService.extractContent(document);
                var links = htmlParserService.extractLinks(document);

                log.info("LINKS: {}", links);

                var pageToSave = PageDto.builder()
                        .url(currentUrl)
                        .title(title)
                        .content(content)
                        .links(links)
                        .build();
                pageService.savePage(pageToSave);
                indexService.indexPage(pageToSave);

                for (var link : links) {
                    if (!visited.contains(link)) {
                        visited.add(link);
                        queue.add(link);
                        graph.addPage(link);
                        graph.addLink(currentUrl, link);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                maxPages--;
            }
        }
    }
}
