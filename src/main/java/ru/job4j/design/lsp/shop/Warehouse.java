package ru.job4j.design.lsp.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Store {

    private List<Food> warehouse = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rez = false;
        if (checkPercent(food)) {
            rez = warehouse.add(food);
        }
        return rez;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(warehouse);
    }

    @Override
    public List<Food> findByFilter(Predicate<Food> filter) {
        return warehouse.stream().filter(filter).toList();
    }

    @Override
    public void deleteAll() {
        warehouse.clear();
    }

    @Override
    public boolean checkPercent(Food food) {
        int percent = calculateExpiryPercent(food);
        return percent >= 0 && percent < 25;
    }
}
