package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravelServiceTest {
    TravelService travelService;

    @BeforeEach
    void beforeEach() {
        travelService = new TravelService();
        travelService.add(new CityInfo("Москва", new GeoPosition("55(44'24'')", "37(36'36'')")));
        travelService.add(new CityInfo("Владивосток", new GeoPosition("43(07'00'')", "131(54'00'')")));
        travelService.add(new CityInfo("Калининград", new GeoPosition("54(43'12'')", "20(30'00'')")));
    }
    @Test
    void add() {
       assertEquals("[Москва, Владивосток, Калининград]", travelService.citiesNames().toString());
        travelService.add(new CityInfo("Санкт-Петербург", new GeoPosition("59(56'19'')", "30(18'50'')")));
        assertEquals("[Москва, Владивосток, Калининград, Санкт-Петербург]", travelService.citiesNames().toString());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> travelService.add(new CityInfo("Москва", new GeoPosition("55(44'24'')", "37(36'36'')"))));
        assertEquals("Город Москва уже есть в списке", exception.getMessage());
    }

    @Test
    void remove() {
        assertEquals("[Москва, Владивосток, Калининград]", travelService.citiesNames().toString());
        travelService.remove("Калининград");
        assertEquals("[Москва, Владивосток]", travelService.citiesNames().toString());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> travelService.remove("Калининград"));
        assertEquals("Город Калининград отсутствует в списке", exception.getMessage());

    }

    @Test
    void citiesNames() {
        assertEquals("[Москва, Владивосток, Калининград]", travelService.citiesNames().toString());
    }

    @Test
    void getDistance() {
        assertEquals(6419, travelService.getDistance("Москва", "Владивосток"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> travelService.getDistance("Москва", "Сочи"));
        assertEquals("Город Сочи отсутствует в списке", exception.getMessage());
    }

    @Test
    void getCitiesNear() {
        assertEquals("[Калининград]",travelService.getCitiesNear("Москва",2000).toString());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> travelService.getCitiesNear("Сочи",2000));
        assertEquals("Город Сочи отсутствует в списке", exception.getMessage());
    }

    @Test
    void testToString() {
        assertEquals("TravelService{cities=[CityInfo{name='Москва', position=GeoPosition{latitude=0.9728465250616394, longitude=0.6564183316750674}}, CityInfo{name='Владивосток', position=GeoPosition{latitude=0.7525277958182217, longitude=2.302089283380521}}, CityInfo{name='Калининград', position=GeoPosition{latitude=0.9550441666912971, longitude=0.35779249665883756}}]}",
                travelService.toString());
    }
}