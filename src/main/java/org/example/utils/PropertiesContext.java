package org.example.utils;

import com.codeborne.selenide.Configuration;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.example.utils.MyLogger.getLogger;

/**
 * The primary purpose of this class is to allow access to the properties
 */
public class PropertiesContext {
    public static final String PROPERTIES_SUFFIX = ".properties";
    private static final String TEST = "test";
    private static final String APPLICATIONS_PROPERTIES = "application";
    private final Properties applicationMap = new Properties();
    private final Properties testMap = new Properties();
    private final Properties generalMap = new Properties();
    private static PropertiesContext instance;

    public static PropertiesContext getInstance() {
        if (instance == null) {
            instance = new PropertiesContext();
        }
        return instance;
    }

    private PropertiesContext() {
        init();
        Configuration.remote = getProperty("selenoid.remote.url");
        System.getProperties().stringPropertyNames().forEach(p -> setProperty(p, System.getProperty(p)));
    }

    /**
     * The method purpose is to get property by key
     *
     * @param key - a string that which represents a property name
     * @return property value
     */
    public String getProperty(String key) {
        return (String) generalMap.get(key);
    }

    private void loadProperties(Properties props, String fileName) throws IOException {
        loadPropertiesFromClassPath(props, fileName);

    }

    private void loadPropertiesFromClassPath(Properties props, String fileName) throws IOException {
        System.out.println("Loading original properties for file " + fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(getFullFileName(fileName));
        if (resourceAsStream != null) {
            props.load(resourceAsStream);
        }
    }

    private String getFullFileName(String fileName) {
        return fileName + PROPERTIES_SUFFIX;
    }

    private void init() {
        try {
            loadProperties(applicationMap, TEST);
            loadProperties(testMap, APPLICATIONS_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        generalMap.putAll(applicationMap);
        generalMap.putAll(testMap);
    }

    private void setProperty(String key, String value) {
        generalMap.setProperty(key, value);
    }
}