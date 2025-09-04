package fi.project.petcare

import com.google.common.truth.Truth.assertThat
import fi.project.petcare.ui.nav.Screen
import fi.project.petcare.ui.nav.petCareDestinations
import org.junit.Test

class PetCareDestinationTest {

    @Test
    fun welcomeScreen_hasCorrectRoute() {
        assertThat(Screen.Welcome.route).isEqualTo("Welcome")
    }

    @Test
    fun dashboardScreens_haveExpectedRoutes() {
        assertThat(Screen.Dashboard.Home.route).isEqualTo("Home")
        assertThat(Screen.Dashboard.Pets.route).isEqualTo("Pets")
        assertThat(Screen.Dashboard.Community.route).isEqualTo("Community")
    }

    @Test
    fun petProfileScreen_hasCorrectRoute() {
        assertThat(Screen.PetProfile.route).isEqualTo("PetProfile")
    }

    @Test
    fun settingsScreen_hasCorrectRoute() {
        assertThat(Screen.Settings.route).isEqualTo("Settings")
    }

    @Test
    fun petCareDestinations_containsExpectedScreens() {
        val routes = petCareDestinations.map { it.route }
        assertThat(routes).containsExactly("Home", "Pets", "Community")
    }

    @Test
    fun dashboardScreens_haveIconsAssigned() {
        assertThat(Screen.Dashboard.Home.unselectedIcon).isNotNull()
        assertThat(Screen.Dashboard.Home.selectedIcon).isNotNull()

        assertThat(Screen.Dashboard.Pets.unselectedIcon).isNotNull()
        assertThat(Screen.Dashboard.Pets.selectedIcon).isNotNull()

        assertThat(Screen.Dashboard.Community.unselectedIcon).isNotNull()
        assertThat(Screen.Dashboard.Community.selectedIcon).isNotNull()
    }

    @Test
    fun screensWithoutIcons_areHandledCorrectly() {
        // PetProfile only has unselectedIcon
        assertThat(Screen.PetProfile.unselectedIcon).isNotNull()
        assertThat(Screen.PetProfile.selectedIcon).isNull()

        // Welcome has no icons
        assertThat(Screen.Welcome.unselectedIcon).isNull()
        assertThat(Screen.Welcome.selectedIcon).isNull()
    }
}
