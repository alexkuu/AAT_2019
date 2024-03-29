package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class PropReader {

    private ArrayList<String> content = new ArrayList<>();

    PropReader(String fileName) {
        try {
            URL res = PropReader.class.getClassLoader().getResource(fileName);
            System.out.println("Reading file:" + res.getPath());
//            String stringPath = URLDecoder.decode(res.toString(), "UTF-8").replace("file:/", "")
//                    .replace("var/jenkins_home/workspace/PR-check_testBranch@script/", "")
//                    .replace("var/jenkins_home/workspace/PR-check_testBranch/", "");
//            System.out.println("Decoded: " + stringPath);

            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(res.getPath().replace("file:", ""))));
                String line, name, email;
                // read through the first two lines to get to the data
                line = br.readLine();
                line = br.readLine();
                while ((line = br.readLine()) != null) {
                    if (line.contains("=") && !line.startsWith("#")) {
                        content.add(line);
                    }
                }
                br.close();
            } catch (Exception e) {
                System.out.println("There was an issue parsing the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String readProperty(String propertyName) {
        System.out.println("Looking for property [" + propertyName + "]");
        for (String line : content) {
            if (line.split("=")[0].trim().equals(propertyName)) {
                System.out.println("Found value: [" + line.split("=")[1] + "]");
                return line.split("=")[1];
            }
        }
        System.out.println("Value not found. Return null");
        return null;
    }
}
