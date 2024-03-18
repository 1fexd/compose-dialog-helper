package fe.android.compose.dialog.helper

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.window.DialogProperties
import fe.android.compose.dialog.helper.result.ResultDialog
import fe.android.compose.dialog.helper.result.rememberResultDialogState


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = rememberResultDialogState<Boolean>(DialogState.Open)
            ResultDialog(state = state, onClose = { Log.d("Test", "Closed, $it") }) {
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
                    Text(text = "lol")
                }
            }
        }
    }
}
