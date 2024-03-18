package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fe.android.compose.dialog.helper.DialogState
import fe.android.compose.dialog.helper.StateLessDialog


@Stable
class ResultDialogState<R : Any>(
    initial: DialogState? = null,
    result: R? = null,
) : StateLessDialog(initial) {
    private var resultState by mutableStateOf(result)

    internal val result: R?
        get() = resultState

    fun open(): Boolean {
        return openInternal()
    }

    fun dismiss(): Boolean {
        if (!isOpen) return false
        return close()
    }

    fun close(result: R): Any {
        if (!isOpen) return false
        this.resultState = result

        return close()
    }

    companion object {
        fun <R : Any> Saver(initial: DialogState? = null) =
            androidx.compose.runtime.saveable.Saver<ResultDialogState<R>, R>(
                save = { it.result },
                restore = { ResultDialogState(initial, it) }
            )
    }
}
