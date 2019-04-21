package com.myres.noban.mykotlinmvpproject.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

import com.myres.noban.mykotlinmvpproject.R;

/**
 * Created by Rafiqul Hasan Rony on 2/6/19.
 * Audacity It Solution.
 */
public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int fontFlag;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextViewCustomFont,
                0, 0);

        try {

            fontFlag = a.getInteger(R.styleable.TextViewCustomFont_custom_font, 1);
            Typeface tf = null;

            if (fontFlag == 0) {
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/light.ttf");
            } else if (fontFlag == 1)
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/regular.ttf");
            else if (fontFlag == 2)
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/medium.ttf");
            else if (fontFlag == 3)
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/bold.ttf");
            else if (fontFlag == 4)
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/condensed.ttf");
            else
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/regular.ttf");

            setTypeface(tf);

        } finally {
            a.recycle();
        }


    }
}
