package codility;

import java.util.HashSet;

public class FrogRiverOne {

    public static int solution(int search, int[] a) {
        HashSet<Integer> positions = new HashSet<>();

        for (int i = 1; i <= search; i++)
            positions.add(i);

        for(int i = 0; i < a.length; i++) {
            positions.remove(a[i]);
            if(positions.isEmpty()) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int output = solution(2, new int[] { 2, 2, 2, 2, 2 });
        System.out.println(output);
    }
}
