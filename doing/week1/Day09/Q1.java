import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Student[] stus = new Student[5];

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.print("\n请输入学生" + (i + 1) + "的信息：\n学号：");
            int id = sc1.nextInt();
            System.out.print("姓名：");
            String name = sc2.nextLine();
            System.out.print("性别：");
            String gender = sc2.nextLine();
            System.out.print("年龄：");
            int age = sc1.nextInt();
            System.out.print("java课成绩：");
            int java = sc1.nextInt();

            stus[i] = new Student(id, name, gender, age, java);
        }
        sc1.close();
        sc2.close();

        // 输出所有学生的信息
        System.out.println("学号\t" + "姓名\t" + "性别\t" + "年龄\t" + "java课成绩");
        for (int i = 0; i < 5; i++) {
            stus[i].print();
        }

        // 计算 java 成绩
        int max = stus[0].java;
        int min = stus[0].java;
        int average = stus[0].java;
        for (int i = 1; i < 5; i++) {
            if (max < stus[i].java) {
                max = stus[i].java;
            }
            if (min > stus[i].java) {
                min = stus[i].java;
            }
            average += stus[i].java;
        }

        System.out.println("Java课平均成绩：" + (double)average / 5 + "\n最高分：" + max + "\n最低分：" + min);
    }
}

class Student {
    int id;
    String name;
    String gender;
    int age;
    int java;

    public Student(int id, String name, String gender, int age, int java) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.java = java;
    }

    public void print() {
        System.out.println(id + "\t" + name + "\t" + gender + "\t" + age + "\t" + java);
    }
}