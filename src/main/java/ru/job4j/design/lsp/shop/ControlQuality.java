package ru.job4j.design.lsp.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        List<Food> foods = stores.stream()
                .flatMap(store -> store.findAll().stream())
                .collect(Collectors.toList());
        controlFood(foods, stores);
    }
}
