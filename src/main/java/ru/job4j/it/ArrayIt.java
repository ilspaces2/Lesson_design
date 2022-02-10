package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Шаблон "итератор" позволяет последовательно получить элементы набора данных.
 * Описывается интерфейсом - java.util.Iterator.
 * Используется в коллекциях, базах данных, чтении файлов.
 */
public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     * Многократный вызов этого метода должен быть одинаковым.
     *
     * @return если следующего элемента нету то возвращает false
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод next сдвигает указатель итератора.
     * Указатель - это ссылка на элемент, который нужно вернуть.
     *
     * @return возвращает элемент
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}