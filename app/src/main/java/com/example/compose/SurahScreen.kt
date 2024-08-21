import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.preference.PreferenceManager
import com.example.compose.SurahModelList
import com.example.mushafconsolidated.R
import com.example.surahdisplaycompose.SurahViewModel
import com.example.utility.QuranGrammarApplication.Companion.context
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun SurahScreen(viewModel: SurahViewModel) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context!!
    )


    //grammatically colred word default font
    val arabic_font_selection =
        sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
    // val words by wordoccuranceModel.words.collectAsStateWithLifecycle()
    val cards by viewModel.cards.collectAsStateWithLifecycle()
    //   val expandedCardIds by viewModel.sur.collectAsStateWithLifecycle()
    val context = LocalContext.current

    viewModel.open.value = true


    val bgColour = remember {
        Color(ContextCompat.getColor(context, R.color.colorDayNightWhite))
    }

    Scaffold(
        Modifier.background(bgColour)

    ) { paddingValues ->
        //  val copyProgress: MutableState<Float> = remember { mutableStateOf(0.0f) }


        LazyColumn(Modifier.padding(paddingValues)) {
            items(cards) { card ->
                ElevatedCardExample(


                    cards
                )
            }


        }


    }
}

@Preview
@Composable
fun MyViewPreviews() {
    ElevatedCardExample(cards = ArrayList<SurahModelList>())
}


@Composable

fun ElevatedCardExample(cards: List<SurahModelList>) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 640.dp, height = 200.dp)
            .padding(20.dp)
    )

    {

        Row {


            cards.forEach { surah ->

                Text(

                    text = surah.id.toString(),
                    color = Color.Black,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp
                    )


                )
                Text(
                    text = surah.arabicSurahname.toString(),
                    color = Color.Black,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp
                    )
                )
                Text(
                    text = surah.englishsurahname.toString(),
                    color = Color.Black,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp
                    )
                )
            }


            // on below line we are specifying
            // divider for each list item
            androidx.compose.material3.Divider(color = Color.Red, thickness = 3.dp)
        }

        androidx.compose.material3.Divider()


    }



    @Composable
    fun LazyVerticalGridDemo(cards: List<SurahModelList>) {

        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {


                items(cards.size) {
                    Card(
                        backgroundColor = Color.Red,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        elevation = 8.dp,
                    )
                    {
                        Text(
                            text = "$it",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            })


    }

}


@Composable
fun GridScreen(cards: List<SurahModelList>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        content = {
            items(cards.size) { index ->
                GridIcon(cards[index])
            }
        }
    )


}

@Composable


fun GridIcon(cards: SurahModelList) {
    Column {


        Row {
            Text(
                text = cards.id.toString(),
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = cards.arabicSurahname.toString(),
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = cards.englishsurahname.toString(),
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
        }


        // on below line we are specifying
        // divider for each list item
        androidx.compose.material3.Divider(color = Color.Red, thickness = 3.dp)

    }
}














