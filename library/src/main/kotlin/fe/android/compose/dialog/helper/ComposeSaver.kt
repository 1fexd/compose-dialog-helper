package fe.android.compose.dialog.helper

import androidx.compose.runtime.saveable.SaverScope

typealias ComposeSaver<O, S> = androidx.compose.runtime.saveable.Saver<O, S>

fun <O, S : Any> createSaver(
    save: SaverScope.(O) -> S?,
    restore: (S) -> O?
): ComposeSaver<O, S> {
    return object : ComposeSaver<O, S> {
        override fun restore(value: S): O? {
            return restore(value)
        }

        override fun SaverScope.save(value: O): S? {
            return save(value)
        }
    }
}
