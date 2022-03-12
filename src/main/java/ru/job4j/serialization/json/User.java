package ru.job4j.serialization.json;

public class User {
    private final String name;
    private final String mail;

    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", mail='" + mail + '\''
                + '}';
    }
}
