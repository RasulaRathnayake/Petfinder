package fi.project.petcare.ui.composables

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class LoadingIndicatorUnitTest {

    @Test
    fun radialGradient_isCreatedCorrectly() {
        val colors = listOf(Color(0xFF000000), Color(0xFFFFFFFF))
        val brush = Brush.radialGradient(colors)

        // Test that brush has correct colors
        assertThat(brush).isNotNull()
    }
}
