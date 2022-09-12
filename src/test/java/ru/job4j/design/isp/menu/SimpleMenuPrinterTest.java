package ru.job4j.design.isp.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenu() {
        SimpleMenuPrinterInterceptor printer = new SimpleMenuPrinterInterceptor();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        printer.print(menu);
        String expected = """
                1.Сходить в магазин
                ---1.1.Купить продукты
                ------1.1.1.Купить хлеб
                ------1.1.2.Купить молоко
                2.Покормить собаку
                """;
        assertEquals(printer.getOutput(), expected);
    }
}