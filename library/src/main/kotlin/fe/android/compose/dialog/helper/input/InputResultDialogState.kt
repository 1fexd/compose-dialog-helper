package fe.android.compose.dialog.helper.input

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.base.BaseResultDialogState
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.createSaver

@Stable
class InputResultDialogState<I : Any, R : Any>(
    result: R? = null,
) : BaseResultDialogState<R>(DialogState.Closed, result) {

    private val dataState = mutableStateOf<I?>(null)

    internal val data: I
        get() = dataState.value!!

    fun open(data: I): Boolean {
        if (isOpen) return false
        dataState.value = data

        return super.tryOpen()
    }

    companion object {
        fun <I : Any, R : Any> Saver(): ComposeSaver<InputResultDialogState<I, R>, R> {
            return createSaver(
                save = { it.result },
                restore = { InputResultDialogState(it) }
            )
        }
    }
}

