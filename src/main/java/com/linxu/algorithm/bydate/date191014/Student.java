package com.linxu.algorithm.bydate.date191014;

import java.util.*;

/**
 * @author linxu
 * @date 2019/10/16
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Student {
    private String name;
    private int chinese;
    private int math;
    private int english;
    private int count;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" ").append(chinese).append(" ").append(math).append(" ").append(english);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //Student[] students = new Student[10];
        List<Student> studentList = new ArrayList<>();
        //初始化，顺便算出总分，并且剔除不及格的人员
        String[] students = {
                "goudan2 60 80 80",
                "zhaowu 60 80 80 ",
                "zhangsan 60 80 80",
                "yatou 90 80 80",
                "goudan1 60 80 80",
                "gousheng 80 100 60",
                "hanmingmei 80 90 60",
                "liutao 80 100 60",
                "SimonYin 80 100 60",
                "lilei 80 100 60"
        };
        for (String s : students
                ) {
            String[] student = s.split(" ");
            int chinese = Integer.valueOf(student[1]);
            int math = Integer.valueOf(student[2]);
            int english = Integer.valueOf(student[3]);
            if (chinese >= 60 && math >= 60 && english >= 60) {
                Student newStudent = new Student();
                newStudent.count = chinese + math + english;
                newStudent.chinese = chinese;
                newStudent.math = math;
                newStudent.english = english;
                newStudent.name = student[0];
                studentList.add(newStudent);
            }
        }
        //sort name
        studentList.sort((o1, o2) -> {
            char[] chars1 = o1.name.toCharArray();
            char[] chars2 = o2.name.toCharArray();
            int i = 0;
            while (i < chars1.length && i < chars2.length) {
                if (chars1[i] > chars2[i]) {
                    return 1;
                } else if (chars1[i] < chars2[i]) {
                    return -1;
                } else {
                    i++;
                }
            }
            if (i == chars1.length) {  //o1到头
                return 1;
            }
            if (i == chars2.length) { //o2到头
                return -1;
            }
            return 0;
        });
        //sort english
        studentList.sort((o1, o2) -> {
            if (o1.english > o2.english) {
                return -1;
            } else if (o1.english < o2.english) {
                return 1;
            } else {
                return 0;
            }
        });
        //sort math
        studentList.sort((o1, o2) -> {
            if (o1.math > o2.math) {
                return -1;
            } else if (o1.math < o2.math) {
                return 1;
            } else {
                return 0;
            }
        });
        //sort chinese
        studentList.sort((o1, o2) -> {
            if (o1.chinese > o2.chinese) {
                return -1;
            } else if (o1.chinese < o2.chinese) {
                return 1;
            } else {
                return 0;
            }
        });
        //sort count
        studentList.sort((o1, o2) -> {
            if (o1.count > o2.count) {
                return -1;
            } else if (o1.count < o2.count) {
                return 1;
            } else {
                return 0;
            }
        });

        //output first round
        System.out.println("[First round name list]");
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("[Final name list]");
        //计数器
        int refCounter = 0;
        for (int i = 0; i < studentList.size(); i++) {

        }

    }

    /**
     * 排名完全相同的判断规则
     *
     * @param student1
     * @param student2
     * @return
     */
    private boolean equeals(Student student1, Student student2) {
        if (student1.count == student2.count) {
            if (student1.english == student2.english && student1.math == student2.math && student1.chinese == student2.chinese) {
                return true;
            }
        }
        return false;
    }
}
