package ru.sberbank.edu;

import java.util.Comparator;

/**
 * Класс `CustomDigitComparator` определяет следующий порядок:
 * - Сначала четные числа, затем нечетные
 * - На вход подаются числа, отличные от null.
 */
public class CustomDigitComparator implements Comparator<Integer> {
    /**
     * @param i1 - первое число
     * @param i2 - второе число
     * @return результат сравнения
     */
    @Override
    public int compare(Integer i1, Integer i2) {
        if (i1 == null || i2 == null) return 0;
        if (i1 % 2 == 0 && i2 % 2 != 0) return -1;
        if (i1 % 2 != 0 && i2 % 2 == 0) return 1;
        return 0;
    }

    /**
     * @return компаратор, сортирующий в обратном порядке
     */
    @Override
    public Comparator<Integer> reversed() {
        return Comparator.super.reversed();
    }
}
