package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест класса Person
 *  Методы `equals` и `hashCode` (Условие равенства: все поля `name`, `city`, `age` должны совпадать, `name` и `city` без учета регистра).
 *  Класс Person должен реализовывать интерфейс `Comparable<Person>`, который обеспечивает следующий порядок:
 *  - Сортировка сначала по полю `city`, а затем по полю `name`.
 *  - Поля `city`, `name` отличны от `null`.
 */
class PersonTest {

    @Test
    void compareTo() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Алешин", "москва", 31));
        personList.add(new Person("Иванов", "анадырь", 32));
        personList.add(new Person("Иванов", "Москва", 33));
        personList.add(new Person("Васильев", "Анадырь", 34));
        assertEquals("Person{name='Алешин', city='москва', age=31}", personList.get(0).toString());
        assertEquals("Person{name='Васильев', city='Анадырь', age=34}", personList.get(3).toString());
        personList.sort(Person::compareTo);
        assertEquals("Person{name='Васильев', city='Анадырь', age=34}", personList.get(0).toString());
        assertEquals("Person{name='Иванов', city='Москва', age=33}", personList.get(3).toString());
    }
    @Test
    void testEquals() {
        Person person1 = new Person("иванов", "Москва", 30);
        Person person2 = new Person("Иванов", "москва", 30);
        Person person3 = new Person("Петров", "москва", 30);
        Person person4 = new Person("Иванов", "масква", 30);
        Person person5 = new Person("иванов", "Москва", 31);
        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person1));

        assertFalse(person1.equals(person3));
        assertFalse(person1.equals(person4));
        assertFalse(person1.equals(person5));
    }

    @Test
    void testHashCode() {
        Person person1 = new Person("иванов", "Москва", 30);
        Person person2 = new Person("Иванов", "москва", 30);
        Person person3 = new Person("Петров", "москва", 30);
        Person person4 = new Person("Иванов", "масква", 30);
        Person person5 = new Person("иванов", "Москва", 31);
        assertEquals(148662975,person1.hashCode());
        assertEquals(148662975,person2.hashCode());
        assertEquals(person1.hashCode(),person2.hashCode());

        assertNotEquals(person1.hashCode(),person3.hashCode());
        assertNotEquals(person2.hashCode(),person4.hashCode());
        assertNotEquals(person1.hashCode(),person5.hashCode());
    }

    @Test
    void testToString() {
        Person person1 = new Person("Иванов", "Москва", 30);
        assertEquals("Person{name='Иванов', city='Москва', age=30}", person1.toString());
    }
}