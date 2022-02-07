package it.sosinski.newssaver.service;

import it.sosinski.newssaver.model.Article;
import it.sosinski.newssaver.model.Source;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@Service
class SaveService {

    private static final Logger LOGGER = Logger.getLogger(SaveService.class.getName());

    void saveArticlesToFile(String fileName, Source source) throws IOException {
        LOGGER.info("saveArticlesToFile(" + fileName + ", " + source + ")");

        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        List<Article> articles = source.getArticles();

        for (Article article : articles) {
            printWriter.println(article);
        }
        printWriter.close();
        fileWriter.close();

        LOGGER.info("saveArticlesToFile(...)");
    }
}
