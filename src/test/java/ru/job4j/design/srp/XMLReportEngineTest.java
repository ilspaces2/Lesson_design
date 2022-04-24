package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class XMLReportEngineTest {
    @Test
    public void whenJSONGenerates() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSXXX");
        Employee employee1 = new Employee("Boris", now, now, 300);
        Employee employee2 = new Employee("Anna", now, now, 200);
        store.add(employee1);
        store.add(employee2);
        Report engine = new XMLReportEngine(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<store>\n")
                .append("    <employees>\n")
                .append("        <employee>\n")
                .append(String.format("            <fired>%s</fired>\n",
                        dateFormat.format(employee1.getFired().getTime())))
                .append(String.format("            <hired>%s</hired>\n",
                        dateFormat.format(employee1.getHired().getTime())))
                .append(String.format("            <name>%s</name>\n",
                        employee1.getName()))
                .append(String.format("            <salary>%s</salary>\n",
                        employee1.getSalary()))
                .append("        </employee>\n")
                .append("        <employee>\n")
                .append(String.format("            <fired>%s</fired>\n",
                        dateFormat.format(employee2.getFired().getTime())))
                .append(String.format("            <hired>%s</hired>\n",
                        dateFormat.format(employee2.getHired().getTime())))
                .append(String.format("            <name>%s</name>\n",
                        employee2.getName()))
                .append(String.format("            <salary>%s</salary>\n",
                        employee2.getSalary()))
                .append("        </employee>\n")
                .append("    </employees>\n")
                .append("</store>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}