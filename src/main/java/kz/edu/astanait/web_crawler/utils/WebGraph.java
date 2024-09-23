package kz.edu.astanait.web_crawler.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WebGraph {

    private final Map<String, Set<String>> graph = new HashMap<>();

    public void addPage(String pageUrl) {
        graph.putIfAbsent(pageUrl, new HashSet<>());
    }

    public void addLink(String fromPage, String toPage) {
        graph.get(fromPage).add(toPage);
    }

    public Boolean hasPage(String pageUrl) {
        return graph.containsKey(pageUrl);
    }

    public Set<String> getLinks(String pageUrl) {
        return graph.getOrDefault(pageUrl, Collections.emptySet());
    }

}
