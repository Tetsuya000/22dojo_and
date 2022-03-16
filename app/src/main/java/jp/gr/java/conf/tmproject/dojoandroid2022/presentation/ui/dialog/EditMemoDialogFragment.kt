package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node

@AndroidEntryPoint
class EditMemoDialogFragment : DialogFragment() {

    private val viewModel: EditMemoDialogViewModel by viewModels()
    private var node: Node? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        node = arguments!!.getParcelable("node")

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    MemoCard()
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.FullScreenAlertDialogTheme)
    }

    @Preview
    @Composable
    fun MemoCard() {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
        ) {
            CardContent()
        }
    }

    @Composable
    fun CardContent() {
        Column {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                CardTitle()

                val focusRequester = remember { FocusRequester() }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                var text by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .focusRequester(focusRequester),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = Transparent,
                        disabledIndicatorColor = Transparent
                    ),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Memo") }
                )

                SaveButton(text)
            }
        }
    }

    @Composable
    fun CardTitle() {
        Text(getString(R.string.text_note), style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(16.dp))
    }

    @Composable
    fun SaveButton(text: String) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.saveNode(node!!, text)
            },
        ) {
            Text(getString(R.string.text_master))
        }
    }
}
