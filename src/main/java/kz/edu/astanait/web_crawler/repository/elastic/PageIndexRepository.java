package kz.edu.astanait.web_crawler.repository.elastic;

import kz.edu.astanait.web_crawler.model.elastic.PageIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PageIndexRepository extends ElasticsearchRepository<PageIndex, String> {

    List<PageIndex> findByContentContaining(String content);

}
