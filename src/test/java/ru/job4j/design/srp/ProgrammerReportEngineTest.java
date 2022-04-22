package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProgrammerReportEngineTest {

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String ln = System.lineSeparator();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Bob", now, now, 230);
        Employee worker3 = new Employee("Max", now, now, 451.5);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ProgrammerReportEngine(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<!DOCTYPE html>").append(ln)
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>ProgrammerReport</title>").append(ln)
                .append("</head>").append(ln)
                .append("<body>").append(ln)
                .append("<h1>Name; Hired; Fired; Salary;</h1>").append(ln)
                .append("<p>")
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary()).append(";")
                .append("</p>").append(ln)
                .append("<p>")
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";")
                .append("</p>").append(ln)
                .append("<p>")
                .append(worker3.getName()).append(";")
                .append(worker3.getHired()).append(";")
                .append(worker3.getFired()).append(";")
                .append(worker3.getSalary()).append(";")
                .append("</p>").append(ln)
                .append("</body>").append(ln)
                .append("</html>").append(ln);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}