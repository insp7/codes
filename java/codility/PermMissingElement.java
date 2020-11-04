package codility;

import java.util.HashMap;

public class PermMissingElement {
    public static int solution(int[] a) {
        if(a.length == 1) return a[0] == 1 ? 2 : 1; // Special case

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int missingElement = 1;
         // 1.... n+1  (1, 2, 3, 4, 5)
        for(int i = 1; i <= a.length + 1; i++)
            hashMap.put(i, i);
        // 2 , 3 , 1, 5
        for (int j : a)
            hashMap.remove(j);

        // single element
        for(int key : hashMap.keySet())
            missingElement = hashMap.get(key);
        return missingElement;
    }

    public static void main(String[] args) {
        int missEle = solution(new int[]{2, 3, 1, 5});
        System.out.println(missEle);
    }

}
