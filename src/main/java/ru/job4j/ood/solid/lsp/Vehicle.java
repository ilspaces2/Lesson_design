package ru.job4j.ood.solid.lsp;

import java.util.List;

public class Vehicle {

    static class Car {
        void drive() {
        }
    }

    static class SuperCar extends Car {
        @Override
        void drive() {
            /*
            изменили поведение метода
             */
        }
    }

    public void test(List<? extends Car> cars) {
       /*
       какаято логика
        */
    }

    public static void main(String[] args) {
        new Vehicle().test(List.of(new SuperCar()));
        /*
        класс суперкар изменил нам программу
         */
    }
}
