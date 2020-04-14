public class Q3 {
    public static void main(String[] args) {
        MyStudent st = new MyStudent();
        st.print();
    }
}

class MyStudent {
    String name;
    int age;
    boolean isMale;
    int sno;

    // 构造方法
    public MyStudent() {
        this("未知", -1, true, -1);
    }
    public MyStudent(String name) {
        this(name, -1, true, -1);
    }
    public MyStudent(String name, int sno) {
        this(name, -1, true, sno);
    }
    public MyStudent(String name, int age, boolean isMale) {
        this(name, age, isMale, -1);
    }
    public MyStudent(String name, int age, boolean isMale, int sno) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.sno = sno;
    }

    // 成员方法
    public void print() {
        System.out.println("麻花疼" + "---" + 100 + "---" + "男" + "---" + 1000);
    }
}