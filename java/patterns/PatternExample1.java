package patterns;

/**
 * Write a function that is able to generate the following (infinite) pattern:
 * 2 3 3 2 3 3 3 2 3 3 3 2 3 3 2 3 3 3 2 3 3 3 2 3 3 3 2 3 3 2...
 *
 * Write the most optimum code for the generation of such an (infinite) sequence of positive integers x & y
 * Another example, if x = 5, y = 6, the sequence would be:
 * 5 6 6 6 6 6 5 6 6 6 6 6 6 5 6 6 6 6 6 6 5 6 6 6 6 6 6 5...
 *
 * For more understanding on the question, refer the screenshot in this directory
 */

public class PatternExample1 {

    public static int[] generatePattern(int x, int y) {
        int[] pattern = new int[16]; // generating till first 50 numbers
        int i = 0, counter = x;
        boolean flag = true;

        while (i < pattern.length) {
            if (flag) { // O(1)
                pattern[i] = x;
                i += y;
                flag = false;
                if (i < pattern.length)
                    pattern[i] = x;
            } else {
                int z = 1;
                while (z <= counter) { // O([counter*(counter-1)/2] - [x*(x-1)/2])
                    i = i + y + 1;
                    if (i < pattern.length)
                        pattern[i] = x;
                    z++;
                }
                flag = true;
                counter++;
            }
        }

        for (int index = 0; index < pattern.length; index++) { // O(n)
            if (pattern[index] == 0)
                pattern[index] = y;
        }
        return pattern;
    }

    public static void main(String[] args) {
        int[] pattern = generatePattern(1, 2);
        for (int p : pattern)
            System.out.print(p + " ");
    }
}

