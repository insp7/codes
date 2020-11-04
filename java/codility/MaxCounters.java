package codility;

import java.util.Arrays;

public class MaxCounters {
    public static int[] naiveSolution(int n, int[] a) {
        int[] counters = new int[n];
        int maxCounter = 0;

        for (int element : a) {
            if (element == n + 1)
                Arrays.fill(counters, maxCounter);
            else {
                counters[element - 1]++;
                if (counters[element - 1] > maxCounter) maxCounter = counters[element - 1];
            }
            for (int counter : counters) System.out.print(counter + " ");
            System.out.println();
        }
        return counters;
    }

    public static int[] solution(int n, int[] a) {
        int currentMax = 0;
        int lastUpdate = 0; // represents the maxCounter value from the previous maxCounter operation
        int[] counters = new int[n];

        for (int currentValue : a) {
            if (currentValue == n + 1)
                lastUpdate = currentMax;
            else {
                int position = currentValue - 1;
                if (counters[position] < lastUpdate)
                    counters[position] = lastUpdate + 1;
                else
                    counters[position]++;

                if (counters[position] > currentMax) {
                    currentMax = counters[position];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (counters[i] < lastUpdate)
                counters[i] = lastUpdate;
        }
        return counters;
    }

    public static void main(String[] args) {
        int[] counters = solution(5, new int[]{ 3, 4, 4, 6, 1, 4, 4 });
        for(int counter : counters) System.out.print(counter + " ");
    }
}