package ru.sberbank.edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Конструктор
     * parse and set latitude and longitude in radian
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        latitude = Math.toRadians(convertStringToGradus(latitudeGradus));
        longitude = Math.toRadians(convertStringToGradus(longitudeGradus));
    }

    /**
     *
     * @param str градусы строкой с минутами, секундами
     *            Possible values: 55, 55(45'07''), 59(57'00'')
     * @return градусы числом double
     */
    private double convertStringToGradus(String str) {
        final Matcher matcher = Pattern.compile("(-?\\d+)$|(-?\\d+)\\s?\\(\\s?(\\d+)\\s?\\'\\s?(\\d{1,}\\.?\\,?\\d{0,}?)\\'\\'\\)$",
                Pattern.MULTILINE).matcher(str);
        if (!matcher.find())
            throw new NumberFormatException("Ошибка преобразования строки в градусы");
        return (matcher.group(1) == null)
                ? Double.parseDouble(matcher.group(2)) + Double.parseDouble(matcher.group(3)) / 60 + Double.parseDouble(matcher.group(4)) / 3600
                : Double.parseDouble(matcher.group(1));
    }

    /**
     * Широта
     * @return широта
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Долгота
     * @return долгота
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Преобразование объекта в строку
     * @return строка
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GeoPosition{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Сравнение
     * @param o объект
     * @return результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPosition that = (GeoPosition) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        return Double.compare(that.longitude, longitude) == 0;
    }

    /**
     * hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}