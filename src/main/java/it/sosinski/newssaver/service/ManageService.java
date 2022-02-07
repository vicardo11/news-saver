package it.sosinski.newssaver.service;

import it.sosinski.newssaver.model.Source;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class ManageService {

    private static final Logger LOGGER = Logger.getLogger(ManageService.class.getName());

    private final RequestService requestService;
    private final SaveService saveService;

    public ManageService(RequestService requestService, SaveService saveService) {
        this.requestService = requestService;
        this.saveService = saveService;
    }

    public void saveArticlesToFile(String fileName, String category, String countryCode) throws IOException {
        LOGGER.info("saveArticlesToFile(" + fileName + ", " + category + ", " + countryCode + ")");

        Source source = requestService.getNews(category, countryCode);
        saveService.saveArticlesToFile(fileName, source);

        LOGGER.info("saveArticlesToFile(...)");
    }
}
