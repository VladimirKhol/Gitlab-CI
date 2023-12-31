package org.example.utils;

import lombok.val;

import java.io.IOException;
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

    private void loadProperties(Properties props, String fileName) {
        loadPropertiesFromClassPath(props, fileName);
    }

    private void loadPropertiesFromClassPath(Properties props, String fileName) {
        getLogger().info("Loading original properties for file " + fileName);
        val classLoader = getClass().getClassLoader();
        val resourceAsStream = classLoader.getResourceAsStream(getFullFileName(fileName));
        try {
            if (resourceAsStream != null)
                props.load(resourceAsStream);
        } catch (IOException e) {
            getLogger().info(e.getMessage());
        }

    }

    private String getFullFileName(String fileName) {
        return fileName + PROPERTIES_SUFFIX;
    }

    private void init() {
        loadProperties(applicationMap, TEST);
        loadProperties(testMap, APPLICATIONS_PROPERTIES);
        generalMap.putAll(applicationMap);
        generalMap.putAll(testMap);
    }

    private void setProperty(String key, String value) {
        generalMap.setProperty(key, value);
    }
}