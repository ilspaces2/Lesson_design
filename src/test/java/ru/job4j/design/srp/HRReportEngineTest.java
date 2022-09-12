package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRReportEngineTest {

    @Test
    public void whenHRGenerates() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Boris", now, now, 300));
        store.add(new Employee("Anna", now, now, 200));
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name;Salary;")
                .append(System.lineSeparator())
                .append("Boris").append(";").append("300.0").append(";")
                .append(System.lineSeparator())
                .append("Anna").append(";").append("200.0").append(";")
                .append(System.lineSeparator())
                .append("Ivan").append(";").append("100.0").append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }
}