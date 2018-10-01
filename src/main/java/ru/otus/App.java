package ru.otus;

import javax.management.*;
import java.lang.management.ManagementFactory;


public class App {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        //int size = 5 * 1000 * 10000;
        int size = 10000000*3;
        //int size = 50 * 1000 * 100; //for small dump

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        final Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.setValues();
    }
}
