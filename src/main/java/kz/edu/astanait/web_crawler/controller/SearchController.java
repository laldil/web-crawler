package kz.edu.astanait.web_crawler.controller;

import kz.edu.astanait.web_crawler.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final IndexService indexService;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String query) {
        return ResponseEntity.ok().body(indexService.findByContent(query));
    }
}
