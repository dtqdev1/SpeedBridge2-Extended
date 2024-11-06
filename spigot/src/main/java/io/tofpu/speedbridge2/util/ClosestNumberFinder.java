package io.tofpu.speedbridge2.util;

import java.util.Collections;
import java.util.List;

public class ClosestNumberFinder {
    /**
     * Finds the closest number in the given list of numbers to the given number.
     *
     * @param number the number to find the closest match for
     * @param numbers the numbers to find the closest match in
     * @return the closest match, or null if no match exists
     */
    public static Integer findClosest(int number, List<Integer> numbers) {
        // binary search requires the numbers to be sorted
        Collections.sort(numbers);

        int index = Collections.binarySearch(numbers, number);
        if (index >= 0) {
            // exact match found
            return numbers.get(index);
        }

        // no exact match found; calculate the insertion point
        int insertionPoint = -index - 1;
        // check if insertion point is within bounds
        if (insertionPoint < numbers.size()) {
            return numbers.get(insertionPoint);
        }

        // If insertion point is out of bounds, return null (no such number exists)
        return null;
    }
}
