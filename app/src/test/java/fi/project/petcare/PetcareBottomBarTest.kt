package fi.project.petcare

import fi.project.petcare.ui.nav.petCareDestinations
import org.junit.Test

class PetCareBottomBarTest {

    @Test
    fun bottomBar_routes_areAllValid() {
        // Ensure all routes are non-null and unique
        val routes = petCareDestinations.map { it.route }
        assert(routes.all { it.isNotEmpty() }) { "All routes must be non-empty" }
        assert(routes.size == routes.toSet().size) { "All routes must be unique" }
    }

    @Test
    fun bottomBar_hasExpectedNumberOfDestinations() {
        // Check the bottom bar has at least 1 destination
        assert(petCareDestinations.isNotEmpty()) { "There should be at least one destination in the bottom bar" }
    }
}
