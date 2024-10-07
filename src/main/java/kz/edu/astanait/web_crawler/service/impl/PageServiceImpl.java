package kz.edu.astanait.web_crawler.service.impl;

import kz.edu.astanait.web_crawler.dto.PageDto;
import kz.edu.astanait.web_crawler.model.PageEntity;
import kz.edu.astanait.web_crawler.repository.PageRepository;
import kz.edu.astanait.web_crawler.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    @Override
    public void savePage(PageDto pageDto) {
        var entity = new PageEntity();

        entity.setTitle(pageDto.getTitle());
        entity.setUrl(pageDto.getUrl());
        entity.setContent(pageDto.getContent());
        entity.setLinks(pageDto.getLinks());
        entity.setCreatedAt(new Date());

        pageRepository.save(entity);
    }

    @Override
    public PageEntity getLastPage() {
        return pageRepository.findFirstByOrderByCreatedAtDesc();
    }
}
