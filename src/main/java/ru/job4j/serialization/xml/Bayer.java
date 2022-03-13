package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bayer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bayer {

    @XmlAttribute
    private String sex;

    public Bayer() {
    }

    public Bayer(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Bayer{"
                + "sex='" + sex + '\''
                + '}';
    }
}
