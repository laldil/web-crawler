package kz.edu.astanait.web_crawler.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageDto {
    private String url;
    private String title;
    private String content;
    private List<String> links;
}
