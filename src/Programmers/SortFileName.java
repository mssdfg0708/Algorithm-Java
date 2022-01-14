package Programmers;

import java.util.*;

public class SortFileName {
    public String[] solution(String[] files) {

        // Comparator 를 이용한 Array 정렬
        Arrays.sort(files, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                String[] file1 = detach(s1);
                String[] file2 = detach(s2);
                int headValue = file1[0].compareTo(file2[0]);

                if(headValue == 0) {
                    int num1 = Integer.parseInt(file1[1]);
                    int num2 = Integer.parseInt(file2[1]);

                    return num1 - num2;
                }
                else {
                    return headValue;
                }
            }

            private String[] detach(String str) {
                StringBuilder head = new StringBuilder();
                StringBuilder number = new StringBuilder();
                StringBuilder tail = new StringBuilder();

                int idx = 0;
                for( ; idx < str.length() ; idx++) {
                    char ch = str.charAt(idx);
                    if(ch >= '0' && ch <= '9')
                        break;
                    head.append(ch);
                }

                for( ; idx < str.length() ; idx++) {
                    char ch = str.charAt(idx);
                    if(!(ch >= '0' && ch <= '9'))
                        break;
                    number.append(ch);
                }

                for( ; idx < str.length() ; idx++) {
                    char ch = str.charAt(idx);
                    tail.append(ch);
                }

                String[] file = {head.toString().toLowerCase(), number.toString(), tail.toString()};

                return file;
            }
        });

        return files;
    }
}

class SortFileNameMain {
    public static void main(String[] args) {
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};

        SortFileName sortFileName = new SortFileName();
        String[] answer = sortFileName.solution(files);
        System.out.println(Arrays.toString(answer));
    }
}