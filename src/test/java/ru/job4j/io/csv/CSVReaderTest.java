package ru.job4j.io.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.io.ArgsName;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVReaderTest {

    @TempDir
    public Path temporaryFolder;

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.resolve("source.csv").toFile();
        File target = temporaryFolder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterThreeColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.resolve("source.csv").toFile();
        File target = temporaryFolder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age,education"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age;education",
                "Tom;20;Bachelor",
                "Jack;25;Undergraduate",
                "William;30;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test()
    public void whenFilterIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> {
            String data = String.join(
                    System.lineSeparator(),
                    "name;age;last_name;education",
                    "Tom;20;Smith;Bachelor",
                    "Jack;25;Johnson;Undergraduate",
                    "William;30;Brown;Secondary special"
            );
            File file = temporaryFolder.resolve("source.csv").toFile();
            File target = temporaryFolder.resolve("target.csv").toFile();
            ArgsName argsName = ArgsName.of(new String[]{
                    "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=male"
            });
            Files.writeString(file.toPath(), data);
            CSVReader.handle(argsName);
        });
    }

    @Test()
    public void whenPathIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = temporaryFolder.resolve("source.csv").toFile();
            File target = temporaryFolder.resolve("target.csv").toFile();
            ArgsName argsName = ArgsName.of(new String[]{
                    "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name"
            });
            CSVReader.handle(argsName);
        });
    }

    @Test()
    public void whenDelimiterIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = temporaryFolder.resolve("source.csv").toFile();
            File target = temporaryFolder.resolve("target.csv").toFile();
            ArgsName argsName = ArgsName.of(new String[]{
                    "-path=" + file.getAbsolutePath(), "-delimiter=,", "-out=" + target.getAbsolutePath(), "-filter=name"
            });
            CSVReader.handle(argsName);
        });
    }

    @Test()
    public void whenOutToFileIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = temporaryFolder.resolve("source.csv").toFile();
            File target = temporaryFolder.resolve("target.csv").toFile();
            ArgsName argsName = ArgsName.of(new String[]{
                    "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name"
            });
            CSVReader.handle(argsName);
        });
    }

    @Test()
    public void whenOutToConsoleIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = temporaryFolder.resolve("source.csv").toFile();
            ArgsName argsName = ArgsName.of(new String[]{
                    "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=out", "-filter=name"
            });
            CSVReader.handle(argsName);
        });
    }
}