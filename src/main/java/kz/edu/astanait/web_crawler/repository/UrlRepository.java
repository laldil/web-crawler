package kz.edu.astanait.web_crawler.repository;

import kz.edu.astanait.web_crawler.model.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    Optional<UrlEntity> findFirstByProcessedIsFalse();

    Boolean existsByDomain(String domain);

    Optional<UrlEntity> findByUrl(String url);
}
