package com.example.mygithub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_user")
data class FavoriteUser(
    val login: String,
    @PrimaryKey
    val id: Int,
    val avatar_url: String
) : java.io.Serializable
