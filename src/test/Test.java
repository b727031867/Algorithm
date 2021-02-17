package test;

import java.util.ArrayList;
import java.util.List;

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
        {
            byte[] data = new byte[1024 * 1024 * 8];
        }
        String a = "";
        System.gc();
    }

//    public static void main(String[] args) {
//        Test<Fruit2> test = new Test<>();
//        test.currentClass(new Fruit2());
//        List<OOMObj> oomObjs = new ArrayList<>();
//        while (true){
//            oomObjs.add(new OOMObj());
//        }
//    }
}

class Plant {
}

interface A {
}

interface B extends A {
}

class Fruit2 implements B {
}

class Fruit extends Plant implements B {
    void test1() {
        System.out.println("test1");
    }
}