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

    static long minorCount = 0;
    static long minorTime = 0;
    static long majorCount = 0;
    static long majorTime = 0;
    static long unknownCount = 0;
    static long unknownTime = 0;

    public static void printGCMetrics()   {


        List<GarbageCollectorMXBean> mxBeans
                = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : mxBeans) {
            long count = gc.getCollectionCount();
            if (count >= 0) {
                if (YOUNG_GC.contains(gc.getName())) {
                    minorCount += count;
                    minorTime += gc.getCollectionTime();
                } else if (OLD_GC.contains(gc.getName())) {
                    majorCount += count;
                    majorTime += gc.getCollectionTime();
                } else {
                    unknownCount += count;
                    unknownTime += gc.getCollectionTime();
                }
            }
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("MinorGC -> Count: ").append(minorCount)
                .append(", Time (ms): ").append(minorTime)
                .append(", MajorGC -> Count: ").append(majorCount)
                .append(", Time (ms): ").append(majorTime);

        if (unknownCount > 0) {
            sb.append(", UnknownGC -> Count: ").append(unknownCount)
                    .append(", Time (ms): ").append(unknownTime);
        }
        System.out.println(sb);
    }

}
