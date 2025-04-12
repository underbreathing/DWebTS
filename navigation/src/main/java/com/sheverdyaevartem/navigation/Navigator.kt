package com.sheverdyaevartem.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

interface Navigator {
    var navHostFragment: NavHostFragment
    var navController: NavController

    fun goto(dest: Int)

    fun goto(uri: Uri)

    fun goto(dest: Int, graphId: Int) {
        setGraph(graphId)
        goto(dest)
    }

    fun goto(uri: Uri, graphId: Int) {
        setGraph(graphId)
        goto(uri)
    }

    fun graphSpecificNavigation(graphId: Int)

    private fun setGraph(graphId: Int) {
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(graphId)
        navHostFragment.navController.graph = graph
    }
}
