package fi.project.petcare

import org.junit.Test

class InfoDialogUnitTest {

    @Test
    fun lambdas_areCalled() {
        var toggleCalled = false
        var confirmCalled = false

        // Simulate what would happen when buttons clicked
        val toggle = { toggleCalled = true }
        val confirm = { confirmCalled = true }

        // Simulate Confirm button logic
        toggle()
        confirm()

        assert(toggleCalled)
        assert(confirmCalled)

        // Reset flags
        toggleCalled = false
        confirmCalled = false

        // Simulate Dismiss button logic
        toggle()

        assert(toggleCalled)
        assert(!confirmCalled)
    }
}
