package ru.job4j.io.simplefilevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> duplicateMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!duplicateMap.containsKey(fileProperty)) {
            duplicateMap.put(fileProperty, new ArrayList<>());
        }
        duplicateMap.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void showFiles() {
        duplicateMap.entrySet().stream()
                .filter(k -> k.getValue().size() > 1)
                .flatMap(k -> k.getValue().stream())
                .forEach(path -> System.out.println(path.toFile().getAbsolutePath()));
    }
}
