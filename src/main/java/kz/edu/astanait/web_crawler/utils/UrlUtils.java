package kz.edu.astanait.web_crawler.utils;

import java.net.URI;

public class UrlUtils {

    /**
     * Extracts the domain name from the given URL string.
     * If the URL does not start with "http://" or "https://", the method assumes "http://".
     * The method parses the URL, retrieves the host, and removes the "www." prefix if present.
     *
     * @param url The URL string from which to extract the domain.
     * @return The domain name without the "www." prefix.
     * @throws IllegalArgumentException if the URL is invalid or the host cannot be determined.
     */
    public static String extractDomain(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        var uri = URI.create(url);
        var host = uri.getHost();

        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Invalid URL: Host is null");
        }

        return host.startsWith("www.") ? host.substring(4) : host;
    }
}
