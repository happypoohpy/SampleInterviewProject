package com.sample.interviewproject.model

data class User (
    var id: Long? = null,
    var givenName: String? = null,
    var surname: String? = null,
    var location: String? = null,
    var about: String? = null,
    var avatarUrl: String? = null,
    var featuredFriends: ArrayList<Friend>? = null,
    var shots: ArrayList<Shot>? = null
)