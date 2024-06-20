package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Stable
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.createSaver
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.base.BaseResultDialogState


@Stable
class ResultDialogState<R : Any>(
    result: R? = null,
) : BaseResultDialogState<R>(DialogState.Closed, result) {

    fun open(): Boolean {
        return super.tryOpen()
    }

    companion object {
        fun <R : Any> Saver(): ComposeSaver<ResultDialogState<R>, R> {
            return createSaver(
                save = { it.result },
                restore = { ResultDialogState(it) }
            )
        }
    }
}
