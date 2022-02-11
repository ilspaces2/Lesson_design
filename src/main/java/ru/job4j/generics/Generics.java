package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Обобщения или generics (обобщенные типы и методы)
 * позволяют нам уйти от жесткого определения используемых типов.
 * <p>
 * Во время процесса компилятором выполняется стирание типов,
 * которое заключается в том, что все параметры типа компилятор
 * заменяет каждый своей первой привязкой, если параметр типа ограничен
 * (List < Integer> -> List < Integer>),
 * или Object если параметр типа не ограничен.
 * (List < ?> -> List < Object>)
 * <p>
 * Существует такое понятие, связанное с generics,
 * как необработанные типы (в литературе, интернете еще можно
 * встретить такое название как "сырые типы").
 * Обозначаются они также как и generics в скобках <>, в которых
 * проставляются заглавные латинские символы, зарезервированные
 * специально для этих целей символы.
 * <p>
 * Использование generics имеет несколько ограничений:
 * <p>
 * 1. Невозможно создать массив параметра типа new T[100]
 * <p>
 * 2. Невозможно создать массив Generic-классов
 * List< Integer>[] lists = new List< Integer>[10];
 */
public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /*
        gen.printBoundedWildCard(first);
         */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /*
        gen.printLowerBoundedWildCard(third);
         */
    }

    /**
     * WildCard (обозначается <?>).
     *
     * @param list принимаем на вход лист с любым типом данных
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Bounded Wildcard ("Ограничение сверху")
     *
     * @param list принимаем на вход лист типа Predator и все типы наследников
     *             ( вниз по иерархии)
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Predator next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Lower Bounded Wildcard ("Ограничение снизу")
     *
     * @param list принимаем на вход лист типа Predator и все типы его суперклассов
     *             (вверх по иерархи до object)
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}