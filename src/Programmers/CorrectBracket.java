package Programmers;

import java.util.Stack;

public class CorrectBracket {

    boolean solution(String string) {

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<string.length(); i++){
            if(string.charAt(i) == '(')
                stack.push('(');
            else{
                if(stack.isEmpty())
                    return false;
                else
                    stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

class CorrectBracketMain {
    public static void main(String[] args) {
        String s = ")()(";
        
        CorrectBracket correctBracket = new CorrectBracket();
        boolean answer = correctBracket.solution(s);
        System.out.println("answer = " + answer);
    }
}
