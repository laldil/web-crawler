package kz.edu.astanait.web_crawler.repository;

import kz.edu.astanait.web_crawler.model.PageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<PageEntity, String> {

    PageEntity findFirstByOrderByCreatedAtDesc();
}
