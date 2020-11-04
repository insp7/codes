package codility;

import java.util.HashSet;

public class Distinct {

    public static int solution(int[] a) {
        HashSet<Integer> hs = new HashSet<>();
        for (int element : a)
            hs.add(element);
        return hs.size();
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 1, 2, 3, 1};
        int output = solution(a);
        System.out.println(output);
    }
}
