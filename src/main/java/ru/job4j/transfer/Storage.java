package ru.job4j.transfer;

public interface Storage {

    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
}
