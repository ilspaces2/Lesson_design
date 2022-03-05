package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        if (!Files.exists(Path.of(botAnswers))) {
            throw new IllegalArgumentException("Файл с ответами не существует");
        }
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        String question = null;
        String answer;
        boolean continueKey = true;
        while (!OUT.equals(question)) {
            question = input.nextLine();
            if (STOP.equals(question) || OUT.equals(question)) {
                continueKey = false;
            }
            if (CONTINUE.equals(question)) {
                log.add("User: " + question);
                continueKey = true;
                continue;
            }
            if (continueKey) {
                answer = answers.get(random.nextInt(answers.size()));
                System.out.println(answer);
                log.add("User: " + question);
                log.add("Bot: " + answer);
            } else {
                log.add("User: " + question);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException err) {
            err.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8), true)) {
            log.forEach(pw::println);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chatLog.txt", "./answers.txt");
        cc.run();
    }
}
