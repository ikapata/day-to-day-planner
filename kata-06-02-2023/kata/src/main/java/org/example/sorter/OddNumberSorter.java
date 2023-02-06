package org.example.sorter;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.stream.Collectors.toCollection;

public class OddNumberSorter {
    public static int[] sort(int[] numbers) {
        Queue<Integer> sortedOddNumbers = Arrays.stream(numbers)
                .filter(OddNumberSorter::isOdd)
                .sorted()
                .boxed()
                .collect(toCollection(LinkedBlockingQueue::new));
        return Arrays.stream(numbers).map(
                number ->
                        isOdd(number) ? sortedOddNumbers.poll() : number
        ).toArray();
    }

    private static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}

