 package com.example.mygithub.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mygithub.R
import com.example.mygithub.databinding.ActivityDetailUserBinding
import com.example.mygithub.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class DetailUserActivity : AppCompatActivity() {

     companion object{
         const val EXTRA_USERNAME = "extra_username"
         const val EXTRA_ID = "extra_id"
         const val EXTRA_URL = "extra_url"
     }

     private lateinit var binding: ActivityDetailUserBinding
     private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        //disini
        viewModel.setUserdetail(username!!)
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count > 0){
                        binding.toogleFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        binding.toogleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toogleFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked){
                viewModel.addToFavorite(username, id, avatarUrl)
            }else{
                viewModel.removeFromFavorite(id)
            }
            binding.toogleFavorite.isChecked = _isChecked
        }

    val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }
}