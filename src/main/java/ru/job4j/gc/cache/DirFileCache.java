package ru.job4j.gc.cache;

import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text = null;
        try {
            text = Files.readString(Path.of(cachingDir, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}