package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;

import java.util.function.Predicate;

public class XMLReportEngine implements Report {

    private Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = null;
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}