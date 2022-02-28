package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerOffOnOffOn() throws IOException {
        File srcFile = folder.newFile("server.txt");
        File trgFile = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter((srcFile))) {
            writer.println("400 10:56:01");
            writer.println("500 10:57:01");
            writer.println("200 10:58:01");
            writer.println("300 10:59:01");
            writer.println("200 11:01:02");
            writer.println("400 11:02:02");
            writer.println("200 11:02:11");
        }
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        Analizy analizy = new Analizy();
        analizy.unavailable(srcFile.getAbsolutePath(), trgFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(trgFile))) {
            reader.lines().forEach(stringJoiner::add);
        }
        assertThat(stringJoiner.toString(), is("10:56:01;10:58:01;" + System.lineSeparator()
                                                    + "11:02:02;11:02:11;"));
    }

    @Test
    public void whenServerOff() throws IOException {
        File srcFile = folder.newFile("server.txt");
        File trgFile = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter((srcFile))) {
            writer.println("400 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("400 11:02:02");
            writer.println("400 11:02:11");
        }
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        Analizy analizy = new Analizy();
        analizy.unavailable(srcFile.getAbsolutePath(), trgFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(trgFile))) {
            reader.lines().forEach(stringJoiner::add);
        }
        assertThat(stringJoiner.toString(), is("10:56:01;11:02:11;"));
    }

    @Test
    public void whenServerOn() throws IOException {
        File srcFile = folder.newFile("server.txt");
        File trgFile = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter((srcFile))) {
            writer.println("200 10:56:01");
            writer.println("200 10:57:01");
            writer.println("200 10:58:01");
            writer.println("200 10:59:01");
            writer.println("200 11:01:02");
            writer.println("200 11:02:02");
            writer.println("200 11:02:11");
        }
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        Analizy analizy = new Analizy();
        analizy.unavailable(srcFile.getAbsolutePath(), trgFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(trgFile))) {
            reader.lines().forEach(stringJoiner::add);
        }
        assertThat(stringJoiner.toString(), is(isEmptyString()));
    }
}