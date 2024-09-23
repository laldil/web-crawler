package kz.edu.astanait.web_crawler.service;

import org.jsoup.nodes.Document;

import java.util.List;

public interface HtmlParserService {

    Document fetchPage(String url);

    String extractTitle(Document document);

    String extractContent(Document document);

    List<String> extractLinks(Document document);

}
