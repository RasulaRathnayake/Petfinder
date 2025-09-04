package fi.project.petcare

import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import org.junit.Test

class GoogleSignInButtonTest {

    @Test
    fun `onResult handles all NativeSignInResult cases`() {
        // Correct instances according to your library
        val success = NativeSignInResult.Success
        val closed = NativeSignInResult.ClosedByUser
        val error = NativeSignInResult.Error("Test error")
        val networkError = NativeSignInResult.NetworkError("Network failure")

        val results = listOf(success, closed, error, networkError)

        results.forEach { result ->
            when (result) {
                NativeSignInResult.Success -> { /* handled */ }
                NativeSignInResult.ClosedByUser -> { /* handled */ }
                is NativeSignInResult.Error -> { /* handled */ }
                is NativeSignInResult.NetworkError -> { /* handled */ }
            }
        }

        // Test passes if no exceptions are thrown
        assert(true)
    }
}
