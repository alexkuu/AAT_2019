package service;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PropReader {

    private String content;

    PropReader(String fileName) {
        try {
            URL res = PropReader.class.getClassLoader().getResource(fileName);
            File file = Paths.get(res.toURI()).toFile();
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String readProperty(String propertyName) {
        for (String line : content.split("\r\n")) {
            if (line.split("=")[0].trim().equals(propertyName)) {
                return line.split("=")[1];
            }
        }
        return null;
    }
}
