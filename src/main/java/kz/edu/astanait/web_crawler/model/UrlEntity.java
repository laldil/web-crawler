package kz.edu.astanait.web_crawler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id;

    private String url;

    private String domain;

    private Boolean processed;
}
