package fi.project.petcare

import fi.project.petcare.ui.nav.Screen
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class AppNavigationTest {

    @Test
    fun screenRoutes_areUnique() {
        val routes = listOf(
            Screen.Dashboard.Home.route,
            Screen.Dashboard.Pets.route,
            Screen.Dashboard.Community.route,
            Screen.PetProfile.route,
            Screen.Settings.route
        )

        // Ensure all routes are unique
        val uniqueRoutes = routes.toSet()
        assertThat(uniqueRoutes.size).isEqualTo(routes.size)
    }

    @Test
    fun settingsScreen_hasExpectedRoute() {
        val route = Screen.Settings.route
        assertThat(route).isEqualTo("settings")
    }

    @Test
    fun homeScreen_hasExpectedRoute() {
        val route = Screen.Dashboard.Home.route
        assertThat(route).isEqualTo("home")
    }
}
