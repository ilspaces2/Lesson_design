package ru.job4j.ood.solid.lsp;

public class Parking {

    static class Sedan {

    }

    static class Jeep {

    }

    /*
    приходится дописывать код метода что бы он работал с другим типом
     */
    public void parkingCar(Object o) {
        if (o.getClass() == Sedan.class) {
            System.out.println("sedan");
        } else if (o.getClass() == Jeep.class) {
            System.out.println("jeep");
        }
    }
}
