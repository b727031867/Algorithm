package test;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author GXF
 * @version 0.1.0
 * @date 2020-10-28 08:56
 * @since 0.1.0
 **/
public class Test<B> {
    static class OOMObj {

    }

    private void currentClass(B t) {
        System.out.println("test" + " " + t.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        testStringBuilder();
    }

    private static void testStringBuilder(){
        StringBuilder builder = new StringBuilder("赣");
        String 赣 = new StringBuilder("赣").substring(0, 0);
        System.out.println(赣);
        int length = builder.length();
        System.out.println(length);
    }

//    public static void main(String[] args) {
//        String a = "aaa";
//        String n = "bb";
//        AtomicReference<String> strRe = new AtomicReference<>(a);
//        String s = strRe.get();
//        strRe.set(n);
//        strRe.compareAndSet(a,s);
//
//        int[] ac = new int[660];
//        Arrays.asList(ac);
//
//        Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        synchronized (this){
//                            System.out.println("同步等待");
//                            wait(100);
//                            System.out.println("等待完毕");
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
//        try {
//            System.out.println("等待子线程");
//            thread.join();
//            System.out.println("等待子线程结束");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        {
////            byte[] data = new byte[1024 * 1024 * 8];
////        }
////        String a = "";
////        System.gc();
//    }

//    public static void main(String[] args) {
//        Test<Fruit2> test = new Test<>();
//        test.currentClass(new Fruit2());
//        List<OOMObj> oomObjs = new ArrayList<>();
//        while (true){
//            oomObjs.add(new OOMObj());
//        }
//    }
}

//class Plant {
//}
//
//interface A {
//}
//
//interface B extends A {
//}
//
//class Fruit2 implements B {
//}
//
//class Fruit extends Plant implements B {
//    void test1() {
//        System.out.println("test1");
//    }
//}