package com.example.text;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

public final class SpanUtils {
    private SpanUtils() {
    }

    public static CharSequence createSpannable(Context context, int stringId, Pattern pattern, CharacterStyle... styles) {
        String string = context.getString(stringId);
        return createSpannable(string, pattern, styles);
    }

    public static CharSequence createSpannable(CharSequence source, Pattern pattern, CharacterStyle... styles) {
        SpannableString spannableString = new SpannableString(source);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            applyStylesToSpannable(spannableString, start, end, styles);
        }
        return spannableString;
    }

    private static SpannableString applyStylesToSpannable(SpannableString source, int start, int end, CharacterStyle... styles) {
        for (CharacterStyle style : styles) {
            source.setSpan(CharacterStyle.wrap(style), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return source;
    }
}
