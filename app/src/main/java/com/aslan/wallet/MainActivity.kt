package com.aslan.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aslan.wallet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView)
        AppBarConfiguration(setOf(
            R.id.walletFragment,
            R.id.analyticsFragment,
            R.id.reportFragment,
        ))

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.reportFragment -> binding.bottomBar.visibility = View.GONE
                else -> binding.bottomBar.visibility = View.VISIBLE
            }
        }

        binding.bottomBar.setupWithNavController(navController)

    }
}