package ru.job4j.io;

import java.util.function.Predicate;

public interface File {

    public String content(Predicate<Character> filter);
}
