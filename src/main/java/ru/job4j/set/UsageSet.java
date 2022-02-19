package ru.job4j.set;

import java.util.*;
import java.util.Set;

/*
 * Set хранит в себе уникальные элементы
 * HashSet не упорядочен
 */
public class UsageSet {

    /*
     * можно изпользовать стримы
     */
    static <T> void print(Set<T> col) {
        col.forEach(s -> System.out.println("Текущий элемент: " + s));
        System.out.println(col.size() + "\n");
    }

    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        strings.addAll(List.of("one", "four", "five"));
        strings.remove("four");
        print(strings);

        /*
         * метод с of возвращают список который нельзя редактировать
         */
        Set<String> stringsTwo = Set.of("one", "two", "three");
        print(stringsTwo);

        Iterator<String> it = strings.iterator();
        System.out.println("Iterator");
        while (it.hasNext()) {
            System.out.println("Текущий элемент: " + it.next());
        }

        /*
         * удаляем элементы, которые  задали в параметрах метода
         */
        strings.removeAll(List.of("two", "three"));
        System.out.println("Вывод в консоль после удаления...");
        print(strings);
        strings.addAll(List.of("two", "three"));

        /*
         * удаляем элементы коллекции кроме тех что задали в параметрах метода
         */
        strings.retainAll(List.of("one"));
        System.out.println("Вывод в консоль после удаления...");
        print(strings);
        strings.addAll(List.of("two", "three"));

        /*
         * удаляем если условие совпадает, в метод передается предикат
         */
        strings.removeIf(s -> s.startsWith("o"));
        System.out.println("Вывод в консоль после удаления...");
        print(strings);


        /*
         * LinkedHashSet хранит элементы в порядке добавления
         */
        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(4, 11, 3, 45, 3, 433, 7, 93, 9));
        print(linkedHashSet);

        /*
         * TreeSet сортирует элементы ascending (по возрастанию)
         * есть возможность задать в конструкторе компаратоор
         */
        Set<Integer> treeSet = new TreeSet<>(List.of(4, 11, 3, 45, 3, 433, 7, 93, 9));
        print(treeSet);
    }
}