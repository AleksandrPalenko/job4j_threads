package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }

    @Override
    public void run() {
        String[] symbols = new String[]{"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < symbols.length; i++) {
                    System.out.println("\r load " + symbols[i]);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}