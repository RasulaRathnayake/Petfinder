package fi.project.petcare

import fi.project.petcare.model.data.PetResponse
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class FullScreenModalTest {

    @Test
    fun initialPetState_ownerIdIsSetCorrectly() {
        val userId = "user123"

        val newPet = PetResponse.Pet(
            name = "",
            species = "",
            breed = "",
            gender = "",
            weight = 0.0,
            ageMonths = 0,
            notes = "",
            microchipId = 0,
            ownerId = userId,
            locate = "london,uk"
        )

        assertThat(newPet.ownerId).isEqualTo(userId)
        assertThat(newPet.name).isEmpty()
        assertThat(newPet.weight).isEqualTo(0.0)
        assertThat(newPet.ageMonths).isEqualTo(0)
    }

    @Test
    fun updatingPet_updatesAllFieldsCorrectly() {
        val userId = "user123"
        var newPet = PetResponse.Pet(
            name = "",
            species = "",
            breed = "",
            gender = "",
            weight = 0.0,
            ageMonths = 0,
            notes = "",
            microchipId = 0,
            ownerId = userId,
            locate = "london,uk"
        )

        // Simulate updating pet
        val updatedPet = newPet.copy(
            name = "Milo",
            species = "Dog",
            breed = "Beagle",
            weight = 10.5,
            ageMonths = 24
        )

        newPet = updatedPet

        assertThat(newPet.name).isEqualTo("Milo")
        assertThat(newPet.species).isEqualTo("Dog")
        assertThat(newPet.breed).isEqualTo("Beagle")
        assertThat(newPet.weight).isEqualTo(10.5)
        assertThat(newPet.ageMonths).isEqualTo(24)
    }

    @Test
    fun enableSaveFlag_canBeEnabled() {
        var enableSave = false

        // Simulate enabling save
        val onEnableSave = { enableSave = true }
        onEnableSave()

        assertThat(enableSave).isTrue()
    }
}
