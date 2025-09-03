package fi.project.petcare.ui.composables

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import fi.project.petcare.ui.MapActivity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    onNavigateToSettings: () -> Unit,
    onAddPetClick: () -> Unit? = {},
    navController: NavController,
    content: @Composable () -> Unit
) {
    val scrollTopBarBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollTopBarBehavior.nestedScrollConnection),
        topBar = {
            PetCareTopBar(
                navController = navController,
                onSettingsClick = { onNavigateToSettings() },
                onAddClick = { onAddPetClick() },
                scrollBehavior = scrollTopBarBehavior
            )
        },
        bottomBar = {
            PetCareBottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Existing screen content
                content()

                // ✅ New Button to open Map
                Button(
                    onClick = {
                        val intent = Intent(context, MapActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("View Pet Map")
                }
            }
        }
    }
}
