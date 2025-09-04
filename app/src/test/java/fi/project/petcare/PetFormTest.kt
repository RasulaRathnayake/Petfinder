package fi.project.petcare

import fi.project.petcare.model.data.PetResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class PetFormUnitTest {

    @Test
    fun updatePetName_updatesCorrectly() {
        val pet = PetResponse.Pet(
            id = "1",              // String ✅
            name = "",
            species = "",
            breed = "",
            gender = "",
            microchipId = 0,
            weight = 0.0,
            ageMonths = 0,
            notes = "",
            locate = "",
            ownerId = "1"          // String? ✅
        )

        val updated = pet.copy(name = "Buddy")

        assertEquals("Buddy", updated.name)
    }
}
