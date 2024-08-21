package com.example.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.style.CharacterStyle;

public abstract class TextColourSpan extends CharacterStyle {
    public static TextColourSpan newInstance(Context context, int resourceId) {
        Resources resources = context.getResources();
        ColorStateList colorStateList = resources.getColorStateList(resourceId);
        return new ColourStateListSpan(colorStateList);
    }
}
