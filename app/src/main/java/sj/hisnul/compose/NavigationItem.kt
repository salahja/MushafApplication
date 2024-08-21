package sj.hisnul.compose

import com.example.mushafconsolidated.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_web_home_24, "Home")
    object Music : NavigationItem("music", R.drawable.ic_dua_one, "Music")
    object Movies : NavigationItem("movies", R.drawable.resume_icon_24, "Movies")
    object Books : NavigationItem("books", R.drawable.ic_book, "Books")
    object Profile : NavigationItem("profile", R.drawable.ic_baseline_bookmark_add_24, "Profile")
}