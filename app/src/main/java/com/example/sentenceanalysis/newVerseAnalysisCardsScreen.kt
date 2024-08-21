package com.example.sentenceanalysis


import EXPANSTION_TRANSITION_DURATION
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.preference.PreferenceManager
import com.codelab.basics.ui.theme.cardCollapsedBackgroundColor
import com.codelab.basics.ui.theme.cardExpandedBackgroundColor
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable

fun NewVerseAnalysisCardsScreen(


    viewModel: ExpandableVerseViewModel,


    ) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        QuranGrammarApplication.context!!
    )

    //  var loading = versemodel.loading.value

    //grammatically colred word default font
    val arabic_font_selection =
        sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
    // val words by wordoccuranceModel.words.collectAsStateWithLifecycle()
    // val cards by viewModel.roots.collectAsStateWithLifecycle()
    val cards by viewModel.items.collectAsStateWithLifecycle()
    val expandedCardIds by viewModel.expandedCardIdsList.collectAsStateWithLifecycle()


    val context = LocalContext.current
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }


    val bgColour = remember {
        Color(ContextCompat.getColor(context, R.color.colorDayNightWhite))
    }

    Scaffold(
        Modifier.background(bgColour)

    ) { paddingValues ->
        val copyProgress: MutableState<Float> = remember { mutableFloatStateOf(0.0f) }
        //   loading = viewModel.loading.value
        //   WordOccuranceLoading(isDisplayed = loading)
        LazyColumn(Modifier.padding(paddingValues)) {
            items(cards, ExpandableVerseViewModel.VerseAnalysisModel::id) { card ->
                ExpandableVerseCard(
                    card = card,
                    onCardArrowClick = { viewModel.onCardArrowClicked(card.id) },
                    expanded = expandedCardIds.contains(card.id),
                )
            }


        }


    }
}
/*
card: ExpandableVerseViewModel.VerseAnalysisModel,
onCardArrowClick: () -> Unit,
expanded: Boolean
@Composable
fun ExpandableVerseCard(
*/

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable

fun ExpandableVerseCard(
    card: ExpandableVerseViewModel.VerseAnalysisModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val cardBgColor by transition.animateColor({

        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "bgColorTransition") {
        if (expanded) cardExpandedBackgroundColor else cardCollapsedBackgroundColor
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "paddingTransition") {
        if (expanded) 48.dp else 24.dp
    }
    val cardElevation: Dp by transition.animateDp({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "elevationTransition") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = EXPANSTION_TRANSITION_DURATION,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }
    val context = LocalContext.current
    val contentColour = remember {
        Color(ContextCompat.getColor(context, R.color.colorDayNightPurple))
    }

    Card(
        //backgroundColor = cardBgColor,
        //  contentColor = contentColour,

        /* colors = CardDefaults.cardColors(
             containerColor = MaterialTheme.colorScheme.primaryContainer,
         ),*/
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(),

            ) {
            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                CardTitle(title = card.grammarrule)
            }


            //   MyScreen(visible = expanded)
            ExpandableVerseContent(card.result, visible = expanded)
            //     ExpandableContent( visible = expanded)
        }
    }
}


@Composable

fun ExpandableVerseContent(


    card: List<AnnotatedString>,
    visible: Boolean = true,
//    viewModel: LemmaViewModel = viewModel(),


//  viewModel: Any = viewModel<LemmaViewModel>()

) {
    // val versemodel= viewModel(modelClass = DuaViewModel::class.java)
    // val versstate=versemodel.state
    // val duanames = versstate.duanames
    //  versemodel.state.duanames.filterbyid()

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.padding(8.dp))


        {


            card.forEach { verses ->
                val annotatedString = verses

                Text(
                    text = annotatedString, style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Light,
                        textDirection = TextDirection.ContentOrRtl,
                        textAlign = TextAlign.Center,
                        //   fontFamily = indopak

                    )


                )

                // on below line we are specifying
                // divider for each list item
                Divider(color = Color.Red, thickness = 0.5.dp)
            }
            Divider()
        }

        /*
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.heightIn(100.dp))
            Text(
           //  text =card.verseslist.get(0),
                text=card.get(0),
             //   text="expan",
                textAlign = TextAlign.Center
            )

        }*/
    }


}


@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable

fun CardArrow(
    degrees: Float,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
            )
        }
    )
}
