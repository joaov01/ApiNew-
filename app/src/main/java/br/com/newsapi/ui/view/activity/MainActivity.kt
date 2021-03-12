package br.com.newsapi.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.newsapi.R
import br.com.newsapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.nav_host) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)
        drawerLayout = binding.container
        NavigationUI.setupActionBarWithNavController(this, controller, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, controller)
        controller.addOnDestinationChangedListener{_, destination, _->
            title = destination.label
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(controller, drawerLayout)
    }

}