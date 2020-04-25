package week2.day17;

public class Solution2 {
    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();

        try {
            exceptionTest.getException1();
        } catch (Exception1 e) {
            e.printStackTrace();
        }

        try {
            exceptionTest.getException2();
        } catch (Exception2 e) {
            e.printStackTrace();
        }

        try {
            exceptionTest.getException3();
        } catch (Exception3 e) {
            e.printStackTrace();
        }
    }

}

class ExceptionTest {
    public void getException1() throws Exception1 {
        throw new Exception1("异常 1");
    }

    public void getException2() {
        throw new Exception2("异常 2");
    }
    public void getException3() throws Exception3 {
        throw new Exception3("异常 3");
    }
}

class Exception1 extends Exception {
    public Exception1(String msg) {
        super(msg);
    }
}

class Exception2 extends RuntimeException {
    public Exception2(String msg) {
        super(msg);
    }
}

class Exception3 extends Exception {
    public Exception3(String msg) {
        super(msg);
    }
}
