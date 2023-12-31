package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RecordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    passwordMask: Boolean = false,
    multiline: Boolean = true,
    error: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
            errorIndicatorColor = Color.Transparent,
        ),
        label = {
            Text(text = stringResource(id = labelRes))
        },
        shape = CutCornerShape(8.dp),
        singleLine = !multiline,
        visualTransformation = if (passwordMask) PasswordVisualTransformation() else VisualTransformation.None,
        isError = error
    )
}