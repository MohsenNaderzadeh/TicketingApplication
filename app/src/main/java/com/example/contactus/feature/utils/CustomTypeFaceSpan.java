package com.example.contactus.feature.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import android.util.TypedValue;

import androidx.annotation.ColorInt;

public class CustomTypeFaceSpan extends TypefaceSpan {

    private static float SIXTHEEN_DP;
    private final Typeface newType;
    private final int mColor;


    public CustomTypeFaceSpan(String family, Typeface type, @ColorInt int color, Context context) {

        super(family);
        newType = type;
        mColor = color;

        SIXTHEEN_DP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, context.getResources().getDisplayMetrics());
    }

    private static void applyCustomTypeFace(TextPaint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(tf);
        paint.setTextSize(SIXTHEEN_DP);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mColor);
        applyCustomTypeFace(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    @Override
    public int getSpanTypeId() {
        return super.getSpanTypeId();
    }

    @ColorInt
    public int getForegroundColor() {
        return mColor;
    }
}
