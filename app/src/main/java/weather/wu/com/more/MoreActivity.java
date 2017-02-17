package weather.wu.com.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
import weather.wu.com.more.fragment.TodayInHistoryFragment;
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
        //getHistoryJsonData();
    }
    private void initView(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.more_ic_nav_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TodayInHistoryFragment todayInHistoryFragment = new TodayInHistoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment_content,todayInHistoryFragment);
        fragmentTransaction.commit();
    }
/*    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }*/


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
