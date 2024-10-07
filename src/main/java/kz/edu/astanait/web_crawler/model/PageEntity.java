package kz.edu.astanait.web_crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "webpages")
public class PageEntity {
    @Id
    private String url;

    private String title;

    private String content;

    private List<String> links;

    private Date createdAt;
}
