public class Q2 {
    public static void main(String[] args) {
        
    }
}

class TestFinal {
    final int finalField1 = 1;
    final int finalField2;
    final int finalField3;

    {
        finalField2 = 2;
    }

    public FinalVariable(int a) {
        finalField3 = a;
    }
}