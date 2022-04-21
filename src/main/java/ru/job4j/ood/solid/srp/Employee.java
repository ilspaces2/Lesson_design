package ru.job4j.ood.solid.srp;

import java.util.List;

public class Employee {
    private String post;
    private String name;
    private String surname;

    public Employee(String post, String name, String surname) {
        this.post = post;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return post;
    }

    public void setId(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getInfoEmployee() {
        return List.of(post, name, surname);
    }

    public float calculateSalary(int days, float rate) {
        /*
         * Калькуляция зарплаты
         */
        return 0;
    }

}
