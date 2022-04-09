package ru.job4j.gc;

public class User {
    int id;
    double random;

    public User(int id, double random) {
        this.id = id;
        this.random = random;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(id);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 80000; i++) {
            new User(i, i);
        }
    }
}
