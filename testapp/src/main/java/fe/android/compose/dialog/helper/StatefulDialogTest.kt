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
import fe.android.compose.dialog.helper.stateful.StatefulDialog
import fe.android.compose.dialog.helper.stateful.rememberStatefulDialog

@Composable
fun StatefulDialogTest() {
    val events = remember { mutableStateListOf<String>() }
    val state = rememberStatefulDialog<Boolean, Boolean>(true)

    StatefulDialog(
        state = state,
        onClose = { input, result -> events.add("Closed, input=$input, result=$result") },
        onDismiss = { events.add("Dismissed: $it") }
    ) { input ->
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            title = {
                Text(text = "Dialog test")
            },
            text = {
                Text(text = "Input is $input")
            },
            onDismissRequest = { state.dismiss() },
            confirmButton = {
                Button(onClick = { state.close(true) }) {
                    Text(text = "Confim")
                }
            }
        )
    }

    Column {
        Button(onClick = { state.open(false) }) {
            Text(text = "Open dialog with input")
        }

        for (event in events) {
            Text(text = event, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }
    }
}
