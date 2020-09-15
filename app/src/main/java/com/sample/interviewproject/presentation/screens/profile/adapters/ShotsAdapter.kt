package com.sample.interviewproject.presentation.screens.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.interviewproject.R
import com.sample.interviewproject.model.Shot
import kotlinx.android.synthetic.main.view_circle_image_view.view.*

class ShotsAdapter() :
    RecyclerView.Adapter<ShotsAdapter.ViewHolder>() {

    private val shots: MutableList<Shot> = arrayListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val shot = shots[position]
            Glide.with(itemView)
                .load(shot.imageUrl)
                .into(itemView.iv_avatar)
        }
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_shot, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shots.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    internal fun setShots(shots: List<Shot>) {
        this.shots.clear()
        this.shots.addAll(shots)
        notifyDataSetChanged()
    }
}