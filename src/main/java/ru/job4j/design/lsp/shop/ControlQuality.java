package ru.job4j.design.lsp.shop;

import java.util.List;

public class ControlQuality {


    public void controlFood(List<Food> foods, List<Store> stores) {
        checkLists(foods, stores);
        for (Store store : stores) {
            for (Food food : foods) {
                store.add(food);
            }
        }
    }

    private void checkLists(List<Food> foods, List<Store> stores) {
        if (foods == null || stores == null || foods.isEmpty() || stores.isEmpty()) {
            throw new IllegalArgumentException("Error list");
        }
    }
}
