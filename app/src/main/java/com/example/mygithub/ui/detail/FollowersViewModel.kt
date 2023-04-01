package com.example.mygithub.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithub.api.RetrofitClient
import com.example.mygithub.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<User>>()
    fun setListFollowers(username: String){
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("error", t.message!!)
                }

            })
    }
    fun getListFollowers(): LiveData<ArrayList<User>>{
        return listFollowers
    }
}