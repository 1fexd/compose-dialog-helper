package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <R : Any> ResultDialog(
    state: ResultDialogState<R>,
    onClose: (R) -> Unit,
    onDismiss: (() -> Unit)? = null,
    content: @Composable () -> Unit,
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
        content()
    }
}

@Composable
fun <R : Any> rememberResultDialogState(): ResultDialogState<R> {
    return rememberSaveable(saver = ResultDialogState.Saver()) { ResultDialogState() }
}
