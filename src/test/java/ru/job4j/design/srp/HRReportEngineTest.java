package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}