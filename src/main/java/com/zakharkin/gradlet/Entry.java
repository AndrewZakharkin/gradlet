package com.zakharkin.gradlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;

public class Entry {
    static Object o = new Object();
    static volatile boolean running = true;

    static class MyTask implements Runnable{
        private final int idx;

        public MyTask(int idx){
            this.idx = idx;
        }
        @Override
        public void run() {
            synchronized (o) {
                while (running) {
                    try {
                        o.wait();
                        System.out.println("Awaking " + idx);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[]args){
        System.out.println(System.getProperty("user.dir"));
        var cl = new FSClassLoader("out/");

        try {
            Class c = Class.forName("com.zakharkin.tst.TestClass", true, cl);
            System.out.println(c.getClassLoader());
            System.out.println(c);
            var tstO = c.getDeclaredConstructor().newInstance();
            Method tstM = c.getMethod("tst", String.class);
            tstM.invoke(tstO, "AAAAA");

            var execService = Executors.newFixedThreadPool(10);
            for(int i = 0; i < 10; ++i) {
                execService.submit(new MyTask(i));
            }

            for (int i = 0; i < 3; ++i) {
                System.out.println("Press Enter to wake");
                System.in.read();
                synchronized (o) {
                    o.notify();
                }
            }
            running = false;
            execService.shutdown();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
