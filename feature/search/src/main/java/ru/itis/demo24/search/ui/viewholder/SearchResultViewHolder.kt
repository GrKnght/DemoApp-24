package ru.itis.demo24.search.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.search.databinding.ItemSearchResultBinding

class SearchResultViewHolder(
    private val viewBinding: ItemSearchResultBinding,
    private val requestManager: RequestManager,
    onItemClick: (SearchResultModel) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root) {

    private var searchData: SearchResultModel? = null

    init {
        viewBinding.root.setOnClickListener {
            searchData?.let(onItemClick::invoke)
        }
    }

    fun bindData(data: SearchResultModel) {
        this.searchData = data
        viewBinding.itemTextTv.text = data.title
        requestManager
            .load(data.thumbnailUrl)
            .into(viewBinding.previewIv)
    }
}