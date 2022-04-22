package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountingReportEngine implements Report {
    private Store store;
    private double rubleCourse;

    public AccountingReportEngine(Store store, double rubleCourse) {
        this.store = store;
        this.rubleCourse = rubleCourse;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * rubleCourse).append(" rub").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
