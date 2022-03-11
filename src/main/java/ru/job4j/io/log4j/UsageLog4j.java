package ru.job4j.io.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte bit = 8;
        short capacity = 16364;
        int age = 10;
        long distance = 1000L;
        float pi = 3.14f;
        double money = 1000.123;
        char one = 49;
        boolean key = true;

        LOG.debug("Bit: {}, Capacity: {}, Age: {}, Distance: {}", bit, capacity, age, distance);
        LOG.debug("Pi: {}, Money: {}, One: {}, Key: {}", pi, money, one, key);


    }
}