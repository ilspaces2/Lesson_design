package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Soft Reference
 * <p>
 * Объекты, на которые ссылаются безопасные ссылки, удаляются только если JVM не хватает памяти,
 * т.е. они могут пережить более одной сборки мусора.
 * <p>
 * Данный тип ссылок подходит для реализации кэша - такой структуры данных, при которой часть
 * данных запоминается, а потом часто переиспользуется.
 * <p>
 * Например, можно запоминать данные из файлов или тяжелых запросов.
 * <p>
 * При нехватке памяти JVM может удалить объекты по этим ссылкам, если на них нет сильных ссылок.
 * <p>
 * Есть контракт для данного типа ссылок: GC гарантировано удалит с кучи все объекты,
 * доступные только по soft-ссылке, перед тем как бросит OutOfMemoryError
 * <p>
 * В первом методе не смотря на то, что мы за'null'уляем сильную ссылку, на объект остается безопасная ссылки,
 * а это значит объект будет удален только при нехватке памяти.
 * <p>
 * Во втором методе, мы создаем много объектов, но на них ссылается безопасная ссылка. Если мы создадим
 * большое количество объектов при малом хипе, мы увидим, что объекты начнут удаляться, т.к. станет не хватать памяти.
 * <p>
 * Корректным использованием безопасных ссылок является сначала получение сильной ссылки на данные,
 * а потом работа с сильной ссылкой.
 * <p>
 * Это гарантирует, что в интервалах получения сильной ссылки из безопасной GC не затрет объект. Это касается не только
 * локальных переменных, но и возвращаемых значений и аргументов.
 */

public class SoftDemo {

    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    /**
     * При таком подходе мы можем потерять данные из безопасной ссылки.
     * После выполнения какихто действий есть шанс что данные будут утеряны и при попытке
     * получить данные мы ничего не увидим.
     */
    private static void unsafe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        if (someData.get(0).get() != null) {
            System.out.println("do something");
        } else {
            System.out.println("do something");
        }
        System.out.println("do something");
        someData.get(0).get();
    }

    /**
     * Правильная работа с безопасной ссылкой. Сначала мы ее сохранаям
     * в сильной ссылке (чтобы ее не затерло сборщиком) а потом уже работаем с полоченными данными
     */
    private static void safe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        Object strong = someData.get(0).get();
        if (strong != null) {
            System.out.println("do something");
        } else {
            System.out.println("do something");
        }
        System.out.println("do something");
    }
}