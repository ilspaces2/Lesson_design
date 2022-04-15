package ru.job4j.gc.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Emulator {

    private final DirFileCache dirFileCache;

    public Emulator(String directoryCache) {
        dirFileCache = new DirFileCache(directoryCache);
    }

    public String getFileFromCache(String fileName) {
        return dirFileCache.get(fileName);
    }

    public String loadFileToCache(String fileName) {
        return dirFileCache.load(fileName);
    }

    public static void main(String[] args) throws InterruptedException {
        Emulator emulator = new Emulator("src/main/java/ru/job4j/gc/cache/cacheDir");
        emulator.loadFileToCache("Address.txt");
        emulator.loadFileToCache("Names.txt");
        emulator.loadFileToCache("Phone.txt");

        List<Object> loadHeap = new ArrayList<>();
        for (int i = 0; i < 106_710; i++) {
            loadHeap.add(new Object());
        }

        System.gc();
        TimeUnit.SECONDS.sleep(3);

        String phones = emulator.getFileFromCache("Phone.txt");
        if (phones != null) {
            System.out.println(phones);
        }
    }
}
