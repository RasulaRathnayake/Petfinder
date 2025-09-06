package fi.project.petcare

import fi.project.petcare.ui.composables.Alert
import fi.project.petcare.ui.composables.AlertType
import org.junit.Assert.*
import org.junit.Test

class AlertSystemTest {

    @Test
    fun alert_initializesCorrectly() {
        val alert = Alert(
            id = "1",
            title = "Test Alert",
            message = "This is a test message.",
            type = AlertType.INFO
        )

        assertEquals("1", alert.id)
        assertEquals("Test Alert", alert.title)
        assertEquals("This is a test message.", alert.message)
        assertEquals(AlertType.INFO, alert.type)
    }

    @Test
    fun dismissAlert_removesFromList() {
        val alerts = mutableListOf(
            Alert("1", "Info Alert", "Info message", AlertType.INFO),
            Alert("2", "Warning Alert", "Warning message", AlertType.WARNING),
            Alert("3", "Success Alert", "Success message", AlertType.SUCCESS)
        )

        // Simulate dismiss of alert with id = "1"
        val updatedAlerts = alerts.filterNot { it.id == "1" }

        assertEquals(2, updatedAlerts.size)
        assertFalse(updatedAlerts.any { it.id == "1" })
    }

    @Test
    fun multipleAlerts_areHandledCorrectly() {
        val alerts = listOf(
            Alert("1", "Info Alert", "Info message", AlertType.INFO),
            Alert("2", "Warning Alert", "Warning message", AlertType.WARNING)
        )

        assertEquals(2, alerts.size)
        assertTrue(alerts.any { it.type == AlertType.WARNING })
        assertTrue(alerts.any { it.type == AlertType.INFO })
    }
}
