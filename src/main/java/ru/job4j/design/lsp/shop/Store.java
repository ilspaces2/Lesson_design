package ru.job4j.design.lsp.shop;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    boolean add(Food food);

    List<Food> findAll();

    List<Food> findByFilter(Predicate<Food> filter);

    void deleteAll();

    boolean checkPercent(Food food);

    default int calculateExpiryPercent(Food food) {
        float create = food.getCreateDate().toEpochDay();
        float expiry = food.getExpiryDate().toEpochDay();
        float now = LocalDate.now().toEpochDay();
        return now >= expiry ? -1 : Math.round((now - create) / (expiry - create) * 100);
    }
}
