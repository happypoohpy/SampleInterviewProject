package com.sample.interviewproject.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.io.Serializable
import java.lang.reflect.Type

data class Friend(
    var id: Long? = null,
    var givenName: String? = null,
    var surname: String? = null,
    var avatarUrl: String? = null,
    var isFavorite: Boolean? = null
)