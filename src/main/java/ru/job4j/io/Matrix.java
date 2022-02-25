package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            for (int row = 1; row <= size; row++) {
                for (int cell = 1; cell <= size; cell++) {
                    out.write((row * cell + "\t").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(10);
    }
}
