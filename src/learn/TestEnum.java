package learn;

/**
 * @author 龚秀峰
 * @version 1.0
 * @date 2020/10/31 22:09
 */
public class TestEnum {
    public static void main(String[] args) {
        A first = A.valueOf("First");
        A sec = A.valueOf(A.class, "Sec");
        System.out.println(sec);
        System.out.println(first);
    }
}
enum A{
    First("first"),Sec("the sec"),Thr("the thr");
    private String value;
    A(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
