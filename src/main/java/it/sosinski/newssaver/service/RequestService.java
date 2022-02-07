package it.sosinski.newssaver.service;

import it.sosinski.newssaver.model.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
class RequestService {

    private static final Logger LOGGER = Logger.getLogger(RequestService.class.getName());

    private final RestTemplate restTemplate;

    @Value("${api_key}")
    private String apiKey;

    RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Source getNews(String category, String country) {
        LOGGER.info("getNews(" + category + ", " + country + ")");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Source> responseEntity = restTemplate.exchange("https://newsapi.org/v2/top-headlines?country="+ country + "&category=" + category,
                HttpMethod.GET, entity, Source.class);

        LOGGER.info("getNews(...)");
        return responseEntity.getBody();
    }

}
