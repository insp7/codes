package leetcode;

// 215. Kth Largest Element in an Array
import java.util.Arrays;

public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
