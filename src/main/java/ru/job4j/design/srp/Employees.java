package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Employees() {
    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}