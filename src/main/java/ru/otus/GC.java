package ru.otus;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;

public class GC {
    static final Set<String> YOUNG_GC = new HashSet<String>(3);
    static final Set<String> OLD_GC = new HashSet<String>(3);


    static {
        YOUNG_GC.add("PS Scavenge");
        YOUNG_GC.add("ParNew");
        YOUNG_GC.add("G1 Young Generation");

        OLD_GC.add("PS MarkSweep");
        OLD_GC.add("ConcurrentMarkSweep");
        OLD_GC.add("G1 Old Generation");
    }

    public static void printGCMetrics() {
        List<GarbageCollectorMXBean> mxBeans
                = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gc : mxBeans) {
            long count = gc.getCollectionCount();
            if (count >= 0) {
                if (YOUNG_GC.contains(gc.getName())) {
                    System.out.print("MinorGC -> Count: " + count);
                    System.out.print(", Time (ms): " + gc.getCollectionTime());
                } else if (OLD_GC.contains(gc.getName())) {
                    System.out.print(", MajorGC -> Count: " + count);
                    System.out.print(", Time (ms): " + gc.getCollectionTime());
                    System.out.println();
                }
            }
        }
    }

}
