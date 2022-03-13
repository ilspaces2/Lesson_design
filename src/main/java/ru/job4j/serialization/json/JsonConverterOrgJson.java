package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Преобразование в json с помощью библиотеки org.json.
 * Объекты должны иметь гетеры.
 * Объект преобразуется в JSONObject с помощью строки или объекта в конструкторе.
 * Также можно в ручную собрать нужный JSONObject с помощью метода put.
 * JSONArray принимает на вход массивы,листы.
 * Аннотация @JSONPropertyIgnore помогает избежать исключения StackOverflowError,
 * которое может возникнуть когда классы ссылаются друг на друга (рекурсивное зацикливание)
 */

public class JsonConverterOrgJson {
    public static void main(String[] args) {
        JSONObject jsonUser = new JSONObject("{\"name\":\"Joe\","
                                                    + "\"mail\":\"j@mail.com\"}");
        JSONArray jsonEquipment = new JSONArray(new ArrayList<>(Arrays.asList("Intel", "RAM-32Gb")));

        final Computer computer = new Computer("Gaming", 1000, true,
                new String[]{"Intel", "RAM-32Gb"}, new User("Joe", "j@mail.com"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", computer.getName());
        jsonObject.put("price", computer.getPrice());
        jsonObject.put("available", computer.isAvailable());
        jsonObject.put("equipment", jsonEquipment);
        jsonObject.put("user", jsonUser);
        System.out.println(jsonObject);

        JSONObject jsonComp = new JSONObject(computer);
        System.out.println(jsonComp);


    }
}
