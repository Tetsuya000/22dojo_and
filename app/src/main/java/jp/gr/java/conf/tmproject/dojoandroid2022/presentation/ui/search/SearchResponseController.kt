package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.search

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchItem
import jp.gr.java.conf.tmproject.dojoandroid2022.itemSearchResponse

class SearchResponseController(
    private val selectListener: SelectListener
) : TypedEpoxyController<List<SearchItem>>() {

    override fun buildModels(responseList: List<SearchItem>) {

        responseList.forEach { response ->
            itemSearchResponse {
                id(response.id)
                title(response.fullName)
                onClickListener(
                    View.OnClickListener {
                        this@SearchResponseController.selectListener.onSelected(response.url)
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(url: String)
    }
}
