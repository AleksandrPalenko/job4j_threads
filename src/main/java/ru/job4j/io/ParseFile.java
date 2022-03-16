package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final ParseFile file;

    public ParseFile(ParseFile file) {
        this.file = file;
    }

    /**
     * @return Возвращаем строку с записанным текстом.
     */
    public synchronized String getContent() throws IOException {
        return content(a -> true);
    }

    /**
     * @return Возвращаем строку с записанным текстом и с доп.условием.
     */
    public synchronized String getContentWithoutUnicode() throws IOException {
        return content(a -> (a < 0x80));
    }

    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(String.valueOf(file)))) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}