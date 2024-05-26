package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import fe.android.compose.dialog.helper.base.DialogState

@Composable
fun <R : Any> ResultDialog(
    state: ResultDialogState<R>,
    onClose: (R) -> Unit,
    onDismiss: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    LaunchedEffect(key1 = state.isOpen) {
        if (state.isOpen) return@LaunchedEffect
        if (state.result == null) {
            onDismiss?.invoke()
            return@LaunchedEffect
        }

        onClose(state.result!!)
    }

    if (state.isOpen) {
        content()
    }
}

@Composable
fun <R : Any> rememberResultDialogState(): ResultDialogState<R> {
    return rememberSaveable(saver = ResultDialogState.Saver()) { ResultDialogState() }
}
