package fe.android.compose.dialog.helper

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
import fe.android.compose.dialog.helper.result.ResultDialog
import fe.android.compose.dialog.helper.result.rememberResultDialogState

@Composable
fun ResultDialogTest() {
    val events = remember { mutableStateListOf<String>() }
    val state = rememberResultDialogState<Boolean>()

    ResultDialog(
        state = state,
        onClose = { events.add("Closed, $it") },
        onDismiss = { events.add("Dismissed") }
    ) {
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            title = {
                Text(text = "Dialog test")
            },
            text = {
                Text(text = "")
            },
            onDismissRequest = { state.dismiss() },
            confirmButton = {
                Button(onClick = { state.close(true) }) {
                    Text(text = "Confim")
                }
            })
    }

    Column {
        Button(onClick = { state.open() }) {
            Text(text = "Open dialog")
        }

        Button(onClick = { state.open() }) {
            Text(text = "Open dialog 2")
        }

        for (event in events) {
            Text(text = event, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }
    }
}
