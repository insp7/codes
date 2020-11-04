package codility;

import java.util.Stack;

public class Brackets {
    public static int solution(String s) {
        if(s.length() == 0) return 1;
        if(s.length() % 2 != 0) return 0;

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')') {
                if(stack.empty())
                    return 0;
            }
            if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            else if (s.charAt(i) == '}' && stack.peek() == '{')
                stack.pop();
            else if (s.charAt(i) == ')' && stack.peek() == '(')
                stack.pop();
            else if (s.charAt(i) == ']' && stack.peek() == '[')
                stack.pop();
        }

        if(stack.empty())
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        String s = "{[]{{{{}}}}()(){([{])}}";
        int x = solution(s);
        System.out.println(x);
    }
}
