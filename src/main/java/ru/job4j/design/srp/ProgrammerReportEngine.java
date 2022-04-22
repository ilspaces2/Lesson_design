package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ProgrammerReportEngine implements Report {
    private Store store;

    public ProgrammerReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("<!DOCTYPE html>").append(ln)
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>ProgrammerReport</title>").append(ln)
                .append("</head>").append(ln)
                .append("<body>").append(ln)
                .append("<h1>Name; Hired; Fired; Salary;</h1>").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>").append(ln);
        }
        text.append("</body>").append(ln)
                .append("</html>").append(ln);
        return text.toString();
    }
}
