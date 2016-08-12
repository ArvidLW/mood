package com.study.zlwm.mood.redefview;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by NiceWang on 2016/7/7.
 * 主要用于设置字体
 */
public class HYSGTextView extends TextView{

    public HYSGTextView(Context context) {
        super(context);
        init(context);
    }

    public HYSGTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HYSGTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        AssetManager as = context.getAssets();
        Typeface font = Typeface.createFromAsset(as, "fonts/HYShiGuangTiW.ttf");
        setTypeface(font);
    }

}
