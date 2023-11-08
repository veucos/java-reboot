package ru.sberbank.edu;

import java.util.Objects;

/**
 * Методы `equals` и `hashCode` (Условие равенства: все поля `name`, `city`, `age` должны совпадать, `name` и `city` без учета регистра).
 * Класс Person должен реализовывать интерфейс `Comparable<Person>`, который обеспечивает следующий порядок:
 * - Сортировка сначала по полю `city`, а затем по полю `name`.
 * - Поля `city`, `name` отличны от `null`.
 */
public class Person implements Comparable<Person> {
    private final String name;
    private final String city;
    private final int age;

    /**
     * Конструктор
     * @param name Имя
     * @param city Город
     * @param age Возраст
     */
    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    /**
     * Компаратор
     * @param person объект сравнения
     * `name` и `city` без учета регистра
     * @return результат сравнения
     */
    @Override
    public int compareTo(Person person) {
        return (this.city.equalsIgnoreCase(person.city)) ? this.name.compareToIgnoreCase(person.name) : this.city.compareToIgnoreCase(person.city);
    }

    /**
     * Определение эквивалентности объектов
     * `name` и `city` без учета регистра
     * @param o объект для определения эквивалентности
     * @return результат эквивалентности
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return name.equalsIgnoreCase(person.name) &&
                city.equalsIgnoreCase(person.city) &&
                age == person.age;
    }

    /**
     * Расчет хэшкода
     * `name` и `city` без учета регистра
     * @return хэшкод
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase().hashCode(), city.toLowerCase().hashCode(), age);
    }

    /**
     * Преобразование в строку
     * @return результирующая строка
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
