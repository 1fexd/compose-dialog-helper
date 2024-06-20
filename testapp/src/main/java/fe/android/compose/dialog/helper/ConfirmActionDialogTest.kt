package fe.android.compose.dialog.helper

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import fe.android.compose.dialog.helper.confirm.ConfirmActionDialog
import fe.android.compose.dialog.helper.confirm.rememberConfirmActionDialog

@Composable
fun ConfirmActionDialogTest() {
    val events = remember { mutableStateListOf<String>() }
    val state = rememberConfirmActionDialog<String>()

    ConfirmActionDialog(
        state = state,
        onConfirm = { input -> events.add("Confirmed, input=$input") },
        onDismiss = { input -> events.add("Dismissed, input=$input") }
    ) { input ->
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            title = {
                Text(text = "Dialog test")
            },
            text = {
                Text(text = "Input is $input")
            },
            onDismissRequest = state::dismiss,
            confirmButton = {
                Button(onClick = state::confirm) {
                    Text(text = "Confim")
                }
            }
        )
    }

    Column {
        Button(onClick = { state.open("Open dialog") }) {
            Text(text = "Open dialog")
        }

        Button(onClick = { state.open("Open dialog 2") }) {
            Text(text = "Open dialog 2")
        }

        for (event in events) {
            Text(text = event, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }
    }
}
