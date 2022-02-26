package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lines;
            while ((lines = reader.readLine()) != null) {
                String[] str = lines.split(" ");
                if ("404".equals(str[str.length - 2])) {
                    list.add(lines);
                }
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(writer::println);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}