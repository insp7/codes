package codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CQ1 {
    public static List<Integer> list = new ArrayList<>();

    public static int solution(int A, int B, int C, int D) {
        String s = "" + A + B + C + D; // convert to string
        HashSet<Integer> set = new HashSet<>(); // need hashset to remove duplicates

        list = getPermutations(s); // get all the permutations in an Integer list
        for(int element : list) {
            int firstTwoDigits = element / 100;
            if(firstTwoDigits >= 0 && firstTwoDigits <= 23) {
                int lastTwoDigits = element % 100;
                if(lastTwoDigits >= 0 && lastTwoDigits <= 59) {
                    set.add(element); // add elements to set to keep a track of DISTINCT elements
                }
            }
        }
        return set.size();
    }

    public static List<Integer> getPermutations(String s) {
        if(s == null || s.length() == 0)
            return list;
        generatePermutations("", s);
        return list;
    }

    public static void generatePermutations(String fixed, String remaining) {
        if(remaining.length() == 0) {
            list.add((Integer.parseInt(fixed)));
            return;
        }
        for(int i = 0; i < remaining.length(); i++)
            generatePermutations(fixed + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1));
    }

    public static void main(String[] args) {
        int answer = solution(2, 2, 1, 1);
        System.out.println(answer);
    }
}
