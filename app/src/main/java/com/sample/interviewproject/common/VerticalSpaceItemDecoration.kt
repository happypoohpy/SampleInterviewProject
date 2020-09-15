package com.sample.interviewproject.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // Apply offset only to first item
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = verticalSpaceHeight
        }
        outRect.right = verticalSpaceHeight
    }
}