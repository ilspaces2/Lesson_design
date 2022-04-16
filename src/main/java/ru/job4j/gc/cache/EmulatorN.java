package ru.job4j.gc.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EmulatorN {

    private String directoryCache;

    private DirFileCache dirFileCache;

    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        String ls = System.lineSeparator();
        System.out.println("====================" + ls
                + "1. Select directory" + ls
                + "2. Load file to cache" + ls
                + "3. Get file from cache" + ls
                + "4. Exit" + ls
                + "====================");
    }

    public void start() {
        boolean run = true;
        while (run) {
            int menu = checkInputNumber();
            switch (menu) {
                case 1:
                    setDirectory();
                    break;
                case 2:
                    loadFileToCache();
                    break;
                case 3:
                    getFileFromCache();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Enter 1 to 4");
            }
        }
    }

    private void setDirectory() {
        directoryCache = scanner.nextLine();
        if (directoryCache.isBlank() || Files.notExists(Path.of(directoryCache))) {
            System.out.println("Directory error. Try again.");
            directoryCache = null;
            menu();
        } else {
            dirFileCache = new DirFileCache(directoryCache);
            System.out.println("Directory set.");
            menu();
        }
    }

    private void loadFileToCache() {
        String key = scanner.nextLine();
        if (checkSetDir() && checkFile(key)) {
            String text = dirFileCache.load(key);
            dirFileCache.put(key, text);
            menu();
        }
    }

    private void getFileFromCache() {
        String key = scanner.nextLine();
        if (checkSetDir() && checkFile(key)) {
            System.out.println(dirFileCache.get(key));
            menu();
        }
    }

    private boolean checkFile(String key) {
        String path = String.format("%s/%s", directoryCache, key);
        boolean rez = Files.isRegularFile(Path.of(path));
        if (!rez) {
            System.out.println("File not found. Try again.");
            menu();
        }
        return rez;
    }

    private int checkInputNumber() {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = Integer.parseInt(scanner.nextLine());
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Enter validate data again.");
                menu();
            }
        } while (invalid);
        return value;
    }

    private boolean checkSetDir() {
        boolean rez = directoryCache != null;
        if (!rez) {
            System.out.println("Need set directory.");
            menu();
        }
        return rez;
    }

    public static void main(String[] args) {
        EmulatorN e = new EmulatorN();
        e.menu();
        e.start();
    }
}

