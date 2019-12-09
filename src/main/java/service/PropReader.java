package service;

import com.sun.jndi.toolkit.url.Uri;

import java.io.Console;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PropReader {

    private String content;

    PropReader(String fileName) {
        try {
            URL res = PropReader.class.getClassLoader().getResource(fileName);
            System.out.println("Reading file:" + res.getPath());
            String stringPath = URLDecoder.decode(res.toString(), "UTF-8").replace("file:/", "")
                    .replace("var/jenkins_home/workspace/PR-check_testBranch@script/", "")
                    .replace("var/jenkins_home/workspace/PR-check_testBranch/", "");
            System.out.println("Decoded: " + stringPath);
            File file = Paths.get(stringPath).toFile();
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String readProperty(String propertyName) {
        for (String line : content.split("\r\n")) {
            System.out.println("Reading property debug: Searching ["+propertyName+"]. LINE: " + line);
            System.out.println("Current line property is: [" + line.split("=")[0].trim() + "]");
            if (line.split("=")[0].trim().equals(propertyName)) {
                return line.split("=")[1];
            }
        }
        return null;
    }
}
