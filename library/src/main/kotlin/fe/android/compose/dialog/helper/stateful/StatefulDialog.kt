package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <T : Any, R : Any> StatefulDialog(
    state: StatefulDialogState<T, R>,
    onClose: (T, R) -> Unit,
    onDismiss: ((T) -> Unit)? = null,
    content: @Composable (T) -> Unit,
) {
    LaunchedEffect(key1 = state.isOpen) {
        if (state.isOpen) return@LaunchedEffect
        if (state.result == null) {
            onDismiss?.invoke(state.data)
            return@LaunchedEffect
        }

        onClose(state.data, state.result!!)
    }

    if (state.isOpen) {
        content(state.data)
    }
}

@Composable
fun <T : Any, R : Any> rememberStatefulDialog(data: T): StatefulDialogState<T, R> {
    return rememberSaveable(saver = StatefulDialogState.Saver()) { StatefulDialogState(data) }
}
