package fi.project.petcare.ui.composables



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Alert(
    val id: String,
    val title: String,
    val message: String,
    val type: AlertType,
    val timestamp: Long = System.currentTimeMillis()
)

enum class AlertType {
    INFO, WARNING, SUCCESS
}

@Composable
fun AlertSystem(
    alerts: List<Alert>,
    onDismissAlert: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        alerts.forEach { alert ->
            AlertCard(
                alert = alert,
                onDismiss = { onDismissAlert(alert.id) }
            )
        }
    }
}

@Composable
fun AlertCard(
    alert: Alert,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, iconColor, icon) = when (alert.type) {
        AlertType.INFO -> Triple(
            Color(0xFFE3F2FD),
            Color(0xFF1976D2),
            Icons.Default.Info
        )
        AlertType.WARNING -> Triple(
            Color(0xFFFFF3E0),
            Color(0xFFE65100),
            Icons.Default.Warning
        )
        AlertType.SUCCESS -> Triple(
            Color(0xFFE8F5E8),
            Color(0xFF2E7D32),
            Icons.Default.CheckCircle
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = alert.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = alert.message,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            IconButton(
                onClick = onDismiss
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Dismiss",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AlertButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text("Alerts")
    }
}

