package fi.project.petcare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.project.petcare.ui.composables.Alert
import fi.project.petcare.ui.composables.AlertType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class CommunityUiState(
    val alerts: List<Alert> = emptyList(),
    val isLoading: Boolean = false
)

class CommunityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CommunityUiState())
    val uiState: StateFlow<CommunityUiState> = _uiState.asStateFlow()

    init {
        // Add some sample alerts for demonstration
        addSampleAlerts()
    }

    fun addAlert(title: String, message: String, type: AlertType) {
        viewModelScope.launch {
            val newAlert = Alert(
                id = UUID.randomUUID().toString(),
                title = title,
                message = message,
                type = type
            )

            val currentAlerts = _uiState.value.alerts.toMutableList()
            currentAlerts.add(0, newAlert) // Add to the beginning of the list

            _uiState.value = _uiState.value.copy(alerts = currentAlerts)
        }
    }

    fun dismissAlert(alertId: String) {
        viewModelScope.launch {
            val updatedAlerts = _uiState.value.alerts.filter { it.id != alertId }
            _uiState.value = _uiState.value.copy(alerts = updatedAlerts)
        }
    }

    fun clearAllAlerts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(alerts = emptyList())
        }
    }

    private fun addSampleAlerts() {
        viewModelScope.launch {
            val sampleAlerts = listOf(
                Alert(
                    id = UUID.randomUUID().toString(),
                    title = "Lost Pet Alert",
                    message = "A golden retriever named Max was lost near Central Park. If you see him, please contact the owner.",
                    type = AlertType.WARNING
                ),
                Alert(
                    id = UUID.randomUUID().toString(),
                    title = "Pet Found!",
                    message = "Great news! Bella the cat has been found safe and returned to her family.",
                    type = AlertType.SUCCESS
                ),
                Alert(
                    id = UUID.randomUUID().toString(),
                    title = "Community Event",
                    message = "Join us for the annual Pet Care Workshop this Saturday at 2 PM in the community center.",
                    type = AlertType.INFO
                )
            )

            _uiState.value = _uiState.value.copy(alerts = sampleAlerts)
        }
    }

    fun addSampleAlert() {
        val sampleAlerts = listOf(
            Alert(
                id = UUID.randomUUID().toString(),
                title = "New Pet Adoption Event",
                message = "Local shelter is hosting an adoption event this weekend. Many pets are looking for loving homes!",
                type = AlertType.INFO
            ),
            Alert(
                id = UUID.randomUUID().toString(),
                title = "Missing Cat Alert",
                message = "Fluffy, a white Persian cat, went missing from Oak Street. Please keep an eye out!",
                type = AlertType.WARNING
            ),
            Alert(
                id = UUID.randomUUID().toString(),
                title = "Pet Vaccination Reminder",
                message = "Don't forget to schedule your pet's annual vaccination. Contact your vet today!",
                type = AlertType.SUCCESS
            )
        )

        val randomAlert = sampleAlerts.random()
        addAlert(randomAlert.title, randomAlert.message, randomAlert.type)
    }
}

