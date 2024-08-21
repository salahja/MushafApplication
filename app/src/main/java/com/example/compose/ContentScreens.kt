package com.example.compose

import android.content.res.TypedArray
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.utility.QuranGrammarApplication.Companion.context
import com.skyyo.expandablelist.theme.AppThemeSettings.isDarkThemeEnabled
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //   .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            //   color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun MusicScreen(chapid: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //    .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusicScreenPreview() {
    MusicScreen(1)
}

@Composable
fun MoviesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //  .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Movies View",
            fontWeight = FontWeight.Bold,
            //   color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesScreen()
}


@Composable
fun BottomSheetDemos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //  .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Books View",
            fontWeight = FontWeight.Bold,
            //   color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BottomSheetDemos()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //  .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun GridScreens(navController: NavHostController) {
    val imgs = context!!.resources.obtainTypedArray(R.array.sura_imgs)
    val utils = Utils(context)
    val allAnaChapters = utils.getAllAnaChapters()!!;
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        content = {
            items(allAnaChapters!!.size) { index ->
                GridList(allAnaChapters[index], imgs, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
private
@Composable

fun GridList(
    surahModelList: ChaptersAnaEntity?,
    imgs: TypedArray,
    navController: NavHostController,
) {
    val img = imgs.getDrawable(surahModelList!!.chapterid.toInt() - 1)
    val darkThemeEnabled = isDarkThemeEnabled

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


            if (!darkThemeEnabled) {
                AsyncImage(

                    model = img,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Cyan),
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),

                    )
            } else {
                AsyncImage(

                    model = img,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Red),
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),

                    )
            }


        }
    }


    @Suppress("DEPRECATION")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @ExperimentalFoundationApi
    @Composable
    fun BottomDialog() {
        //Lets create list to show in bottom sheet
        data class BottomSheetItem(val title: String, val icon: ImageVector)

        val bottomSheetItems = listOf(
            BottomSheetItem(title = "Notification", icon = Icons.Default.Notifications),
            BottomSheetItem(title = "Mail", icon = Icons.Default.MailOutline),
            BottomSheetItem(title = "Scan", icon = Icons.Default.Search),
            BottomSheetItem(title = "Edit", icon = Icons.Default.Edit),
            BottomSheetItem(title = "Favorite", icon = Icons.Default.Favorite),
            BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
        )

        //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        val coroutineScope = rememberCoroutineScope()
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(topEnd = 30.dp),
            sheetContent = {
                //Ui for bottom sheet
                Column(
                    content = {

                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(
                            text = "Bottom Sheet",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            color = Color.White
                        )
                        LazyVerticalGrid(
                            //cells = GridCells.Fixed(3)
                            columns = GridCells.Fixed(3), //https://developer.android.com/jetpack/compose/lists
                        ) {
                            items(bottomSheetItems.size, itemContent = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp)
                                        .clickable {


                                        },
                                ) {
                                    Spacer(modifier = Modifier.padding(8.dp))
                                    Icon(
                                        bottomSheetItems[it].icon,
                                        bottomSheetItems[it].title,
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                    Text(text = bottomSheetItems[it].title, color = Color.White)
                                }

                            })
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
            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Click to show Bottom Sheet"
                    )
                }
            }
        }
    }
}