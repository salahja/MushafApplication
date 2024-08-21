package com.example.compose

import BottomNavigationDemo
import Screen
import android.app.Application
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.skyyo.expandablelist.theme.AppTheme


class BottomComposeAct : AppCompatActivity() {
    // val darkTheme = themeViewModel.darkTheme.observeAsState(initial = true)
    private val cardsViewModel by viewModels<CardsViewModel>()

    var root: String? = null

    //   private val viewModel by viewModels<ExpandableListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val listOf = listOf(
                Screen.Home,
                Screen.Favorites,
                Screen.Profile
            )


            val navController: NavHostController = NavHostController(this)

            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {


                    BottomNavigationDemo(navController)


                }
            }


        }


    }

    class MyViewModelFactory(
        private val mApplication: Application,
        private val verbroot: String,
        private val nounroot: String,
        private val isharf: Boolean
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return CardsViewModel(mApplication, verbroot, nounroot, isharf) as T
        }
    }
}

