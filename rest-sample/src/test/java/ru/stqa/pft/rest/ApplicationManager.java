package ru.stqa.pft.rest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private Issue issue;

    ApplicationManager() {
        properties = new Properties();
    }

    void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    Issue issue() {
        if (issue == null) {
            issue = new Issue();
        }
        return issue;
    }


    String getProperty(String key) {
        return properties.getProperty(key);
    }
}
