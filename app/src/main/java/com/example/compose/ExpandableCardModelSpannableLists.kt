package com.example.compose

import android.text.SpannableString
import androidx.compose.runtime.Immutable

@Immutable
data class ExpandableCardModelSpannableLists(
    val id: Int,
    val title: String,
    val lemma: String,
    val vlist: ArrayList<SpannableString>
)