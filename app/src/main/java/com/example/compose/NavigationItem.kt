package com.example.compose

import com.example.mushafconsolidated.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_book, "Home")
    object Music : NavigationItem("music", R.drawable.baseline_audiotrack_24, "Music")
    object Movies : NavigationItem("movies", R.drawable.ic_action_voice_search, "Movies")
    object Books : NavigationItem("books", R.drawable.ic_book, "Books")
    object Profile : NavigationItem("profile", R.drawable.ic_baseline_construction_24, "Profile")
}