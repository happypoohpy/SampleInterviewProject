package com.sample.interviewproject.presentation.screens.friends.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.interviewproject.R
import com.sample.interviewproject.model.Friend
import kotlinx.android.synthetic.main.item_favorite_friend.view.tv_given_name
import kotlinx.android.synthetic.main.view_circle_image_view.view.*

class FriendsAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_NORMAL = 0
        private const val TYPE_FAVORITE = 1
    }

    private val friends: MutableList<Friend> = arrayListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val friend = friends[position]
            Glide.with(itemView)
                .load(friend.avatarUrl)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.iv_avatar)

            itemView.tv_given_name.text = "${friend.givenName} ${friend.surname}"
        }
    }

    inner class FavoritedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val friend = friends[position]
            Glide.with(itemView)
                .load(friend.avatarUrl)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.iv_avatar)

            itemView.tv_given_name.text = "${friend.givenName} ${friend.surname}"
        }
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        if (viewType == TYPE_FAVORITE) {
            return FavoritedViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_favorite_friend, parent, false)
            )
        }

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_friend, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (friends[position].isFavorite == true) TYPE_FAVORITE else TYPE_NORMAL
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == TYPE_FAVORITE) {

            (holder as FavoritedViewHolder).onBind(position)
            return
        }
        (holder as ViewHolder).onBind(position)
    }

    internal fun setFriends(featuredFriends: List<Friend>) {
        this.friends.clear()
        this.friends.addAll(featuredFriends)
        notifyDataSetChanged()
    }
}