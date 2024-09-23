package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.exceptions.NotFoundException;
import kz.edu.astanait.web_crawler.model.UrlEntity;
import kz.edu.astanait.web_crawler.repository.UrlRepository;
import kz.edu.astanait.web_crawler.service.UrlQueueService;
import kz.edu.astanait.web_crawler.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UrlQueueServiceImpl implements UrlQueueService {

    private final UrlRepository urlRepository;

    public void addUrl(String url) {
        if (!urlRepository.existsByDomain(UrlUtils.extractDomain(url))) {
            var urlEntity = UrlEntity.builder()
                    .url(url)
                    .domain(UrlUtils.extractDomain(url))
                    .processed(false)
                    .build();
            urlRepository.save(urlEntity);
        }
    }

    public Optional<String> getNextUrl() {
        return urlRepository.findFirstByProcessedIsFalse().map(UrlEntity::getUrl);
    }

    public void markProcessed(String url) {
        var urlEntity = urlRepository.findByUrl(url)
                .orElseThrow(() -> new NotFoundException("Url not found"));
        urlEntity.setProcessed(true);
        urlRepository.save(urlEntity);
    }
}
