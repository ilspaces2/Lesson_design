package ru.job4j.ood.solid.srp;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    /*
    Имеет какие-то еще поля
    Выполняет какойто свой основной функционал. Например коннект к бд
    */

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}