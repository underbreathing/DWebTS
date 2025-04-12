package com.sheverdyaevartem.dwebts

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.sheverdyaevartem.navigation.Navigator

class MainActivity : AppCompatActivity(), Navigator {

    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun goto(dest: Int) {
        navController.navigate(dest)
    }

    override fun goto(uri: Uri) {
        //or using NavDeepLinkRequest
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri)
            .build()
        navController.navigate(request)

    }

    override fun graphSpecificNavigation(graphId: Int) {
        val myNavHostFragment = navHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(graphId)
        navController.graph.addAll(graph)
    }
}
