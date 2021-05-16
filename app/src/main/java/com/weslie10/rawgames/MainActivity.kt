package com.weslie10.rawgames

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.weslie10.rawgames.databinding.ActivityMainBinding
import com.weslie10.rawgames.ui.home.HomeFragment


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_menu -> loadFragment(HomeFragment())
            R.id.favorite_menu -> moveToFavoriteFragment()
        }
        return true
    }

    private fun moveToFavoriteFragment() {
        val fragment = instantiateFragment()
        if (fragment != null) {
            loadFragment(fragment)
        }
    }

    private fun instantiateFragment(): Fragment? {
        return try {
            Class.forName("$packageName.favorite.FavoriteFragment")
                .newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
            return true
        }
        return false
    }
}