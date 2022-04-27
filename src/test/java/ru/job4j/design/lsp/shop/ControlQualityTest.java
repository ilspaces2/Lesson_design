package ru.job4j.design.lsp.shop;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenAddToShop() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        List<Food> foods = List.of(
                new Milk("Milk 5%", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(10), 10, 0));
        new ControlQuality().controlFood(foods, stores);
        assertThat(foods.get(0), is(shop.findByFilter(e -> true).get(0)));
        assertTrue(warehouse.findAll().isEmpty());
        assertTrue(trash.findAll().isEmpty());
    }

    @Test
    public void whenAddToShopWithDiscount() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        List<Food> foods = List.of(
                new Bread("White", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(1), 10, 10));
        new ControlQuality().controlFood(foods, stores);
        assertThat(foods.get(0), is(shop.findAll().get(0)));
        assertThat(9.0, is(shop.findAll().get(0).getPrice()));
        assertTrue(warehouse.findAll().isEmpty());
        assertTrue(trash.findAll().isEmpty());
    }

    @Test
    public void whenAddToWarehouse() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        List<Food> foods = List.of(
                new Milk("Milk 5%", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(100), 10, 10));
        new ControlQuality().controlFood(foods, stores);
        assertThat(foods.get(0), is(warehouse.findAll().get(0)));
        assertTrue(shop.findAll().isEmpty());
        assertTrue(trash.findAll().isEmpty());
    }

    @Test
    public void whenAddToTrash() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        List<Food> foods = List.of(
                new Water("BonAqua%", LocalDate.now().minusDays(10),
                        LocalDate.now().minusDays(5), 10, 10));
        new ControlQuality().controlFood(foods, stores);
        assertThat(foods.get(0), is(trash.findAll().get(0)));
        assertTrue(shop.findAll().isEmpty());
        assertTrue(warehouse.findAll().isEmpty());
    }

    @Test
    public void whenAddToWarehouseAndShopAndShopWithDiscountAndTrash() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        List<Food> foods = List.of(
                new Milk("Milk 5%", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(10), 10, 0),
                new Bread("White", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(100), 10, 10),
                new Bread("Black,", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(8), 14, 0),
                new Water("BonAqua%", LocalDate.now().minusDays(10),
                        LocalDate.now().minusDays(5), 10, 10)
        );
        new ControlQuality().controlFood(foods, stores);
        assertThat(foods.get(0), is(shop.findAll().get(0)));
        assertThat(foods.get(1), is(warehouse.findAll().get(0)));
        assertThat(foods.get(2), is(shop.findAll().get(1)));
        assertThat(foods.get(3), is(trash.findAll().get(0)));
    }

    @Test
    public void whenDeleteAllFromShop() {
        Store shop = new Shop();
        List<Store> stores = List.of(shop);
        new ControlQuality().controlFood(List.of(
                new Milk("Milk 5%", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(10), 10, 0)), stores);
        shop.deleteAll();
        assertThat(shop.findAll().size(), is(0));
    }

    @Test
    public void whenAddToStoreThenTrue() {
        Store shop = new Shop();
        assertTrue(shop.add(new Milk("Milk 5%", LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10), 10, 0)));
    }

    @Test
    public void whenAddToStoreThenFalse() {
        Store shop = new Shop();
        assertFalse(shop.add(new Milk("Milk 5%", LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(5), 10, 0)));
    }
}


