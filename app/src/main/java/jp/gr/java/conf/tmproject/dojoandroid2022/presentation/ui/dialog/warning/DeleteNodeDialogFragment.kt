package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.warning

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted

@AndroidEntryPoint
class DeleteNodeDialogFragment : DialogFragment() {

    private val viewModelNode: DeleteNodeDialogViewModel by viewModels()
    private var node: Node? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        node = arguments!!.getParcelable("node")
        observeSaveSuccess()

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

    private fun observeSaveSuccess() {
        viewModelNode.isDeleteSuccess.collectWhenStarted(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                dismiss()
            }
        }
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
            ImageClose()
            Column(
                Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CardTitle()
                DeleteButton()
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
    fun CardTitle() {
        Text(
            text = node?.title!!,
            color = colorResource(R.color.text_dialog),
            fontSize = 19.sp,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(5.dp)
        )
    }

    @Composable
    fun DeleteButton() {
        var enabled by remember { mutableStateOf(true) }

        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(backgroundColor = colorResource(R.color.warning_delete)),
            enabled = enabled,
            onClick = {
                enabled = false
                viewModelNode.deleteNode(node)
            },
        ) {
            Text(
                text = stringResource(R.string.text_delete),
                color = colorResource(R.color.text_button),
            )
        }
    }
}
