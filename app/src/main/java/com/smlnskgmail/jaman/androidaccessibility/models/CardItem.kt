package com.smlnskgmail.jaman.androidaccessibility.models

import java.util.*

data class CardItem(
    var avatarId: Int = -1,
    var name: String = "",
    var date: Date = Date(),
    var country: String = "",
    var shareText: String = "",
    var imageId: Int = -1,
    var isLiked: Boolean = false,
    var isFavorite: Boolean  = false
)
