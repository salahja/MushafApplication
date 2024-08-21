package com.example.compose

import android.content.res.TypedArray
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.NewQuranMorphologyDetails
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.QuranGrammarApplication
import com.skyyo.expandablelist.theme.AppTheme

lateinit var worddetails: HashMap<String, SpannableStringBuilder?>

class BottomCompose : AppCompatActivity() {
    private val viewModel by viewModels<QuranVIewModel>()
    private lateinit var allAnaChapters: List<ChaptersAnaEntity?>
    private lateinit var imgs: TypedArray
    lateinit var mainViewModel: QuranVIewModel
    //  allofQuran = mainViewModel.getquranbySUrah(chapterno).value

    private val quranModel by viewModels<QuranVIewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val bundle: Bundle? = intent.extras
            //    root = bundle?.getString(Constant.QURAN_VERB_ROOT)
            //  val defArgs = bundleOf("root" to root)

            val enableDarkMode = remember { mutableStateOf(false) }
            AppTheme() {


                MainScreen()


            }


        }


    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        //backgroundColor = colorResource(id =colorPrimaryDark),
        // backgroundColor = colorResource(R.color.colorPrimaryDark) // Set background color to avoid the white flashing when you switch between screens
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            val activity = LocalContext.current as? AppCompatActivity
            GridScreens(navController)
        }
        composable(NavigationItem.Music.route) {

            val chapid = 1
            QuranVerseScreen(navController, chapid)
        }

        composable(NavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(NavigationItem.Books.route) {
            BottomSheetDemo(viewModel())
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun BottomSheetDemo(mainViewModel: QuranVIewModel) {
    val utils = Utils(QuranGrammarApplication.context)
    val quranCorpusWord = utils.getQuranCorpusWord(1, 2, 1)
    val corpusSurahWord = mainViewModel.getQuranCorpusWbw(1, 2, 1).value

    var vbdetail = HashMap<String, String?>()
    val quran = mainViewModel.getsurahayahVerseslist(1, 2).value
    val corpusNounWord = mainViewModel.getNouncorpus(1, 2, 1).value

    val verbCorpusRootWord =
        mainViewModel.getVerbRootBySurahAyahWord(1, 2, 1).value


    val am = NewQuranMorphologyDetails(
        corpusSurahWord!!,
        corpusNounWord as ArrayList<NounCorpus>?,
        verbCorpusRootWord as ArrayList<VerbCorpus>?,
        QuranGrammarApplication.context
    )
    worddetails = am.wordDetails
    // wordbdetail = am.wordDetails
    if (verbCorpusRootWord != null) {
        if (verbCorpusRootWord.isNotEmpty() && verbCorpusRootWord[0].tag.equals("V")) {
            vbdetail = am.verbDetails
            //  isVerb = true
        }
    }


    //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 30.dp),
        sheetContent = {
            //Ui for bottom sheet
            Column(
                content = {

                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = worddetails["surahid"].toString() + worddetails["ayahid"].toString() + worddetails["wordno"].toString(),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    Row {

                        if (worddetails["PRON"] != null) {
                            Text(
                                text = worddetails["PRON"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )
                        }
                    }
                    Row {

                        if (worddetails["worddetails"] != null) {
                            Text(
                                text = worddetails["worddetails"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )
                        }
                    }




                    Row {
                        if (worddetails["noun"] != null) {
                            Text(
                                text = "Noun" + worddetails["noun"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )
                        }
                    }

                    Row {
                        if (worddetails["lemma"] != null || worddetails["lemma"]!!.isNotEmpty()) {
                            Text(
                                text = "Lemma" + worddetails["lemma"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )

                        }
                    }
                    Row {
                        if (worddetails["arabicword"] != null) {
                            Text(
                                text = "ArabicWord" + worddetails["arabicword"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )
                        }
                    }
                    Row {
                        if (worddetails["translation"] != null) {
                            Text(
                                text = "Translation" + worddetails["translation"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )
                        }
                    }



                    Row {
                        if (worddetails["root"] != null) {
                            Text(
                                text = "Root:" + worddetails["root"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )

                        }
                    }
                    Row {
                        if (worddetails["formnumber"] != null) {
                            Text(
                                text = worddetails["form"].toString(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                color = Color.White
                            )


                        }
                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)

                    //.background(Color(0xFF6650a4))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8E2DE2),
                                Color(0xFF4A00E0)
                            )
                        ),
                        // shape = RoundedCornerShape(cornerRadius)
                    )
                    .padding(16.dp),

                )
        },
        sheetPeekHeight = 0.dp,

        ) {


        //Add button to open bottom sheet

    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 18.sp
            )
        },
        //  backgroundColor = colorResource(id = colorPrimary),


        backgroundColor = MaterialTheme.colorScheme.inversePrimary,
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    BottomNavigation(
        //  backgroundColor = colorResource(id = R.color.colorPrimary),

        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },

                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    // BottomNavigationBar()
}


