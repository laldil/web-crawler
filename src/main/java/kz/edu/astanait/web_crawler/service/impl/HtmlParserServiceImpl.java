package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.exceptions.NotFoundException;
import kz.edu.astanait.web_crawler.service.HtmlParserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HtmlParserServiceImpl implements HtmlParserService {

    @Override
    public Document fetchPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new NotFoundException("Document not found. Url: %s".formatted(url));
        }
    }

    @Override
    public String extractTitle(Document document) {
        return document.title();
    }

    @Override
    public String extractContent(Document document) {
        return document.body().text();
    }

    @Override
    public List<String> extractLinks(Document document) {
        return document.select("a[href]")
                .stream()
                .map(link -> link.attr("abs:href"))
                .collect(Collectors.toList());
    }
}
