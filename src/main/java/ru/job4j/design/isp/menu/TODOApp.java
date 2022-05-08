package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class TODOApp {

    private static final String EXIT = "Выход";

    private static final String ADD = "Добавить";

    private static final String INFO = """
            - Для добавления задачи введите: Добавить
            (Для новой задачи оставить поле parent пустым)
            (Для создания подзадачи в поле parent ввести имя существующей задачи)
            - Для выхода введите: Выход
            """;

    public void start(Menu menu, MenuPrinter menuPrinter, ActionDelegate actionDelegate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INFO);
        menuPrinter.print(menu);
        String input = scanner.nextLine();
        while (!EXIT.equals(input)) {
            if (ADD.equals(input)) {
                System.out.println("Enter parent");
                String parent = scanner.nextLine();
                System.out.println("Enter child");
                String child = scanner.nextLine();
                menu.add(parent.isBlank() ? null : parent, child, actionDelegate);
            } else if (menu.select(input).isPresent()) {
                menu.select(input).get().getActionDelegate().delegate();
            }
            menuPrinter.print(menu);
            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        ActionDelegate actionDelegate = new SimpleActionDelegate();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", actionDelegate);
        menu.add(Menu.ROOT, "Покормить собаку", actionDelegate);
        menu.add("Сходить в магазин", "Купить продукты", actionDelegate);
        menu.add("Купить продукты", "Купить хлеб", actionDelegate);
        menu.add("Купить продукты", "Купить молоко", actionDelegate);
        new TODOApp().start(menu, menuPrinter, actionDelegate);
    }
}
