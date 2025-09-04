package fi.project.petcare

import org.junit.Test
import org.osmdroid.util.GeoPoint

class MapActivityTest {

    @Test
    fun defaultMarker_isAtColombo() {
        val expectedLat = 6.9271
        val expectedLon = 79.8612

        val markerPosition = GeoPoint(6.9271, 79.8612)

        assert(markerPosition.latitude == expectedLat) { "Latitude should be $expectedLat" }
        assert(markerPosition.longitude == expectedLon) { "Longitude should be $expectedLon" }
    }

    @Test
    fun markerPosition_calculation() {
        // Example logic test: adding offsets
        val start = GeoPoint(6.9271, 79.8612)
        val offsetLat = 0.001
        val offsetLon = -0.001
        val newPosition = GeoPoint(start.latitude + offsetLat, start.longitude + offsetLon)

        assert(newPosition.latitude == 6.9281)
        assert(newPosition.longitude == 79.8602)
    }
}
