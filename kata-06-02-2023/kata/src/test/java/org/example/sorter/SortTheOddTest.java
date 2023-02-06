package org.example.sorter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;


class SortTheOddTest {

    private static Stream<Arguments> params() {
        return Stream.of(
                of(new int[]{}, new int[]{}),
                of(new int[]{1}, new int[]{1}),
                of(new int[]{4, 2}, new int[]{4, 2}),
                of(new int[]{2, 1}, new int[]{2, 1}),
                of(new int[]{1, 7}, new int[]{7, 1}),
                of(new int[]{1, 7}, new int[]{1, 7}),
                of(new int[]{1, 2, 3}, new int[]{3, 2, 1}),
                of(new int[]{8, 6, 1}, new int[]{8, 6, 1}),
                of(new int[]{3, 8, 6, 5, 4}, new int[]{5, 8, 6, 3, 4}),
                of(new int[]{1, 8, 3, 6, 5, 4, 7, 2, 9, 0}, new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})

        );
    }

    @ParameterizedTest
    @MethodSource("params")
    void sort(int[] expected, int[] arrayToSort) {
        int[] result = OddNumberSorter.sort(arrayToSort);
        assertArrayEquals(expected, result);
    }
}