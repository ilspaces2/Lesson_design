package ru.job4j.gc;

/**
 * В VM options можно задавать некоторые настрйки JVM.
 * Размер хипа для нашей программы можно задать с помощью ключей -XmxNm -XmsNm
 * соответственно максимальный и начальный размеры хипа.
 * Можно ли как-то вызвать сборку мусора из кода? Да, можно. Но виртуальная машина может это проигнорировать.
 * Чтобы вызвать сборку мусора нужно написать System.gc().
 */

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * Выводим некоторую информацию о состоянии памяти.
     */
    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }
}