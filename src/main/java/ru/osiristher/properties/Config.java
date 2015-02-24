package ru.osiristher.properties;

import java.io.*;
import java.util.Properties;

public class Config {
    static Properties prop = new Properties();
    static IOException e = null;

    static {
        try {
            InputStream stream = new FileInputStream("config.properties");
            prop.load(stream);
        } catch (IOException ex) {
            e = new IOException("check config.properties file");
        }
    }

    static public String getProp(String key) throws IOException {
        if (e == null) {
            return prop.getProperty(key);
        } else
            throw e;
    }
}