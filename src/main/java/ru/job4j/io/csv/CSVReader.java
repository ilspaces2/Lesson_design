package ru.job4j.io.csv;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        checkArgs(argsName);
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(Path.of(argsName.get("path")))) {
            String[] line = scanner.nextLine().split(delimiter);
            List<Integer> indexes = getIndexes(line, filter);
            result.add(filterElements(line, delimiter, indexes));
            while (scanner.hasNext()) {
                line = scanner.nextLine().split(delimiter);
                result.add(filterElements(line, delimiter, indexes));
            }
            print(result, argsName.get("out"));
        }
    }

    private static List<Integer> getIndexes(String[] line, String[] filter) {
        List<Integer> saveIndex = new ArrayList<>();
        for (String str : filter) {
            for (int j = 0; j < line.length; j++) {
                if (str.equals(line[j])) {
                    saveIndex.add(j);
                    break;
                }
            }
        }
        if (saveIndex.isEmpty()) {
            throw new IllegalArgumentException("Filter error");
        }
        return saveIndex;
    }

    private static String filterElements(String[] line, String delimiter, List<Integer> indexes) {
        StringJoiner rez = new StringJoiner(delimiter);
        indexes.forEach(el -> rez.add(line[el]));
        return rez.toString();
    }

    private static void checkArgs(ArgsName argsName) {
        Path path = Path.of(argsName.get("path"));
        if (!path.toFile().exists() || !path.toFile().getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong input file");
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Wrong delimiter ");
        }
        if (!"stdout".equals(argsName.get("out"))) {
            if (!argsName.get("out").endsWith(".csv")) {
                throw new IllegalArgumentException("Wrong output format");
            }
        }

    }

    private static void print(List<String> rez, String out) {
        if ("stdout".equals(out)) {
            rez.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out), true)) {
                rez.forEach(pw::println);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of parameters should be 4.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}