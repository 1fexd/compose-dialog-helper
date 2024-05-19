package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import fe.android.compose.dialog.helper.base.DialogState

@Composable
fun <R : Any> ResultDialog(
    state: ResultDialogState<R>,
    onClose: (R) -> Unit,
    content: @Composable () -> Unit,
) {
    LaunchedEffect(key1 = state.isOpen) {
        val result = state.tryGetResult() ?: return@LaunchedEffect
        onClose(result)
    }

    if (state.isOpen) {
        content()
    }
}

@Composable
fun <R : Any> rememberResultDialogState(): ResultDialogState<R> {
    return rememberSaveable(saver = ResultDialogState.Saver()) { ResultDialogState() }
}
