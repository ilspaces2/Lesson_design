package ru.job4j.map;

import java.util.*;

/**
 * Демонстрация как два одинаковых с виду объекта ведут себя в HashMap.
 * Методы equals и hashCode влияют на результат HashMap. Отсутсвие этих методов,
 * или одного из них, уже не дает нам корректно сравнить объекты.
 *
 * <p>
 * При добавлении значения в Map происходит следующее:
 * <ol>
 *      <li> Ключ преобразуется в числовое значение с помощью hashCode()
 * 		<li> Вычисляется хэш ключа с помощью hash()
 * 		<li> Определяется место в массиве(тоесть индекс) с помощью хэша и длинны массива
 * 		<li> Если места хватает то переходим дальше , иначе нужно расшириться
 * 			делается рехеширование
 * 		<li> Далее смотрим свободен ли бакет по индексу из пункта 3.
 * 		<li> Если свободно то создается объект Node, в него записывается хэш, ключ и значение
 *      <li> В случае если бакет занят (возникает колизия. Может быть по причинам:hashCode() hash(),index).
 * 			 Выполняется проверка  по hash. Если не равно то добавляется объект.
 * 			 Если по hash равны то идет проверка по ссылкам и по equals. В случае если есть равенство то объект переписывается,
 * 			 иначе создается новый объект, и у нас в бакете образуется уже связный список из нескольких объектов.
 * 	<ol>
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar();
        date.setTimeInMillis(951429661000L);
        Map<User, Object> users = new HashMap();
        users.put(new User("Mike", 1, date), new Object());
        users.put(new User("Mike", 1, date), new Object());

        for (Map.Entry<User, Object> user : users.entrySet()) {
            System.out.println(user.getKey());
            System.out.println(user.getValue() + "\n");
        }
    }
}
