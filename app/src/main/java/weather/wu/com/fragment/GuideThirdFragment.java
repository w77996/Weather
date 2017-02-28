package weather.wu.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import weather.wu.com.activity.GudieActivity;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;
import weather.wu.com.weather.WeatherActivity;

/**
 * Created by Administrator on 2017/1/6.
 */
public class GuideThirdFragment extends Fragment {
    ImageView imageView ;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      /*  TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText("吴海辉");
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);*/
        View view = inflater.inflate(R.layout.guide_fragment3,container,false);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.guide_fragment3_linearlayout);
        //button = (Button)view.findViewById(R.id.guide3_btn);

       // imageView = (ImageView)view.findViewById(R.id.guide3_img);
      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utility.isNetworkConnected(getActivity())){
                    getActivity().startActivity(new Intent(getActivity(),WeatherActivity.class));
                    getActivity().finish();
                }else{
                    //SystemUtils.showDialog(getActivity());
                    Toast.makeText(getActivity(),"网络连接无可用，请开启网络",Toast.LENGTH_SHORT);
                }
            }
        });*/
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dddddddd");
                if(Utility.isNetworkConnected(getContext())){
                   *//* getActivity().startActivity(new Intent(getActivity(),WeatherActivity.class));
                    getActivity().finish();*//*
                    Toast.makeText(getContext(),"网络连接无可用，请开启网络",Toast.LENGTH_SHORT).show();
                }else{
                   *//* SystemUtils.showDialog(getActivity().getApplicationContext());
                    System.out.println("dddddddd");*//*
                    Toast.makeText(getActivity().getApplication(),"网络连接无可用，请开启网络",Toast.LENGTH_SHORT).show();
                }

            }
        });*/
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utility.isNetworkConnected(getActivity())){
                    getActivity().startActivity(new Intent(getActivity(),WeatherActivity.class));
                    //Toast.makeText(getActivity().getApplication(),"网络连接无可用，请开启网络",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    //SystemUtils.showDialog(getActivity());
                }else{
                    SystemUtils.showDialog(getActivity());
                    Toast.makeText(getActivity(),"网络连接无可用，请开启网络",Toast.LENGTH_SHORT).show();
                }

            }
        });
      /*  LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);*/
        return view;
    }
}
