package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест компаратора
 * - Сначала четные числа, затем нечетные
 * - На вход подаются числа, отличные от null.
 */
class CustomDigitComparatorTest {

    @Test
    void compare() {
        List<Integer> ints = Arrays.asList(5, 7, 8, 3, 1, 2, 4);
        assertEquals("[5, 7, 8, 3, 1, 2, 4]", ints.toString());
        ints.sort(new CustomDigitComparator());
        assertEquals("[8, 2, 4, 5, 7, 3, 1]", ints.toString());
        ints.sort(new CustomDigitComparator().reversed());
    }

    @Test
    void reversed() {
        List<Integer> ints = Arrays.asList(5, 7, 8, 3, 1, 2, 4);
        assertEquals("[5, 7, 8, 3, 1, 2, 4]", ints.toString());
        ints.sort(new CustomDigitComparator().reversed());
        assertEquals("[5, 7, 3, 1, 8, 2, 4]", ints.toString());
    }
}