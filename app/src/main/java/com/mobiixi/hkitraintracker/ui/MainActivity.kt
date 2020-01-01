package com.mobiixi.hkitraintracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import butterknife.ButterKnife
import com.mobiixi.hkitraintracker.R
import com.mobiixi.hkitraintracker.dagger.CoreComponentInjector
import com.mobiixi.hkitraintracker.dagger.app.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar)

        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // to better support "up" navigation with navigation UI
    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}