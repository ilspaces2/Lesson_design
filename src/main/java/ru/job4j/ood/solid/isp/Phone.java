package ru.job4j.ood.solid.isp;

public interface Phone {

    void call();

    void sendSMS();

    void makePhoto();
}

class PhoneOne implements Phone {

    @Override
    public void call() {
        System.out.println("Call");
    }

    @Override
    public void sendSMS() {
        System.out.println("SMS");
    }

    @Override
    public void makePhoto() {
        System.out.println("Camera");
    }
}

class PhoneWithOutCamera implements Phone {

    @Override
    public void call() {
        System.out.println("Call");
    }

    @Override
    public void sendSMS() {
        System.out.println("SMS");
    }

    @Override
    public void makePhoto() {
        throw new UnsupportedOperationException("No Camera");
    }
}