package weather.wu.com.weather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    private int mHeaderHeight = -1;
    RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
       //  = SystemUtils.getDisplayHeight(getActivity());
        mRelativeLayout = (RelativeLayout)findViewById(R.id.rl);
        WindowManager wm = (WindowManager) MainActivity.this
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int displayHeight = dm.heightPixels;
        Log.e("Log displayHeight",displayHeight+"");
        // HeaderView高度=屏幕高度-标题栏高度-状态栏高度
        mHeaderHeight = displayHeight
                - getResources().getDimensionPixelSize(
                R.dimen.abs__action_bar_default_height)
                ;
        mRelativeLayout.setLayoutParams(new LinearLayout.LayoutParams(
               LinearLayout.LayoutParams.MATCH_PARENT, mHeaderHeight));
    }
}
