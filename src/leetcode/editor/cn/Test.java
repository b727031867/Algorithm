package leetcode.editor.cn;

/**
 * @author 龚秀峰
 * @version 1.0
 * @date 2020/9/29 22:00
 */
@FunctionalInterface
public interface Test<T,R,C> {
    R test(T param1,C param2);
}

class MyClass implements Test<String,Long,Integer>{

    public static void main(String[] args) {
        //这里Double作为返回类型，Double、Long作为参数类型，通过拉姆达表达式传入不同的行为
        Test<Double,Double,Long> iTest = (param1,param2) -> param1 + param2 * param1;
        //不省略参数的类型
        Test<Double,Double,Long> iTest2 = (Double param1,Long param2) -> param1 + param2 * param1;
        //多行要执行的语句，依旧是返回Double类型的值
        Test<Double,Double,Long> iTest3 = (Double param1,Long param2) -> {
            double temp = 0D;
            temp += param1 + param2 * param1;
            return temp;
        };
        Double res = iTest.test(6D, 1L);
        System.out.println(res);
    }

    //传统实现接口的方式
    @Override
    public Long test(String param1, Integer param2) {
        return (long)(param1.length() + param2);
    }
}