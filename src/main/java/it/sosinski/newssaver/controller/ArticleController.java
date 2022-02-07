package it.sosinski.newssaver.controller;

import it.sosinski.newssaver.service.ManageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/news")
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());

    private final ManageService manageService;

    public ArticleController(ManageService manageService) {
        this.manageService = manageService;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<String> saveToFile(@PathVariable String fileName) throws IOException {
        LOGGER.info("saveToFile(" + fileName + ")");

        //W celu customizacji, kategorię oraz kod kraju można przekazać jako parametry
        String category = "business";
        String countryCode = "pl";

        manageService.saveArticlesToFile(fileName, category, countryCode);

        LOGGER.info("saveToFile(...)");
        return new ResponseEntity<>("Articles saved to file.", HttpStatus.OK);
    }
}
