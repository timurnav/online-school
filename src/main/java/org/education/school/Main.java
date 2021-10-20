package org.education.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("org.education.school.config");
        logger.info("Context loaded");
    }
}
