package ru.job4j.transfer;

public class User {
    private final int id;
    private  String amount;

    public User(int id, String amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
