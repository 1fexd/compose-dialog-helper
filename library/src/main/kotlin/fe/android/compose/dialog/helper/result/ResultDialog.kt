package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import fe.android.compose.dialog.helper.DialogState

@Composable
fun <R : Any> ResultDialog(
    state: ResultDialogState<R>,
    onClose: (R) -> Unit,
    content: @Composable () -> Unit,
) {
    if (state.isOpen) {
        content()
    } else if (state.isClosed && state.result != null) {
        onClose(state.result!!)
    }
}

@Composable
fun <R : Any> rememberResultDialogState(initial: DialogState? = null): ResultDialogState<R> {
    return rememberSaveable(saver = ResultDialogState.Saver()) { ResultDialogState(initial) }
}
