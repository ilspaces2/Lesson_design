package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private static final String INDENT = "---";

    @Override
    public void print(Menu menu) {
        StringBuilder strings = new StringBuilder();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int cnt = menuItemInfo.getNumber().split("\\.").length - 1;
            strings.append(String.format("%s%s%s%n",
                    INDENT.repeat(cnt),
                    menuItemInfo.getNumber(),
                    menuItemInfo.getName()));
        }
        System.out.print(strings);
    }
}
