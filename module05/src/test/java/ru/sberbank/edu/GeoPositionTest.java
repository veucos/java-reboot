package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тест класса GeoPosition
 */
class GeoPositionTest {
    GeoPosition position;

    @BeforeEach
    void beforeEach() {
        position = new GeoPosition("55(44'24'')", "37");
    }
    @Test
    void getLatitude() {
        assertEquals(0.9728465250616394,position.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(0.6457718232379019,position.getLongitude());
    }

    @Test
    void testToString() {
        assertEquals("GeoPosition{latitude=0.9728465250616394, longitude=0.6457718232379019}",position.toString());
    }

    @Test
    void testEquals() {
        GeoPosition positionEq = new GeoPosition("55(44'24'')", "37");
        GeoPosition positionNotEq = new GeoPosition("55(44'23'')", "37");
        assertEquals(true,position.equals(positionEq));
        assertEquals(false,position.equals(positionNotEq));
    }

    @Test
    void testHashCode() {
        assertEquals(919570694,position.hashCode());
    }
}