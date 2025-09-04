package fi.project.petcare.model.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class HealthRecordTest {

    @Test
    fun healthRecord_equality_sameValues_returnsTrue() {
        val date = Date()
        val record1 = HealthRecord(
            type = HealthRecordType.MEDICATION,
            date = date,
            details = ""
        )
        val record2 = HealthRecord(
            type = HealthRecordType.MEDICATION,
            date = date,
            details = ""
        )

        assertEquals(record1, record2)
    }

    @Test
    fun healthRecord_copy_preservesFields() {
        val date = Date()
        val original = HealthRecord(
            type = HealthRecordType.SYMPTOM,
            date = date,
            details = "Fever"
        )

        val copy = original.copy(details = "High Fever")

        assertEquals(original.type, copy.type)
        assertEquals(original.date, copy.date)
        assertEquals("", copy.details)
    }

    @Test
    fun healthRecordState_defaults_areCorrect() {
        val state = HealthRecordState()

        assertEquals(HealthRecordType.OPERATION, state.type)
        assertTrue(state.details.isEmpty())
        assertTrue(state.operation.isEmpty())
        assertTrue(state.veterinarianvisit.isEmpty())
        assertTrue(state.medication.isEmpty())
        assertTrue(state.symptom.isEmpty())
        assertTrue(state.allergy.isEmpty())
        assertTrue(state.exercise.isEmpty())
        assertTrue(state.weight.isEmpty())
    }

    @Test
    fun healthRecordType_containsAllExpectedValues() {
        val expected = setOf(
            HealthRecordType.OPERATION,
            HealthRecordType.VETERINARIAN_VISIT,
            HealthRecordType.MEDICATION,
            HealthRecordType.SYMPTOM,
            HealthRecordType.ALLERGY,
            HealthRecordType.EXERCISE,
            HealthRecordType.WEIGHT_MEASUREMENT
        )

        assertEquals(expected, HealthRecordType.values().toSet())
    }
}
