package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile implements File {

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

    public void saveContent(String content) {
        try {
            OutputStream o = new FileOutputStream(String.valueOf(file));
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String content(Predicate<Character> filter) {
        String output = "";
        try {
            InputStream i = new FileInputStream(String.valueOf(file));
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}