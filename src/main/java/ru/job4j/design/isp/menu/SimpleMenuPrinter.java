package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private StringBuilder strings = new StringBuilder();

    public String getStrings() {
        return strings.toString();
    }

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int cnt = menuItemInfo.getNumber().split("\\.").length - 1;
            strings.append(String.format("%s%s%s%n",
                    "---".repeat(cnt),
                    menuItemInfo.getNumber(),
                    menuItemInfo.getName()));
        }
        System.out.println(strings);
    }
}
