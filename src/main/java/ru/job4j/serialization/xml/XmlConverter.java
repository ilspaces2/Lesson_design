package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Порядок преобразования в XML и обратно:
 * <ul>
 *     <li> Получаем контекст для доступа к АПИ:
 *              JAXBContext context = JAXBContext.newInstance(Object.class);
 *     <li>Создаем сериализатор:
 *              Marshaller marshaller=context.createMarshaller();
 *     <li>Указываем, что нам нужно форматирование текста:
 *              marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 *     <li>Сериализуем (в данном примере пишем в строковую переменную, можно писать в файл):
 *              marshaller.marshal(shop,writer);
 *     <li>Для десериализации нужно создать десериализатор
 *              Unmarshaller unmarshaller = context.createUnmarshaller();
 *     <li>десериализуем
 *              Object result = (Object)unmarshaller.unmarshal(reader);
 * <ul/>
 */

public class XmlConverter {
    public static void main(String[] args) throws Exception {
        Shop shop = new Shop(true, "5Retail", 5,
                new Bayer("man"), "manager", "security");
        JAXBContext context = JAXBContext.newInstance(Shop.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(shop, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Shop result = (Shop) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
