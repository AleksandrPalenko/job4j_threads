package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final String file;
    private final int speed;

    public Wget(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long bytesWrite = 0;
            long timeStart = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead); /*bytesRead - Это кол-во прочтенных за раз байт*/
                bytesWrite += bytesRead;
                if (bytesWrite >= speed) {
                    long deltaTime = System.currentTimeMillis() - timeStart;
                    if (deltaTime < 1000) {
                        Thread.sleep(1000 - deltaTime);
                    }
                    timeStart = System.currentTimeMillis();
                    bytesWrite = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ey) {
            ey.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("""
                    Invalid arguments, please correct parameters: \s
                     args[0] -> URL
                     args[1] -> speed in Bytes/s
                     args[2] -> name of file / path to file"""
            );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        String url = "https://proof.ovh.net/files/10Mb.dat";
        String file = "C:/projects/job4j_threads/10Mb.dat";
        int speed = 1024 * 1024; ограничивать скорость до 1 мегабайта в секунду
        */

        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new Wget(url, speed, file));
        wget.start();
        wget.join();
    }
}
