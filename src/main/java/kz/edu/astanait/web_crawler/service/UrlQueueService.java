package kz.edu.astanait.web_crawler.service;

import java.util.Optional;

public interface UrlQueueService {

    void addUrl(String url);

    Optional<String> getNextUrl();

    void markProcessed(String url);
}
