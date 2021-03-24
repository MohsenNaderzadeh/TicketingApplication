package com.example.contactus.feature.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;

import androidx.core.content.res.ResourcesCompat;

import com.example.contactus.R;

public class MenuUtils {
    public static void applyFontToMenuItem(MenuItem mi, Context context) {

        Typeface typeface = ResourcesCompat.getFont(context, R.font.iranyekanbold);
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypeFaceSpan("", typeface, Color.BLACK, context), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
