package ru.job4j.design.isp.menu;

public class SimpleActionDelegate implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("Task found");
    }
}
