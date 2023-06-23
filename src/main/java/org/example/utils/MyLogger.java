package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The primary purpose of this class is to allow access to logs
 */
public class MyLogger {
    private static Logger logger = LoggerFactory.getLogger(MyLogger.class);

    /**
     * The method which represents the logger instance
     * @return logger
     */
    public static Logger getLogger() {
        return logger;
    }
}
