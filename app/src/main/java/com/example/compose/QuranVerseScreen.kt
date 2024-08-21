@file:OptIn(ExperimentalFoundationApi::class)

package com.example.compose


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.justJava.MyTextViewZoom
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication


@Composable
fun QuranVerseScreen(navController: NavHostController, chapid: Int) {
    var allofQuran: List<QuranEntity>? = null

    val imgs = QuranGrammarApplication.context!!.resources.obtainTypedArray(R.array.sura_imgs)
    val utils = Utils(QuranGrammarApplication.context)
    val allAnaChapters = utils.getAllAnaChapters()!!;
    val quranbySurah = utils.getQuranbySurah(1)
    var newnewadapterlist = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
    var corpusSurahWord: List<QuranCorpusWbw>? = null

    val corpus = CorpusUtilityorig
    corpusSurahWord = utils.getQuranCorpusWbwbysurah(1);

    newnewadapterlist = corpus.composeWBWCollection(quranbySurah, corpusSurahWord)

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
        content = {
            items(quranbySurah!!.size) { index ->
                VerseList(quranbySurah!![index], navController, newnewadapterlist[index])


            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun VerseList(
    quran: QuranEntity,

    navController: NavHostController,
    wbw: ArrayList<NewQuranCorpusWbw>?
) {
    Card(

        //   onCardArrowClick = { viewModel.onCardArrowClicked(card.id) },
        /*       onClick = { surahModelList.chapterid

                   navController.navigate(NavigationItem.Music.route)


               },*/
        onClick = {

            navController.navigate(
                NavigationItem.Music.route
                    //   "detail/{uId}" //Just modify your route accordingly
                    .replace(
                        oldValue = "{uId}",
                        newValue = "1"
                    )
            )
        },


        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),


        modifier = Modifier
            .fillMaxWidth()

            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            )
    )


    {
        RightToLeftLayout {


            FlowRow(


                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 6,
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    )


            ) {
                if (wbw != null) {
                    var counter = 0
                    //  for (counter in wbw.size - 1 downTo 0) {
                    wbw.forEach {


                        val str =
                            wbw!![counter].corpus!!.araone.toString() + wbw!![counter].corpus!!.aratwo.toString() +
                                    wbw!![counter].corpus!!.arathree.toString() + wbw!![counter].corpus!!.arafour.toString() +
                                    wbw!![counter].corpus!!.arafive.toString() + "\n" + "\n" + wbw[counter++].wbw?.en.toString()


                        /*  Text(

                              text = str, style = MaterialTheme.typography.headlineSmall.copy(
                                  fontWeight = FontWeight.Light,
                                  textDirection = TextDirection.Rtl,

                                  textAlign = TextAlign.Justify,

                                  fontFamily = qalam

                              )
                          )*/


                        ClickableText(
                            text = AnnotatedString(str),
                            onClick = {
                                Log.d(MyTextViewZoom.TAG, "mode=ZOOM")
                                navController.navigate(NavigationItem.Books.route)

                            })


                        /*


                                           Text(
                                               text = strs, style = MaterialTheme.typography.headlineSmall.copy(
                                                   fontWeight = FontWeight.Light,
                                                   textDirection = TextDirection.Ltr,
                                                   textAlign = TextAlign.Center,
                                                   fontFamily = qalam

                                               )
                                           )
                        */


                    }
                }
            }

        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()

                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )

        ) {
            Text(

                text = quran!!.qurantext.toString(),
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )
        ) {

            Text(

                text = quran!!.translation,
                fontSize = 20.sp
            )

        }


    }


}


@Composable
fun RightToLeftLayout(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}
