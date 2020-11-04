package codility;

public class FrogJmp {

    public static int solution(int start, int end, int distance) {
        return ((end - start) % distance == 0 ? (end - start)/distance : ((end - start)/distance) + 1);
    }

    public static void main(String[] args) {
        int steps = solution(10, 85, 30);
        System.out.println(steps);
    }

}
