package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/**
 * Основные аннотации для JAXB:
 * <ui>
 * <li> XmlRootElement(name = "shop")- указывает имя корневого тэга
 * <li> XmlAccessorType(XmlAccessType.FIELD)- метод доступа к полям(существуют еще др. виды)
 * <li> XmlType(propOrder = { "id", "name", "date" }) -определите порядок, в котором поля записываются в XML-файл
 * <li> XmlAttribute -поле будет выглядеть как атрибут (пара ключ=значение)
 * <li> XmlElementWrapper(name = "qwertes") -оборачивает коллекцию
 * <li> XmlElement(name="qwert") - создается элемент с именем (можно использовать совместно с XmlElementWrapper)
 * <li> XmlTransient -помечается поле которое мы не ходим включать в XML
 * <li> XmlAccessorOrder -для сортировки
 * </ul>
 */

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shop {

    @XmlAttribute
    private boolean open;

    @XmlAttribute(name = "shop_name")
    private String name;

    @XmlAttribute
    private int sales;

    @XmlElement(name = "consumer")
    private Bayer bayer;

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employe")
    private String[] employees;

    public Shop() {
    }

    public Shop(boolean open, String name, int sales, Bayer bayer, String... employees) {
        this.open = open;
        this.name = name;
        this.sales = sales;
        this.bayer = bayer;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "name='" + name + '\''
                + ", open=" + open
                + ", sales=" + sales
                + ", bayer=" + bayer
                + ", employe=" + Arrays.toString(employees)
                + '}';
    }
}
