package codility;

public class TapeEquilibrium {

    /*
     * 3 1 2 4 3
     *
     *
     * 3 4 6 10 13 => sum from left
     * 13 10 9 7 3 => sum form right
     * | 3 - 10 | = 7
     * | 4 - 9 | = 5
     * | 6 - 7 | = 1 (is min? true)
     * | 10 - 3 | = 7
     */

    public static int solution(int[] a) {
        int[] sumFromRight = new int[a.length];
        int[] sumFromLeft = new int[a.length];
        // since our range is in (-1000, 1000)
        // array with these 2 elements(1000, -1000) can produce 2000 as the minimum difference,
        // so set min diff by default to 2001
        int minDiff = 2001;

        sumFromLeft[0] = a[0];
        sumFromRight[a.length-1] = a[a.length-1];

        for(int i = 1; i < a.length; i++)
            sumFromLeft[i] = sumFromLeft[i-1] + a[i];

        for(int i = a.length - 2; i >= 0; i--)
            sumFromRight[i] = a[i] + sumFromRight[i+1];

        for(int i = 0; i < a.length - 1; i++) {
            int diff = sumFromLeft[i] - sumFromRight[i+1];

            if(diff < 0) diff = -(diff);
            if(diff < minDiff) minDiff = diff;
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int minDiff = solution(new int[]{3, 1, 2, 4, 3});
        System.out.println(minDiff);
    }
}
