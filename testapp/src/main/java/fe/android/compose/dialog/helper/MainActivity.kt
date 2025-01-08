package fe.android.compose.dialog.helper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Text(text = "Stateful dialog test") }
                item { StatefulDialogTest() }
                item { HorizontalDivider() }
                item { Text(text = "Result dialog test") }
                item { ResultDialogTest() }
                item { HorizontalDivider() }
                item { Text(text = "Input result dialog test") }
                item { InputResultDialogTest() }
                item { HorizontalDivider() }
                item { Text(text = "Confirm action test") }
                item { ConfirmActionDialogTest() }
            }
        }
    }
}
