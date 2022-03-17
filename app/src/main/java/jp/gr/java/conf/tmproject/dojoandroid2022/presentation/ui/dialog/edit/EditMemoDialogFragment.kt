package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.edit

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
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted

@AndroidEntryPoint
class EditMemoDialogFragment : DialogFragment() {

    private val viewModel: EditMemoDialogViewModel by viewModels()
    private var node: Node? = null
    private var memo = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        node = arguments!!.getParcelable("node")
        memo = arguments!!.getString("memo")!!

        observeEditSuccess()

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    MemoCard()
                }
            }
        }
    }

    private fun observeEditSuccess() = viewModel.isEditSuccess.collectWhenStarted(viewLifecycleOwner) {
        dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        Dialog(requireContext(), R.style.FullScreenAlertDialogTheme)

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
                var text by remember { mutableStateOf(memo) }
                TextField(
                    modifier = Modifier
                        .height(200.dp)
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
    fun ImageClose() {
        Row(
            Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Image(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable(onClick = {
                        dismiss()
                    }),
                painter = painterResource(id = R.drawable.ic_close),
                colorFilter = ColorFilter.tint(colorResource(R.color.black)),
                contentDescription = "close"
            )
        }
    }

    @Composable
    fun SaveButton(text: String) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                node?.let {
                    val memo = Memo(it.id, it.title, text)
                    viewModel.saveMemo(memo)
                }
            },
        ) {
            Text(stringResource(R.string.text_ok))
        }
    }
}
