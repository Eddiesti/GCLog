package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;

    public void setValues() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Starting the loop");
                int local = size;
                Object[] array = new Object[local];
                System.out.println("Array of size: " + array.length + " created");
                for(int i = 0; i < local; i++){
                    array[i] = new Student("1","2",3);
                }
                System.out.println("Created " + local + " objects.");
                GC.printGCMetrics();
            }
        }, 0, 1);
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
