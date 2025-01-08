package fe.android.compose.dialog.helper.input

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <I : Any, R : Any> InputResultDialog(
    state: InputResultDialogState<I, R>,
    onClose: (R) -> Unit,
    onDismiss: (() -> Unit)? = null,
    content: @Composable (I) -> Unit,
) {
    var wasEverOpen by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = state.isOpen) {
        if (!wasEverOpen || state.isOpen) return@LaunchedEffect

        val result = state.result
        if (result != null) {
            onClose(result)
        } else {
            onDismiss?.invoke()
        }
    }

    if (state.isOpen) {
        wasEverOpen = true
        content(state.data)
    }
}

@Composable
fun <I : Any, R : Any> rememberInputResultDialog(): InputResultDialogState<I, R> {
    return rememberSaveable(saver = InputResultDialogState.Saver()) { InputResultDialogState() }
}
