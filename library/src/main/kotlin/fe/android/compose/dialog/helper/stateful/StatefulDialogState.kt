package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.base.BaseResultDialogState
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.createSaver

@Stable
class StatefulDialogState<T : Any, R : Any>(
    private val defaultData: T,
    result: R? = null,
) : BaseResultDialogState<R>(DialogState.Closed, result) {
    private val dataState = mutableStateOf(defaultData)

    internal val data: T
        get() = dataState.value

    fun tryGetResult(): Pair<T, R?>? {
        if (isOpen) return null
        return data to result
    }

    fun open(data: T = defaultData): Boolean {
        if (isOpen) return false
        dataState.value = data

        return super.tryOpen()
    }

    companion object {
        fun <T : Any, R : Any> Saver(): ComposeSaver<StatefulDialogState<T, R>, T> {
            return createSaver(
                save = { it.dataState.value },
                restore = { StatefulDialogState(it) }
            )
        }
    }
}
