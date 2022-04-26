package ru.job4j.ood.solid.lsp;

public class Bank {

    public void putMoney(double money) {
        if (money < 0) {
            System.out.println("какой-то код");
        }
        System.out.println("какой-то код");
    }

    public double getMoney() {
        double returnMoney = 50;
        double cash = 100;
        System.out.println("какой-то код");

        if (returnMoney < cash) {
            System.out.println("Выбросить исключение");
        }
        return returnMoney;
    }


    class BankOne extends Bank {
        @Override
        public void putMoney(double money) {
            if (money < 0) {
                System.out.println("какой-то код");
            }
            if (money > 1000) {
                System.out.println("усилили предусловие у наследника");
            }
            System.out.println("какой-то код");
        }

        @Override
        public double getMoney() {
            double returnMoney = 50;
            double cash = 100;
            System.out.println("какой-то код");

            System.out.println("ослабили постусловие");

            return returnMoney;
        }
    }
}
