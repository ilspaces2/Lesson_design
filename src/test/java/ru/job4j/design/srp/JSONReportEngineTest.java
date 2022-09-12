package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReportEngineTest {
    @Test
    public void whenJSONGenerates() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Gson gson = new GsonBuilder().create();
        Employee employee1 = new Employee("Boris", now, now, 300);
        Employee employee2 = new Employee("Anna", now, now, 200);
        store.add(employee1);
        store.add(employee2);
        Report engine = new JSONReportEngine(store);
        StringBuilder expect = new StringBuilder();
        expect.append(String.format("[{\"name\":\"%s\",", employee1.getName()))
                .append(String.format("\"hired\":%s,", gson.toJson(employee1.getHired())))
                .append(String.format("\"fired\":%s,", gson.toJson(employee1.getFired())))
                .append(String.format("\"salary\":%s},", employee1.getSalary()))
                .append(String.format("{\"name\":\"%s\",", employee2.getName()))
                .append(String.format("\"hired\":%s,", gson.toJson(employee2.getHired())))
                .append(String.format("\"fired\":%s,", gson.toJson(employee2.getFired())))
                .append(String.format("\"salary\":%s}]", employee2.getSalary()));

        assertEquals(engine.generate(em -> true), expect.toString());
    }
}