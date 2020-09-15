package com.sample.interviewproject.presentation.screens.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.interviewproject.R
import com.sample.interviewproject.model.Friend
import kotlinx.android.synthetic.main.item_featured_friend.view.*
import kotlinx.android.synthetic.main.view_circle_image_view.view.*

class FeaturedFriendsAdapter() :
    RecyclerView.Adapter<FeaturedFriendsAdapter.ViewHolder>() {

    private val featuredFriends: MutableList<Friend> = arrayListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val friend = featuredFriends[position]
            Glide.with(itemView)
                .load(friend.avatarUrl)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.iv_avatar)

            val backgroundTint = if (friend.isFavorite == true) R.color.yellow else R.color.white
            itemView.view_avatar.backgroundTintList =
                ContextCompat.getColorStateList(context, backgroundTint)

            itemView.tv_given_name.text = "${friend.givenName}"
        }
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_featured_friend, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return featuredFriends.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    internal fun setFriends(featuredFriends: List<Friend>) {
        this.featuredFriends.clear()
        this.featuredFriends.addAll(featuredFriends)
        notifyDataSetChanged()
    }
}