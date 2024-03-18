package fe.android.compose.dialog.helper

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class DialogState {
    Open, Closed
}

@Stable
abstract class StateLessDialog(initial: DialogState? = null) {
    private var dialogState by mutableStateOf(initial)
    internal fun openInternal(): Boolean {
        if (isOpen) return false
        dialogState = DialogState.Open

        return true
    }

    internal fun close(): Boolean {
        if (!isOpen) return false
        dialogState = DialogState.Closed

        return true
    }

    val currentState: DialogState?
        get() = dialogState

    val isOpen: Boolean
        get() = currentState == DialogState.Open

    val isClosed: Boolean
        get() = !isOpen
}
