package com.example.mygithub.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mygithub.data.local.FavoriteUser
import com.example.mygithub.data.local.FavoriteUserDao
import com.example.mygithub.data.local.UserDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}