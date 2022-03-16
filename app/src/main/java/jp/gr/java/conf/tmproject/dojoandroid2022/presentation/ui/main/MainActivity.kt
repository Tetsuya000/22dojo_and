package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.MainActivityBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setUpNavController()
    }

    private fun setUpNavController() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHost.navController

        val bottomNav = binding.bottomNav
        setupWithNavController(bottomNav, navController)
    }
}
