package ru.job4j.design.lsp.shop;

import java.util.*;
import java.util.stream.Collectors;

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

    public void resort(List<Store> stores) {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.findAll());
            store.deleteAll();
        }
        controlFood(foods, stores);
    }
}
