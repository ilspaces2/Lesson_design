package ru.job4j.io.simplefilevisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("c:\\job4j\\job4j_design\\data"), duplicatesVisitor);
        duplicatesVisitor.showFiles();
    }
}