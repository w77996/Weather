package weather.wu.com.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/1/4.
 */
public class GuideFirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText("weather");
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);*/
      /*  String VIDEO_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + getContext().getPackageName() + File.separator + "databases" + File.separator;*/
        View view = inflater.inflate(R.layout.guide_fragment1,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.guide1_img);
        Glide.with(getActivity()).load(R.drawable.video1).asGif().error(R.drawable.ic_default_big).into(imageView);
        return view;
    }

}
