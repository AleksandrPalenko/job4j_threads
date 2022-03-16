package ru.job4j.io;

import java.io.*;

public final class SaveContentFile {

    private final SaveContentFile file;

    public SaveContentFile(SaveContentFile file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
       try (OutputStream out = new BufferedOutputStream(new FileOutputStream(String.valueOf(file)))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
