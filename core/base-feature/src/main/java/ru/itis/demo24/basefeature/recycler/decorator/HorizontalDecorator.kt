package ru.itis.demo24.basefeature.recycler.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalDecorator(
    private val padding: Float,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        view.setPadding(padding.toInt(), 0, padding.toInt(), 0)
    }
}