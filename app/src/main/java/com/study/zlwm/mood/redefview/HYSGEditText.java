package com.study.zlwm.mood.redefview;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by NiceWang on 2016/7/7.
 */
public class HYSGEditText extends EditText{

    public HYSGEditText(Context context) {
        super(context);
        init(context);
    }

    public HYSGEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HYSGEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        AssetManager as = context.getAssets();
        Typeface font = Typeface.createFromAsset(as, "fonts/HYShiGuangTiW.ttf");
        setTypeface(font);
    }

}
