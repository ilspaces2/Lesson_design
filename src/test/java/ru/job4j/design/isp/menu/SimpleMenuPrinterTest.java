package ru.job4j.design.isp.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenu() {
        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        printer.print(menu);
        String expected = "1.Сходить в магазин\n"
                + "---1.1.Купить продукты\n"
                + "------1.1.1.Купить хлеб\n"
                + "------1.1.2.Купить молоко\n"
                + "2.Покормить собаку\n";
        assertThat(printer.getStrings(), is(expected));
    }
}