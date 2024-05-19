package fe.android.compose.dialog.helper

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.result.ResultDialog
import fe.android.compose.dialog.helper.result.rememberResultDialogState
import fe.android.compose.dialog.helper.stateful.StatefulDialog
import fe.android.compose.dialog.helper.stateful.rememberStatefulDialog


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StatefulDialogTest()
        }
    }

    @Composable
    private fun StatefulDialogTest(){
        val state = rememberStatefulDialog<Boolean, Boolean>(true)
        StatefulDialog(state = state, onClose = { input, result -> Log.d("Test", "Closed, input=$input, result=$result") }) { input ->
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
                })
        }

        Column {
            Button(onClick = { state.open(false) }) {
                Text(text = "Open dialog with input")
            }

//            Button(onClick = { state.open() }) {
//                Text(text = "Open dialog")
//            }
        }
    }

    @Composable
    private fun ResultDialogTest(){
        val state = rememberResultDialogState<Boolean>()
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
