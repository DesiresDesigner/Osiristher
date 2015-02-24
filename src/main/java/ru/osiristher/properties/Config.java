package ru.osiristher.properties;

import ru.osiristher.tester.exceptions.ConfigException;

import java.io.*;
import java.util.Properties;

public class Config {
    static Properties prop = new Properties();
    static ConfigException e = null;

    static {
        try {
            InputStream stream = new FileInputStream("config.properties");
            prop.load(stream);
        } catch (IOException ex) {
            e = new ConfigException(System.getProperty("user.dir"));
        }
    }

    static public String getProp(String key) throws ConfigException {
        if (e == null) {
            return prop.getProperty(key);
        } else
            throw e;
    }
}