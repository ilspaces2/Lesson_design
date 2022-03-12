package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
    public static void main(String[] args) {
        final Computer computer = new Computer("Gaming", 1000, true,
                new String[]{"Intel", "RAM-32Gb"}, new User("Joe", "j@mail.com"));

        final Gson compToJson = new GsonBuilder().create();
        final String compJson = compToJson.toJson(computer);
        System.out.println(compJson);

        final Computer compFromJson = compToJson.fromJson(compJson, Computer.class);
        System.out.println(compFromJson);
    }
}
