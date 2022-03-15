package ru.job4j.find;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для извлечения пар ключ=значение из входных аргументов.
 */
public class InputArgs {

    private final Map<String, String> pairs = new HashMap<>();

    /**
     * Разбиваем аргументы на пары и записываем в map.
     * @param arguments входной массив аргументов
     */
    private void parse(String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Arguments length equals 0");
        }
        for (String argument : arguments) {
            if (!argument.startsWith("-")) {
                throw new IllegalArgumentException("Arguments must start with \"-\". Use: -key=value");
            }
            String[] pair = argument.substring(1).split("=", 2);
            if (pair.length == 1 || pair[0].isBlank() || pair[1].isBlank()) {
                throw new IllegalArgumentException("Arguments format error. Use: -key=value");
            }
            pairs.put(pair[0], pair[1]);
        }
    }

    /**
     * Получаем результаты после работы метода parse.
     * @param arguments входной массив аргументов
     * @return возвращаем объект который содержит map с ключами и значениями
     */
    public static InputArgs of(String... arguments) {
        InputArgs inputArgs = new InputArgs();
        inputArgs.parse(arguments);
        return inputArgs;
    }

    /**
     * Получить значенние по ключу.
     * @param key ключ
     * @return значение ключа
     */
    public String get(String key) {
        String value = pairs.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key incorrect. "
                    + "Use: -d=value -n=value -t=value -o=value");
        }
        return value;
    }
}


