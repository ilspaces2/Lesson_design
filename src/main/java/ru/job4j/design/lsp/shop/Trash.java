package ru.job4j.design.lsp.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements Store {

    private List<Food> trash = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rez = false;
        if (checkPercent(food)) {
            rez = trash.add(food);
        }
        return rez;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(trash);
    }

    @Override
    public List<Food> findByFilter(Predicate<Food> filter) {
        return trash.stream().filter(filter).toList();
    }

    @Override
    public void deleteAll() {
        trash.clear();
    }

    @Override
    public boolean checkPercent(Food food) {
        int percent = calculateExpiryPercent(food);
        return percent == -1;
    }
}
