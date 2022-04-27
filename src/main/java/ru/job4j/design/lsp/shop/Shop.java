package ru.job4j.design.lsp.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Store {
    private List<Food> shop = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rzl = false;
        if (checkPercent(food)) {
            rzl = shop.add(food);
        } else if (calculateExpiryPercent(food) > 75) {
            food.setPrice(calculateDiscount(food));
            rzl = shop.add(food);
        }
        return rzl;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(shop);
    }

    @Override
    public List<Food> findByFilter(Predicate<Food> filter) {
        return shop.stream().filter(filter).toList();
    }

    @Override
    public void deleteAll() {
        shop.clear();
    }

    @Override
    public boolean checkPercent(Food food) {
        int percent = calculateExpiryPercent(food);
        return percent >= 25 && percent <= 75;
    }

    private double calculateDiscount(Food food) {
        return food.getPrice() - (food.getPrice() * (food.getDiscount() / 100.0));
    }
}
