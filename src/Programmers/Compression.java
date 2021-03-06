package Programmers;

import java.util.*;

public class Compression {

    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        List<String> dict = new ArrayList<>();

        for(int i=0 ; i<msg.length() ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(i));
            while(i+1<msg.length() && dict.contains(sb.toString()+msg.charAt(i+1))) {
                i += 1;
                sb.append(msg.charAt(i));
            }
            
            // 'A' - 'A' + 1 을 하면 1 이 출력된다
            // 'B' - 'B' + 1 을 하면 2 가 출력된다
            // 'Z' - 'Z' + 1 을 하면 26 이 출력된다
            if(sb.length()==1)
                list.add(msg.charAt(i)-'A'+1);
            else
                list.add(dict.indexOf(sb.toString())+27);

            if(i<msg.length()-1)
                dict.add(sb.toString()+msg.charAt(i+1));
        }

        int[] ret = new int[list.size()];
        for(int i=0; i<ret.length ; i++)
            ret[i] = list.get(i);

        return ret;
    }
}

class CompressionMain {
    public static void main(String[] args) {
        String msg = "KAKAO";

        Compression compression = new Compression();
        int[] answer = compression.solution(msg);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}
