package br.com.newsapi.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import br.com.newsapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.nav_host) }
    private val actionBar by lazy{supportActionBar}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.addOnDestinationChangedListener{_, destination, _->
            title = destination.label
            when(destination.id){
                R.id.newsFragment -> actionBar?.setDisplayHomeAsUpEnabled(false)
                else -> actionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return false
    }
}