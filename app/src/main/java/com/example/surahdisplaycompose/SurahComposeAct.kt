package com.example.surahdisplaycompose


import android.annotation.SuppressLint
import android.app.Application
import android.content.res.TypedArray
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose.MainScreen
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.skyyo.expandablelist.theme.AppTheme
import kotlinx.coroutines.CoroutineScope


class SurahComposeAct : BaseActivity() {


    private lateinit var allAnaChapters: List<ChaptersAnaEntity?>
    private lateinit var imgs: TypedArray


    //val darkTheme = themeViewModel.darkTheme.observeAsState(initial = true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   val listofSurah = viewModel.getAllChapters()

        imgs = this.resources.obtainTypedArray(R.array.sura_imgs)
        val utils = Utils(this)
        allAnaChapters = utils.getAllAnaChapters()!!;
        setContent {

            val bundle: Bundle? = intent.extras
            //    root = bundle?.getString(Constant.QURAN_VERB_ROOT)
            //  val defArgs = bundleOf("root" to root)


            AppTheme {
                Surface(color = MaterialTheme.colorScheme.primary) {

                    MyScaffold()


                }
            }


        }


    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    @Preview
    fun MyScaffold() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val scope: CoroutineScope = rememberCoroutineScope()
        val navController: NavHostController = NavHostController(this)
        Scaffold(
            scaffoldState = scaffoldState,

            content = { GridScreens() },
            topBar = { MyTopAppBar(scaffoldState = scaffoldState, scope = scope) },
            bottomBar = { MainScreen() }

        )
    }


    @Composable

    private fun GridScreens() {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            content = {
                items(allAnaChapters!!.size) { index ->
                    GridList(allAnaChapters[index])
                }
            }
        )
    }

    private @Composable

    fun GridList(surahModelList: ChaptersAnaEntity?) {
        val img = imgs.getDrawable(surahModelList!!.chapterid.toInt() - 1)


        Card(

            colors = CardDefaults.cardColors(
                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),


            modifier = Modifier
                .fillMaxWidth()

                .padding(
                    horizontal = 20.dp,
                    vertical = 8.dp
                )
        )
        {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {

                Text(

                    text = surahModelList!!.chapterid.toString(),
                    fontSize = 20.sp
                )
                Text(

                    text = surahModelList!!.namearabic,
                    fontSize = 20.sp
                )
                Text(

                    text = surahModelList.nameenglish,
                    fontSize = 10.sp
                )

                AsyncImage(

                    model = img,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Cyan),
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),

                    )


            }
        }


    }


    class MyViewModelFactory(
        private val mApplication: Application,

        ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return SurahViewModel(mApplication) as T
        }
    }
}


