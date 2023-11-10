package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel Service.
 */
public class TravelService {
    private final static int EARTH_RADIUS = 6372795;
    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cities.contains(cityInfo))
            throw new IllegalArgumentException(String.format("Город %s уже есть в списке", cityInfo.getName()));
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        cities.remove(getCityInfo(cityName));
    }

    private CityInfo getCityInfo(String cityName) {
        CityInfo cityInfo = cities.stream().filter(c -> c.getName().equalsIgnoreCase(cityName)).findFirst().orElse(null);
        if (cityInfo == null)
            throw new IllegalArgumentException(String.format("Город %s отсутствует в списке", cityName));
        return cityInfo;
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(CityInfo::getName).toList();
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        CityInfo srcCityInfo = getCityInfo(srcCityName);
        CityInfo destCityInfo = getCityInfo(destCityName);
        double cl1 = Math.cos(srcCityInfo.getPosition().getLatitude());
        double cl2 = Math.cos(destCityInfo.getPosition().getLatitude());
        double sl1 = Math.sin(srcCityInfo.getPosition().getLatitude());
        double sl2 = Math.sin(destCityInfo.getPosition().getLatitude());
        double delta = destCityInfo.getPosition().getLongitude() - srcCityInfo.getPosition().getLongitude();
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

// вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;

        return (int) Math.round(Math.atan2(y, x) * EARTH_RADIUS / 1000);
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        return cities.stream()
                .filter(cityInfo -> !cityInfo.getName().equalsIgnoreCase(cityName))
                .filter(cityInfo -> getDistance(cityName, cityInfo.getName()) <= radius)
                .map(CityInfo::getName).toList();
    }

    /**
     * Преобразование объекта в строку
     * @return строка
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TravelService{");
        sb.append("cities=").append(cities.toString());
        sb.append('}');
        return sb.toString();
    }
}
