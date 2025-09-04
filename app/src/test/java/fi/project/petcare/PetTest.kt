
package fi.project.petcare

import com.google.common.truth.Truth.assertThat
import fi.project.petcare.model.data.PetResponse
import org.junit.Test

class PetTest {

    @Test
    fun pet_initialization_hasCorrectValues() {
        // Arrange: create a Pet object
        val pet = PetResponse.Pet(
            id = "pet1",
            name = "Milo",
            species = "Dog",
            breed = "Labrador",
            gender = "Male",
            weight = 12.5,
            ageMonths = 18,
            notes = "Friendly",
            imgUrl = "https://example.com/milo.jpg",
            microchipId = 123456,
            ownerId = "owner1",
            updatedAt = "2025-09-04T12:00:00Z",
            createdAt = "2025-01-01T08:00:00Z",
            locate = "Colombo"
        )

        // Assert: check each field
        assertThat(pet.id).isEqualTo("pet1")
        assertThat(pet.name).isEqualTo("Milo")
        assertThat(pet.species).isEqualTo("Dog")
        assertThat(pet.breed).isEqualTo("Labrador")
        assertThat(pet.gender).isEqualTo("Male")
        assertThat(pet.weight).isEqualTo(12.5)
        assertThat(pet.ageMonths).isEqualTo(18)
        assertThat(pet.notes).isEqualTo("Friendly")
        assertThat(pet.imgUrl).isEqualTo("https://example.com/milo.jpg")
        assertThat(pet.microchipId).isEqualTo(123456)
        assertThat(pet.ownerId).isEqualTo("owner1")
        assertThat(pet.updatedAt).isEqualTo("2025-09-04T12:00:00Z")
        assertThat(pet.createdAt).isEqualTo("2025-01-01T08:00:00Z")
        assertThat(pet.locate).isEqualTo("Colombo")
    }

    @Test
    fun pet_copy_createsEqualObject() {
        val original = PetResponse.Pet(
            id = "pet1",
            name = "Milo",
            species = "Dog",
            breed = "Labrador",
            gender = "Male",
            weight = 12.5,
            ageMonths = 18,
            notes = "Friendly",
            imgUrl = null,
            microchipId = 123456,
            ownerId = "owner1",
            updatedAt = null,
            createdAt = null,
            locate = "Colombo"
        )

        // Act: copy the object
        val copy = original.copy(name = "Milo")

        // Assert: original and copy are equal
        assertThat(copy).isEqualTo(original)
    }

    @Test
    fun pet_equality_sameId_returnsTrue() {
        val pet1 = PetResponse.Pet(
            id = "pet1",
            name = "Milo",
            species = "Dog",
            breed = "Labrador",
            gender = "Male",
            weight = 12.5,
            ageMonths = 18,
            notes = null,
            imgUrl = null,
            microchipId = 123456,
            ownerId = "owner1",
            updatedAt = null,
            createdAt = null,
            locate = "Colombo"
        )

        val pet2 = PetResponse.Pet(
            id = "pet1",
            name = "Milo",
            species = "Dog",
            breed = "Labrador",
            gender = "Male",
            weight = 12.5,
            ageMonths = 18,
            notes = "Friendly",
            imgUrl = null,
            microchipId = 123456,
            ownerId = "owner1",
            updatedAt = null,
            createdAt = null,
            locate = "Colombo"
        )

        assertThat(pet1).isEqualTo(pet2)
    }
}
