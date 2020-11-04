package codility;

import java.util.HashSet;

public class FindLastDistinctElement {
    public static int solution(int[] a) {
        HashSet<Integer> hs = new HashSet<>();
        int distinctElementIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if(hs.contains(a[i]))
                continue;
            hs.add(a[i]);
            distinctElementIndex = i;
        }
        return distinctElementIndex;
    }

    public static void main(String[] args) {
        int[] x = new int[]{2, 2, 1, 0, 1};
        int ans = solution(x);
        System.out.println(ans);
    }
}
