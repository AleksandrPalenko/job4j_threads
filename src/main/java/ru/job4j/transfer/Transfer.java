package ru.job4j.transfer;

public interface Transfer {

    boolean transfer(int fromId, int toId, int amount);
}
