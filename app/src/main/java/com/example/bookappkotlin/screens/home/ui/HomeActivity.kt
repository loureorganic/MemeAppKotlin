package com.example.bookappkotlin.screens.home.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappkotlin.R
import com.example.bookappkotlin.repositories.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityHomeBinding
import com.example.bookappkotlin.screens.home.adapters.PhotoAdapter
import com.example.bookappkotlin.screens.home.viewmodel.HomeViewModel
import com.example.bookappkotlin.screens.login.ui.LoginActivity
import com.example.bookappkotlin.screens.profile.ui.ProfileActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModelUser: UserViewModel
    private lateinit var viewModelHome : HomeViewModel

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val photoAdapter by inject<PhotoAdapter> {
        parametersOf(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        toggle = ActionBarDrawerToggle(this, binding.drawerLayoutOne, R.string.open, R.string.close)
        binding.drawerLayoutOne.addDrawerListener(toggle)
        toggle.syncState()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = photoAdapter

        viewModelUser = ViewModelProvider(this)[UserViewModel::class.java]
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]

        listMemeAtPhotoAdapter()
        navigationViewListener()
    }

    private fun navigationViewListener() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_1 -> startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                R.id.item_2 -> Toast.makeText(
                    applicationContext,
                    "Clicked item 2", Toast.LENGTH_LONG
                ).show()
                R.id.item_3 -> {
                    viewModelHome.signOutUser()
                    viewModelUser.deleteAllUsersData()
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                }
            }
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navigationView.bringToFront()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listMemeAtPhotoAdapter() {
        viewModelHome.getAllMemes()

        viewModelHome.listMemeResponseLiveData.observe(this){ memeList ->

            photoAdapter.setDataList(memeList)
            photoAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroy() {
        viewModelHome.destroyComposite()
        super.onDestroy()
    }
}