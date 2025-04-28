package ru.itis.demo24.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.search.databinding.ItemSearchResultBinding
import ru.itis.demo24.search.ui.viewholder.SearchResultViewHolder

class SearchRvAdapter(
    private val requestManager: RequestManager,
) : RecyclerView.Adapter<SearchResultViewHolder>() {

    private var searchItems = mutableListOf<SearchResultModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            viewBinding = ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            requestManager = requestManager,
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bindData(data = searchItems[position])
    }

    override fun getItemCount(): Int = searchItems.size
}