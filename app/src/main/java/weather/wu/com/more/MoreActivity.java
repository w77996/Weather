package weather.wu.com.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.more.bean.TodayInHistory;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.more_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.more_fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.more_drawlayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.more_nav)
    NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        initView();
        Logger.d("ddd");
        getHistoryJsonData();
    }
    private void initView(){
        setSupportActionBar(mToolbar);
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void getHistoryJsonData() {
        long now = System.currentTimeMillis();
        String currentTime = SystemUtils.getCurrentTime();
        String currentDate = SystemUtils.getCurrentDate();
        String currentMonth = SystemUtils.getCurrentMonth();
        //String url = "http://route.showapi.com/119-42?data=" + currentDate + "&showapi_appid=28198&showapi_timestam" + currentTime + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9";
       String url ="http://api.juheapi.com/japi/toh?v=&month="+currentMonth+"&day="+currentDate+"&key=02351f897b139cc86e39a225aaeaa42d";
        Logger.d(url);
        Logger.d(now + " " + currentTime + " " + currentDate);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject resultJson = new JSONObject(json);
                    String errorcode = resultJson.getInt("error_code") + "";
                    if ("0".equals(errorcode)) {
                        Gson gson = new Gson();
                        TodayInHistory todayInHistory = gson.fromJson(json, TodayInHistory.class);
                        Logger.d(todayInHistory.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Logger.d(json);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.more_nav_history:
                break;
            case R.id.more_nav_news:
                break;
            case R.id.more_nav_pic:
                break;
            case R.id.more_nav_else:
                break;
        }
        return false;
    }
}
