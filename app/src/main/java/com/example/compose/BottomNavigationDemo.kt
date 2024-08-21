import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable

fun BottomNavigationDemo(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Favorites,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        label = { Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationRoute!!)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable

fun NavigationDemoApp() {
    val navController = rememberNavController()


    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            BottomNavigationDemo(navController)
        }
    }

}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

@Composable
fun HomeScreen() {
    Text(text = "Home Screen", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

@Composable
fun FavoritesScreen() {
    Text(text = "Favorites Screen", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

@Composable
fun ProfileScreen() {
    Text(text = "Profile Screen", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    NavigationDemoApp()
}
