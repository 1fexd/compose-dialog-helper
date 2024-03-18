package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <T : Any, R : Any> StatefulDialog(
    state: StatefulDialogState<T, R>,
    onClose: (R) -> Unit,
    content: @Composable (T) -> Unit,
) {
    if (state.isOpen) {
        content(state.data)
    } else if (state.isClosed && state.result != null) {
        onClose(state.result!!)
    }
}

@Composable
fun <T : Any, R : Any> rememberStatefulDialog(data: T? = null): StatefulDialogState<T, R> {
    return rememberSaveable(saver = StatefulDialogState.Saver()) { StatefulDialogState(data) }
}
