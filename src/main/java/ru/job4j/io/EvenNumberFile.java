package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("even.txt")) {
            StringBuilder builder = new StringBuilder();
            int text;
            while ((text = input.read()) != -1) {
                builder.append((char) text);
            }
            String[] nums = builder.toString().split(System.lineSeparator());
            Arrays.stream(nums).filter(n -> Integer.parseInt(n) % 2 == 0)
                    .forEach(n -> System.out.println(n + " is even"));
        } catch (IOException  err) {
            err.printStackTrace();
        }
    }
}
