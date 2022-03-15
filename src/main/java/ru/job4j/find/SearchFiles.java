package ru.job4j.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Класс осущесвляет проход по директориям ,по заданным критериям, для поиска файлов и сохранаят полученные результаты в ArrayList.
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;

    private final List<Path> pathList = new ArrayList<>();

    /**
     * Получить список с результатами поиска.
     *
     * @return возвращает список (unmodifiable list)
     */
    public List<Path> getPaths() {
        if (pathList.isEmpty()) {
            throw new NoSuchElementException("Files not found");
        }
        return List.copyOf(pathList);
    }

    /**
     * Установить условие для поиска.
     *
     * @param predicate на вход подается предикат
     */
    public void setPredicate(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Поиск файлов по директориям. Сохранение найденных файлов.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathList.add(file);
        }
        return super.visitFile(file, attrs);
    }

    /**
     * Выводит на экран директории которые не удалось открыть.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.printf("Visiting failed for %s%n", file);
        return FileVisitResult.SKIP_SUBTREE;
    }
}