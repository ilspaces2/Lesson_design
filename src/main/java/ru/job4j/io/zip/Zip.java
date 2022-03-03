package ru.job4j.io.zip;

import ru.job4j.io.ArgsName;
import ru.job4j.io.filevisitor.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void checkArgs(Path directory, String exclude, File output) {
        if (!Files.isDirectory(directory) || !exclude.startsWith(".") || !output.getName().endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect parameters."
                    + "Use: -d=FOLDER -e=.EXTENSION_FILE -o=NAME_FILE.zip");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getAbsolutePath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Number of parameters should be 3.");
        }
        ArgsName parameters = ArgsName.of(args);
        Path directory = Path.of(parameters.get("d"));
        String exclude = parameters.get("e");
        File output = new File(parameters.get("o"));
        checkArgs(directory, exclude, output);

        List<Path> files = Search.search(directory, p -> !p.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(files, output);
    }
}