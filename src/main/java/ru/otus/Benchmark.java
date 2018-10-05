package ru.otus;

import java.util.*;

public class Benchmark {
    private static void run() {
        List<String> list = new LinkedList<>();
        int size = 100_000;
        while (true) {
            for (int i = 0; i < size; i++) {
                list.add(new String(new char[0]));
                if (i % 100 == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            list.subList(0, size / 2).clear();
            GC.printGCMetrics();
            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void pringGCLogs() {
        Thread thread = new Thread(Benchmark::run);
        thread.start();
    }

}
