package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.service.CrawlerService;
import kz.edu.astanait.web_crawler.service.HtmlParserService;
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

    @Async
    @Override
    public void crawl(String url) {
        var graph = new WebGraph();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(url);
        visited.add(url);
        graph.addPage(url);

        while (!queue.isEmpty()) {
            var currentUrl = queue.poll();
            var document = htmlParserService.fetchPage(currentUrl);
            var links = htmlParserService.extractLinks(document);

            log.info("VISITED: {}. EXTRACTED LINKS: {}", currentUrl, links);
            log.info("VISITED content: {}", htmlParserService.extractContent(document));

            for (var link : links) {
                if (!visited.contains(link)) {
                    visited.add(link);
                    queue.add(link);
                    graph.addPage(link);
                    graph.addLink(currentUrl, link);
                }
            }
        }
    }
}
