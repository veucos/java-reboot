package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование методов класса CustomArrayImpl
 */
class CustomArrayImplTest {

    @Test
    void size() {
        CustomArrayImpl<Integer> arr = new CustomArrayImpl<>();
        assertEquals(0, arr.size());
        arr.add(1);
        assertEquals(1, arr.size());
    }

    @Test
    void isEmpty() {
        CustomArrayImpl<Integer> arr = new CustomArrayImpl<>();
        assertTrue(arr.isEmpty());
        arr.add(1);
        assertFalse(arr.isEmpty());
    }

    @Test
    void add() {
        CustomArrayImpl<Integer> arr = new CustomArrayImpl<>();
        arr.add(1);
        assertEquals(1, arr.get(0));
    }

    @Test
    void addAll() {
        CustomArrayImpl<String> arr = generateTestArr();
        String[] strings = new String[]{"D", "E", "F"};
        arr.addAll(strings);
        assertEquals(6, arr.size());
        assertEquals("E", arr.get(4));
    }

    @Test
    void testAddAll() {
        CustomArrayImpl<String> arr = generateTestArr();
        List<String> stringList = new ArrayList<>(Arrays.asList("D", "E", "F"));
        arr.addAll(stringList);
        assertEquals(6, arr.size());
        assertEquals("E", arr.get(4));
    }

    @Test
    void testAddAll1() {
        CustomArrayImpl<String> arr = generateTestArr();
        String[] strings = new String[]{"D", "E", "F"};
        assertFalse(arr.addAll(2, new String[]{}));
        assertFalse(arr.addAll(5, strings));
        assertFalse(arr.addAll(-1, strings));
        assertTrue(arr.addAll(2, strings));
        assertEquals("[A, B, D, E, F, C]",arr.toString());

    }

    @Test
    void get() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals("C", arr.get(2));
    }

    @Test
    void set() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals("C",arr.set(2,"D"));
        assertEquals("D", arr.get(2));
    }

    @Test
    void remove() {
        CustomArrayImpl<String> arr = generateTestArr();
        arr.remove(1);
        assertEquals("[A, C]",arr.toString());
    }

    @Test
    void testRemove() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertFalse(arr.remove("D"));
        assertTrue(arr.remove("A"));
        assertEquals("[B, C]",arr.toString());
    }

    @Test
    void contains() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertTrue(arr.contains("A"));
        assertTrue(arr.contains("B"));
        assertTrue(arr.contains("C"));
        assertFalse(arr.contains("D"));
    }

    @Test
    void indexOf() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals(0, arr.indexOf("A"));
        assertEquals(1, arr.indexOf("B"));
        assertEquals(2, arr.indexOf("C"));
        assertEquals(-1, arr.indexOf("D"));
    }

    @Test
    void ensureCapacity() {
        CustomArrayImpl<String> arr = generateTestArr();
        arr.ensureCapacity(1);
        assertEquals(6, arr.getCapacity());
    }

    @Test
    void getCapacity() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals(5, arr.getCapacity());
    }

    @Test
    void reverse() {
        CustomArrayImpl<String> arr = generateTestArr();
        arr.reverse();
        assertEquals("[C, B, A]",arr.toString());
    }

    @Test
    void toArray() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals("[A, B, C]",Arrays.toString(arr.toArray()));
    }

    @Test
    void testToString() {
        CustomArrayImpl<String> arr = generateTestArr();
        assertEquals("[A, B, C]",arr.toString());
    }

    CustomArrayImpl<String> generateTestArr(){
        CustomArrayImpl<String> arr = new CustomArrayImpl<>();
        String[] strings = new String[]{"A", "B", "C"};
        arr.addAll(strings);
        return arr;
    }
}