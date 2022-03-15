package ru.job4j.find;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для поиска файлов по критериям с последующим сохранением результатов в файл.
 */
public class Find {

    /**
     * Проверка аргументов, получение условий для поиска, поиск файлов, запись результатов.
     *
     * @param inputArgs входящие аргументы
     */
    public static void find(InputArgs inputArgs) throws IOException {
        checkArgs(inputArgs);
        SearchFiles searchFiles = new SearchFiles();
        getSearchCriteria(searchFiles, inputArgs);
        Files.walkFileTree(Path.of(inputArgs.get("d")), searchFiles);
        write(searchFiles.getPaths(), inputArgs.get("o"));
    }

    /**
     * Выбор критерия по которому будет осуществлятся поиск.
     *
     * @param searchFiles класс который содержит условие для поиска
     * @param inputArgs   входные аргументы
     */
    private static void getSearchCriteria(SearchFiles searchFiles, InputArgs inputArgs) {
        String searchType = inputArgs.get("t");
        if ("name".equals(searchType)) {
            searchFiles.setPredicate(
                    el -> inputArgs.get("n").equals(el.toFile().getName()));
        } else if ("mask".equals(searchType)) {
            searchFiles.setPredicate(
                    el -> el.toFile().getName().matches(inputArgs.get("n")
                            .replace(".", "\\.")
                            .replace("?", ".")
                            .replace("*", ".*")));
        } else if ("regex".equals(searchType)) {
            searchFiles.setPredicate(
                    el -> el.toFile().getName().matches(inputArgs.get("n")));
        }
    }

    /**
     * Запись полученных результатов в файл.
     *
     * @param paths  лист с путями
     * @param target имя файла, куда будем записывать результаты поиска
     */
    private static void write(List<Path> paths, String target) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            paths.forEach(pw::println);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Проверка аргументов. Ключи -n и -o просто вызываются чтобы убедится что они
     * есть в аргументах.
     *
     * @param inputArgs -входящие аргументы
     */
    private static void checkArgs(InputArgs inputArgs) {
        Path path = Path.of(inputArgs.get("d"));
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory incorrect");
        }
        String searchType = inputArgs.get("t");
        if (!"mask".equals(searchType) && !"name".equals(searchType)
                && !"regex".equals(searchType)) {
            throw new IllegalArgumentException("Search type incorrect. "
                    + "Use: -t=mask ,-t=name or -t=regex");
        }
        inputArgs.get("n");
        inputArgs.get("o");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of arguments should be 4");
        }
        InputArgs inputArgs = InputArgs.of(args);
        find(inputArgs);
    }
}

