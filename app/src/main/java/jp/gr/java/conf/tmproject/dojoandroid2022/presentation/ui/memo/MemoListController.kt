package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.memo

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.itemMemo

class MemoListController(
    private val selectListener: SelectListener
) : TypedEpoxyController<List<Memo>>() {

    override fun buildModels(
        memoList: List<Memo>
    ) {

        memoList.forEach { memo ->
            itemMemo {
                id(memo.nodeId)
                title(memo.title)
                onClickListener(
                    View.OnClickListener {
                        this@MemoListController.selectListener.onSelected(memo)
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(memo: Memo)
    }
}
