package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.dto.PageDto;
import kz.edu.astanait.web_crawler.model.elastic.PageIndex;
import kz.edu.astanait.web_crawler.repository.elastic.PageIndexRepository;
import kz.edu.astanait.web_crawler.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexServiceImpl implements IndexService {

    private final PageIndexRepository pageIndexRepository;

    @Override
    public void indexPage(PageDto pageDto) {
        var pageIndex = new PageIndex();

        pageIndex.setUrl(pageDto.getUrl());
        pageIndex.setTitle(pageDto.getTitle());
        pageIndex.setContent(pageDto.getContent());

        pageIndexRepository.save(pageIndex);
    }

    @Override
    public List<PageIndex> findByContent(String content) {
        return pageIndexRepository.findByContentContaining(content);
    }
}
