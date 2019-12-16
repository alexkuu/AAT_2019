package service;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class PropReader {

    private static final Logger logger = Logger.getLogger(PropReader.class);
    private ArrayList<String> content = new ArrayList<>();

    PropReader(String fileName) {
        try {
            URL res = PropReader.class.getClassLoader().getResource(fileName);
            logger.info("Reading file:" + res.getPath());
            String stringPath = URLDecoder.decode(res.toString(), "UTF-8").replace("file:", "");
            logger.info("Decoded: " + stringPath);
            readFile(stringPath);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            // read through the first two lines to get to the data
            line = br.readLine();
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.contains("=") && !line.startsWith("#")) {
                    content.add(line);
                }
            }
        } catch (Exception e) {
            logger.error("There was an issue parsing the file.\n" + e.getMessage());
        }
    }

    String readProperty(String propertyName) {
        logger.info("Looking for property [" + propertyName + "]");
        for (String line : content) {
            if (line.split("=")[0].trim().equals(propertyName)) {
                logger.info("Found value: [" + line.split("=")[1] + "]");
                return line.split("=")[1];
            }
        }
        logger.info("Value not found. Return null");
        return null;
    }
}
