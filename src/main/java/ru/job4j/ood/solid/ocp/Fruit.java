package ru.job4j.ood.solid.ocp;

public class Fruit {
    /*
    Расширение за счет наследования. При дальнейших расширениях будет расти
    иерархия наследования. Удобнее воспользоваться абстракцией
     */

    static class Apple {
        static void smell() {
        }
    }

    static class RedApple extends Apple {
        static void smell() {
        }

        static void color() {
        }
    }

    static class GreenApple extends RedApple {
        static void price() {
        }

        static void color() {
        }
    }
}

