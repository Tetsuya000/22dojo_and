package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted

@AndroidEntryPoint
class EditCharacterDialogFragment : DialogFragment() {

    val viewModel: EditCharacterDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        observeEditSuccess()

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    EditCharacterNameCard()
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        Dialog(requireContext(), R.style.FullScreenAlertDialogTheme)

    @Composable
    fun EditCharacterNameCard() {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()) {
            CardContent()
        }
    }

    @Composable
    fun CardContent() {
        Column {
            ImageClose()
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                  ) {

                val focusRequester = remember { FocusRequester() }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                var text by remember { mutableStateOf(viewModel.characterName.value) }
                val isError = text.length > 15 || text.isEmpty()

                MaxLengthErrorTextField(value = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 15,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .focusRequester(focusRequester),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(8.dp),
                    isError = isError,
                    label = {
                        Text(stringResource(id = R.string.hint_character_name))
                    })

                SaveButton(text, isError)
            }
        }
    }

    @Composable
    fun MaxLengthErrorTextField(
        value: String,
        onValueChange: (String) -> Unit,
        maxLength: Int,
        modifier: Modifier = Modifier,
        colors: TextFieldColors,
        shape: RoundedCornerShape,
        isError: Boolean,
        label: @Composable (() -> Unit)? = null) {

        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = MaxLengthErrorTransformation(maxLength),
            modifier = modifier,
            colors = colors,
            shape = shape,
            isError = isError,
            label = label)
    }

    class MaxLengthErrorTransformation(
        private val maxLength: Int,
        private val errorStyle: SpanStyle = SpanStyle(color = Color.Red)) : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            return TransformedText(
                AnnotatedString(
                    text = text.text, spanStyles = if (text.length > maxLength) {
                        listOf(AnnotatedString.Range(errorStyle, maxLength, text.length))
                    }
                    else {
                        emptyList()
                    }), OffsetMapping.Identity)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MaxLengthErrorTransformation) return false
            if (maxLength != other.maxLength || errorStyle != other.errorStyle) return false
            return true
        }

        override fun hashCode(): Int {
            return maxLength.hashCode()
        }
    }

    @Composable
    fun ImageClose() {
        Row(
            Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {

            Image(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable(onClick = {
                        findNavController().popBackStack()
                    }),
                painter = painterResource(id = R.drawable.ic_close),
                colorFilter = ColorFilter.tint(colorResource(R.color.black)),
                contentDescription = "close")
        }
    }

    @Composable
    fun SaveButton(
        text: String,
        isError: Boolean) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            enabled = !isError,
            onClick = {
                if (isError) return@Button
                viewModel.saveCharacterName(text)
            },
              ) {
            Text(stringResource(R.string.text_ok))
        }
    }

    private fun observeEditSuccess() = viewModel.isEditSuccess.collectWhenStarted(viewLifecycleOwner) {
        findNavController().popBackStack()
    }
}
