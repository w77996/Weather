package weather.wu.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2017/1/4.
 */
public class GuideSecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText("毕业设计");
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(2000);
        animationSet.addAnimation(scaleAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-200);
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);

        text.setAnimation(animationSet);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);*/
        View view = inflater.inflate(R.layout.guide_fragment2,container,false);
        return view;
    }


}
