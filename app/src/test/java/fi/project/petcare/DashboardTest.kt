package fi.project.petcare

import org.junit.Assert.assertTrue
import org.junit.Test


class DashboardTest {

    @Test
    fun testOnNavigateToSettingsCalled() {
        var called = false
        val onNavigateToSettings = { called = true }
        onNavigateToSettings()
        assertTrue("onNavigateToSettings should be called", called)
    }

    @Test
    fun testOnAddPetClickCalled() {
        var called = false
        val onAddPetClick: () -> Unit = { called = true }
        onAddPetClick()
        assertTrue("onAddPetClick should be called", called)
    }




}
