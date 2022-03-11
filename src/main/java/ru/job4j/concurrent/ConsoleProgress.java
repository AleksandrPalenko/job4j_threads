package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        ConsoleProgress consoleProgress = new ConsoleProgress();
        consoleProgress.run();
    }

    @Override
    public void run() {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("\r load: " + count++ + "\\");
                        System.out.println("\r load: " + count++ + "|");
                        System.out.println("\r load: " + count++ + "/");
                    }
                }
        );
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        thread.interrupt();
    }
}
