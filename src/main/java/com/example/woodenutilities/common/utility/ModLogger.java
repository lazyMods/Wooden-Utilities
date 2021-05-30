package com.example.woodenutilities.common.utility;

import org.apache.logging.log4j.Logger;

public class ModLogger {

    private static final String LOGGING_PATTERN = "{} {}";
    private static final String PREFIX = "[Wooden Utilities]:";
    private final Logger logger;

    public ModLogger(Logger logger) {
        this.logger = logger;
    }

    public void debug(String msg) {
        //TODO: Create config that enables or disables debug messages.
        logger.info(LOGGING_PATTERN, PREFIX, msg);
    }

    public void error(String msg) {
        logger.error(LOGGING_PATTERN, PREFIX, msg);
    }

    public void info(String msg) {
        logger.info(LOGGING_PATTERN, PREFIX, msg);
    }

    public void info(int num) {
        info(String.format("%d", num));
    }

    public void warn(String msg) {
        logger.warn(LOGGING_PATTERN, PREFIX, msg);
    }
}