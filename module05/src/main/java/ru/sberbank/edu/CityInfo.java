package ru.sberbank.edu;

import java.util.Objects;

/**
 * City info
 */
public class CityInfo {

    private String name;
    private GeoPosition position;

    /**
     * Конструктор
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    /**
     * Getter имени города
     * @return имя города
     */
    public String getName() {
        return name;
    }

    /**
     * Getter позиции
     * @return позиция города
     */
    public GeoPosition getPosition() {
        return position;
    }

    /**
     * Преобразование объекта в строку
     * @return строка
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CityInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", position=").append(position.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Сравнение без учета регистра
     * @param o объект сравнения
     * @return результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityInfo cityInfo = (CityInfo) o;

        if (!name.equalsIgnoreCase(cityInfo.name)) return false;
        return Objects.equals(position, cityInfo.position);
    }

    /**
     * hashCode без учета регистра имени города
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.toLowerCase().hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}