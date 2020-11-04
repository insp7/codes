package codility;

import java.util.HashMap;

public class OddOccurrencesInArray {

    /**
     * This function uses HashMap to solve the problem.
     * @param a Specifies the input array.
     * @return The unpaired element is returned from the array.
     */
    public static int getUnpairedElement(int[] a) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int unpairedElement = 0;

        for(int element : a) { // Loop through the array
            if(hashMap.containsKey(element)) // if element already exists in the hashmap
                hashMap.remove(element); // remove the element
            else // if element does not exist in the hashmap,
                hashMap.put(element, element); // Store element in hashmap as key & value. ex. {9: 9, 3: 3 ...}
        }
        // At this step, only the single unpaired element will be remaining in the hashmap
        for(int key : hashMap.keySet()) unpairedElement = hashMap.get(key); // get that element
        return unpairedElement;
    }

    public static void main(String[] args) {
        int[] a = new int[]{9, 3, 9, 3, 9, 7, 9};
        int value = getUnpairedElement(a);
        System.out.println(value);
    }
}


