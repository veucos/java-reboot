package ru.sberbank.edu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * Кастомный массив
 *
 * @param <T> - тип элементов массива
 */
public class CustomArrayImpl<T> implements CustomArray<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private T[] items;
    private int size;

    /**
     * Конструктор без параметров.
     * Начальный размер массива элементов DEFAULT_CAPACITY
     */
    public CustomArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор
     *
     * @param initialCapacity - размер массива элементов
     */
    public CustomArrayImpl(int initialCapacity) {
        T emptyItem = (T) new Object();
        this.items = (T[]) Array.newInstance(emptyItem.getClass(), initialCapacity);
    }

    /**
     * Размер массива (добавленные элементы), не путать с емкостью
     *
     * @return размер массива
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Проверка, что массив пустой
     *
     * @return true - если массив пустой
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Добавление еденичного элемента
     *
     * @param item элемент массива
     * @return true - если выполнено удачно
     */
    @Override
    public boolean add(T item) {
        if (items.length == size)
            ensureCapacity(size + 1);
        items[size] = item;
        ++size;
        return true;
    }

    /**
     * Добавления массива элементов
     *
     * @param items - массив элементов
     * @return true - если выполнено удачно
     */
    @Override
    public boolean addAll(T[] items) {
        return addAll(size, items);
    }

    /**
     * Добавление коллекции элементов
     *
     * @param items - коллекция элементы
     * @return true - если удачно
     */
    @Override
    public boolean addAll(Collection<T> items) {
        return addAll((T[]) items.toArray());
    }

    /**
     * Добавление массива элементов по индексу
     *
     * @param index - index
     * @param items - массив элементов
     * @return true - если удачно
     */
    @Override
    public boolean addAll(int index, T[] items) {
        if (items.length == 0)
            return false;
        if (index > size)
            return false;
        if (index < 0)
            return false;
        if (this.items.length < items.length + size)
            ensureCapacity(items.length + size - this.items.length);
        System.arraycopy(this.items, index, this.items, index + items.length, size - index);
        System.arraycopy(items, 0, this.items, index, items.length);
        size += items.length;
        return true;
    }

    /**
     * Выборка элемента по индексу
     *
     * @param index - index
     * @return элемент по индексу, null если не найден
     */
    @Override
    public T get(int index) {
        return this.items[index];
    }

    /**
     * Модификация элемента по индексу
     *
     * @param index - index
     * @param item - элемент
     * @return старый элемент по индексу
     */
    @Override
    public T set(int index, T item) {
        if (size < index)
            return null;
        T prev = get(index);
        this.items[index] = item;
        return prev;
    }

    /**
     * Удаление элемента по индексу
     * @param index - index
     */
    @Override
    public void remove(int index) {
        if (size <= index)
            return;
        System.arraycopy(this.items, index + 1, this.items, index, size - index - 1);
        size--;
        ensureCapacity(size);
    }

    /**
     * Удаление элемента по элементу
     * @param item - item
     * @return true - если удачно
     */
    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index < 0) return false;
        remove(index);
        return true;
    }

    /**
     * Содержится ли элемент в массиве
     * @param item - item
     * @return true - если содержится
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    /**
     * Индекс элемента в массиве
     * @param item - item
     * @return - индекс элемента, -1 если не найдено
     */
    @Override
    public int indexOf(T item) {
        return Arrays.asList(this.items).indexOf(item);
    }

    /**
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount <= 0)
            return;
        this.items = Arrays.copyOf(this.items, this.items.length + newElementsCount);
    }

    /**
     * Емкость массива
     * @return емкость массива
     */
    @Override
    public int getCapacity() {
        return this.items.length;
    }

    /**
     * Обращение массива
     */
    @Override
    public void reverse() {
        for (int i = 0; i < this.size / 2; i++) {
            T temp = this.items[i];
            this.items[i] = this.items[this.size - 1 - i];
            this.items[this.size - 1 - i] = temp;
        }
    }

    /**
     * В массив объектов
     * @return массив объектов
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.items, size);
    }

    /**
     * В строку
     * @return строка
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(this.items, size));
    }
}
