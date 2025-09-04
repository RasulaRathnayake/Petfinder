package fi.project.petcare

import org.junit.Assert.*
import org.junit.Test

class RegisterLoginTest {

    @Test
    fun testRegisterCallback_invokesWithCorrectData() {
        var receivedUsername: String? = null
        var receivedEmail: String? = null
        var receivedPassword: String? = null

        val onRegister: (String?, String, String) -> Unit = { username, email, password ->
            receivedUsername = username
            receivedEmail = email
            receivedPassword = password
        }

        // Simulate user inputs
        val inputUsername = "JohnDoe"
        val inputEmail = "john@example.com"
        val inputPassword = "Password123!"

        // Call the callback directly
        onRegister(inputUsername, inputEmail, inputPassword)

        assertEquals("JohnDoe", receivedUsername)
        assertEquals("john@example.com", receivedEmail)
        assertEquals("Password123!", receivedPassword)
    }

    @Test
    fun testLoginCallback_invokesWithCorrectData() {
        var receivedEmail: String? = null
        var receivedPassword: String? = null

        val onLogin: (String, String) -> Unit = { email, password ->
            receivedEmail = email
            receivedPassword = password
        }

        // Simulate user inputs
        val inputEmail = "jane@example.com"
        val inputPassword = "SecurePass456"

        // Call the callback directly
        onLogin(inputEmail, inputPassword)

        assertEquals("jane@example.com", receivedEmail)
        assertEquals("SecurePass456", receivedPassword)
    }
}
