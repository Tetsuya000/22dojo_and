package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util;

import com.airbnb.epoxy.EpoxyDataBindingLayouts;
import com.airbnb.epoxy.EpoxyDataBindingPattern;

import jp.gr.java.conf.tmproject.dojoandroid2022.R;

@EpoxyDataBindingPattern(rClass = R.class, layoutPrefix = "  view_holder ")
@EpoxyDataBindingLayouts({R.layout.item_roadmap, R.layout.item_memo, R.layout.item_search_response})
interface EpoxyConfig {
}