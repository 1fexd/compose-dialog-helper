package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fe.android.compose.dialog.helper.StateLessDialog

@Stable
class StatefulDialogState<T : Any, R : Any>(
    data: T? = null,
    result: R? = null,
) : StateLessDialog() {
    private var dataState by mutableStateOf(data)
    private var resultState by mutableStateOf(result)

    internal val data: T
        get() = dataState!!

    internal val result: R?
        get() = resultState

    fun open(data: T): Boolean {
        if (isOpen) return false
        this.dataState = data
        this.resultState = null

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
        fun <T : Any, R : Any> Saver() = androidx.compose.runtime.saveable.Saver<StatefulDialogState<T, R>, T>(
            save = { it.dataState },
            restore = { StatefulDialogState(it) }
        )
    }
}
