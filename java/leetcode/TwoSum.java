import java.util.Map;
import java.util.HashMap;

/**
 * Question: https://leetcode.com/problems/two-sum/
 * @author AniketKonkar
 */
class TwoSum {

	/**
	 * Method to perform simple linear search in a given array.
	 * @param  array      The input array.
	 * @param  key        Value to be searched.
	 * @param  startIndex Specifies the index to start searching from.
	 * @param  endIndex   Specifies the index at which the index stops.
	 * @return            The index of the element if found, else -1.
	 */
	public static int linearSearch(int[] array, int key, int startIndex, int endIndex) {
		if(startIndex >= 0 && endIndex <= array.length - 1) {
			for(int index = startIndex; index <= endIndex; index++) 
				if(array[index] == key) return index;		
		}
		return -1;
	}

	// Naive approach which uses linear search.
	public static int[] twoSum(int[] nums, int target) {
		for(int i = 0, index; i < nums.length; i++) {
			if((index = linearSearch(nums, target - nums[i], i + 1, nums.length - 1)) != -1) 
				return new int[] { i, index };
		}
		return new int[] { -1, -1 };
	}

	/**
	 * Method to get indices of 2 numbers from an array whose sum is equivalent to the specified target.
	 * @param  nums   The input array.
	 * @param  target The input target.
	 * @return        The indices of 2 numbers such that they add up to the specified target if exists, else { -1, -1 }.
	 */
	public static int[] twoSumUsingHashMap(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int index = 0; index < nums.length; map.put(nums[index], index++)) 
			if(map.containsKey(target - nums[index]))
				return new int[] { map.get(target - nums[index]), index };
		return new int[] { -1, -1 };
	}

	public static void main(String[] args) throws Exception { 
		int[] array = { -1, -2, -3, -4, -5 };

		int[] indices = twoSum(array, -8);
	
		for(int index : indices) 
			System.out.println(index);
	}
}