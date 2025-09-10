package fi.project.petcare.ui.composables

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fi.project.petcare.model.data.PetResponse
import fi.project.petcare.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * PetAvatar - displays pet image (from picked URI or fallback drawable) and an overlay camera button.
 *
 * Usage:
 *  - Replace your current avatar Image(...) block with:
 *      PetAvatar(pet = pet, onImagePicked = { uri -> /* optional: upload/save URI */ })
 *
 * Note: `onImagePicked` receives the local Uri selected by user. If you want to upload it to Supabase,
 *       implement upload in your ViewModel and call it from onImagePicked.
 */
@Composable
fun PetAvatar(
    pet: PetResponse.Pet,
    modifier: Modifier = Modifier,
    onImagePicked: (Uri) -> Unit = {}
) {
    val context = LocalContext.current

    // Holds locally-picked bitmap for immediate display
    var pickedBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var pickedUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher to pick a single image from device
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            pickedUri = it
        }
    }

    // When pickedUri changes, load the bitmap off the UI thread
    LaunchedEffect(pickedUri) {
        pickedUri?.let { uri ->
            val bmp = loadBitmapFromUri(context, uri)
            if (bmp != null) {
                pickedBitmap = bmp
                onImagePicked(uri) // notify caller (ViewModel upload, save DB, etc.)
            }
        }
    }

    Box(modifier = modifier.size(98.dp)) {
        // 1) If user picked an image -> show it
        if (pickedBitmap != null) {
            Image(
                bitmap = pickedBitmap!!,
                contentDescription = "${pet.name} photo",
                modifier = Modifier
                    .size(98.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // 2) If pet has imgUrl (server) you may want to use Coil/AsyncImage to load it.
            //    We keep a simple fallback here since the project doesn't include Coil by default.
        } else if (!pet.imgUrl.isNullOrEmpty()) {
            // If you add Coil, replace this with AsyncImage(model = pet.imgUrl, ...)
            Image(
                painter = painterResource(id = R.drawable.ic_petcare_default),
                contentDescription = "${pet.name} photo (from server)",
                modifier = Modifier
                    .size(98.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // 3) The special "Fluffy" case kept from your original file
        } else if (pet.name == "Fluffy") {
            Image(
                painter = painterResource(id = R.drawable.pet_icon_1),
                contentDescription = "Pet Cover Photo",
                modifier = Modifier
                    .size(98.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // 4) Final fallback (default drawable & tint behavior)
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_petcare_default),
                contentDescription = "Pet Cover Photo",
                modifier = Modifier
                    .size(98.dp)
                    .clip(CircleShape),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
                contentScale = ContentScale.Crop
            )
        }

        // Small circular camera button overlayed bottom-end of avatar
        IconButton(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                .clip(CircleShape)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Upload photo",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

/**
 * Helper to load a bitmap from Uri on IO dispatcher and convert to ImageBitmap for Compose.
 */
private suspend fun loadBitmapFromUri(context: Context, uri: Uri): ImageBitmap? = withContext(Dispatchers.IO) {
    try {
        val bitmap = if (Build.VERSION.SDK_INT < 28) {
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
        bitmap?.asImageBitmap()
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }
}
