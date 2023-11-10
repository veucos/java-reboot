package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест CityInfo
 */
class CityInfoTest {

    CityInfo cityInfo;

    @BeforeEach
    void beforeEach() {
        cityInfo = new CityInfo("Москва", new GeoPosition("55(44'24'')", "37(36'36'')"));
    }

    @Test
    void getName() {
        assertEquals("Москва", cityInfo.getName());
    }

    @Test
    void getPosition() {
        assertEquals("GeoPosition{latitude=0.9728465250616394, longitude=0.6564183316750674}",
                cityInfo.getPosition().toString());
    }

    @Test
    void testToString() {
        assertEquals("CityInfo{name='Москва', position=GeoPosition{latitude=0.9728465250616394, longitude=0.6564183316750674}}",
                cityInfo.toString());
    }

    @Test
    void testEquals() {
        CityInfo cityInfo1 = new CityInfo("Москва", new GeoPosition("55(44'24'')", "37(36'36'')"));
        CityInfo cityInfo2 = new CityInfo("Владивосток", new GeoPosition("43(07'00'')", "131(54'00'')"));
        assertEquals(true, cityInfo.equals(cityInfo1));
        assertEquals(false, cityInfo.equals(cityInfo2));
    }

    @Test
    void testHashCode() {
        assertEquals(1476954675, cityInfo.hashCode());
    }
}