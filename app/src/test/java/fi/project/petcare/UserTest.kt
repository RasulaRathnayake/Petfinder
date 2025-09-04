package fi.project.petcare

import fi.project.petcare.model.data.User
import fi.project.petcare.model.data.demoUser
import kotlinx.serialization.json.Json
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class UserTest {

    @Test
    fun demoUser_propertiesAreCorrect() {
        assertThat(demoUser.id).isEqualTo("123e4567-e89b-12d3-a456-426614174000")
        assertThat(demoUser.name).isEqualTo("John Demo")
        assertThat(demoUser.email).isEqualTo("john@demo.com")
    }

    @Test
    fun user_equality_sameProperties_areEqual() {
        val user1 = User(id = "1", name = "Alice", email = "alice@demo.com")
        val user2 = User(id = "1", name = "Alice", email = "alice@demo.com")
        val user3 = User(id = "2", name = "Bob", email = "bob@demo.com")

        assertThat(user1).isEqualTo(user2)
        assertThat(user1).isNotEqualTo(user3)
    }

    @Test
    fun user_optionalName_canBeNull() {
        val user = User(id = "2", name = null, email = "noName@demo.com")
        assertThat(user.name).isNull()
        assertThat(user.email).isEqualTo("noName@demo.com")
    }

    @Test
    fun user_serialization_and_deserialization_works() {
        val user = User(id = "3", name = "Charlie", email = "charlie@demo.com")

        // Serialize to JSON
        val jsonString = Json.encodeToString(User.serializer(), user)

        // Deserialize back to User
        val deserializedUser = Json.decodeFromString(User.serializer(), jsonString)

        assertThat(deserializedUser).isEqualTo(user)
    }
}
