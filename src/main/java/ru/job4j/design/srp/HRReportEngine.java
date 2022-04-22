package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReportEngine implements Report {
    private Store store;

    public HRReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Salary;").append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        employeeList.sort(Comparator.comparing(Employee::getSalary).reversed());
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
