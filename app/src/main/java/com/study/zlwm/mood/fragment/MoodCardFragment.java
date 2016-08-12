package com.study.zlwm.mood.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.zlwm.mood.R;

/**
 * Created by 31351 on 2016/8/11.
 */
public class MoodCardFragment extends Fragment{
    private int drawable_mood;
    private int color;
    public static MoodCardFragment newInstance(int drawable_mood, int color){
        MoodCardFragment fragment=new MoodCardFragment();
        Bundle args=new Bundle();
        args.putInt("drawable_mood",drawable_mood);
        args.putInt("color",color);
        fragment.setArguments(args);
        return fragment;
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.mood_card_fragment,container,false);
        view.setBackgroundColor(getResources().getColor(color));
        ImageView imageView= (ImageView) view.findViewById(R.id.mood_face);
        //imageView.setImageDrawable(getResources().getDrawable(drawable_mood));//outofmemory
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            drawable_mood = args.getInt("drawable_mood");
            color=args.getInt("color");
        }
    }
}
