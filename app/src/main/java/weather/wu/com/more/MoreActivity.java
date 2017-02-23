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
import android.view.View;
import android.view.Window;

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
import weather.wu.com.more.fragment.BluetoothFragment;
import weather.wu.com.more.fragment.JokeFragment;
import weather.wu.com.more.fragment.NewsFragment;
import weather.wu.com.more.fragment.PictureFragment;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MoreActivity extends AppCompatActivity {
  /*  @BindView(R.id.more_toolbar)*/
    Toolbar mToolbar;
  /*  @BindView(R.id.more_fab)
    FloatingActionButton mFloatingActionButton;*/
  //  @BindView(R.id.more_drawlayout)
    DrawerLayout mDrawerLayout;
   // @BindView(R.id.more_nav)
    NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more);

        initView();
        Logger.d("ddd");
        //getHistoryJsonData();
    }
    private void initView(){
        mNavigationView = (NavigationView)findViewById(R.id.more_nav);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.more_drawlayout);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              //  Logger.d(item.getItemId()+"");
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.more_nav_news:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        NewsFragment newsFragment = new NewsFragment();
                        fragmentTransaction.replace(R.id.more_fragment_content,newsFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.more_nav_joke:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        JokeFragment jokeFragment = new JokeFragment();
                        fragmentTransaction.replace(R.id.more_fragment_content,jokeFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.more_nav_pic:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        PictureFragment pictureFragment = new PictureFragment();
                        fragmentTransaction.replace(R.id.more_fragment_content,pictureFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.more_nav_else:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        BluetoothFragment bluetoothFragment = new BluetoothFragment();
                        fragmentTransaction.replace(R.id.more_fragment_content,bluetoothFragment);
                        fragmentTransaction.commit();
                        break;
                }
                return false;
            }
        });
         mToolbar = (Toolbar)findViewById(R.id.more_toolbar);
       setSupportActionBar(mToolbar);
         ActionBar actionBar = getSupportActionBar();

        mToolbar.setTitleTextColor(getResources().getColor(R.color.md_white_color_code));

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.more_ic_nav_menu);

        }
       /* actionBar.setHomeButtonEnabled(true); //设置返回键可用
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle("Weather");*/
       // mToolbar.setTitle("");
       /* NewsFragment newsFragment = new NewsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment_content,newsFragment);
        fragmentTransaction.commit();*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewsFragment newsFragment = new NewsFragment();
        fragmentTransaction.replace(R.id.more_fragment_content,newsFragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d((item.getItemId())+"");
        if(item.getItemId()==android.R.id.home){
            if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawers();
            }else{
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);

    }

/* // @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Logger.d(item.getItemId()+"");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()){
            case R.id.more_nav_news:
                NewsFragment newsFragment = new NewsFragment();
                fragmentTransaction.replace(R.id.more_fragment_content,newsFragment);
                fragmentTransaction.commit();
                break;
            case R.id.more_nav_joke:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                JokeFragment jokeFragment = new JokeFragment();
                fragmentTransaction.replace(R.id.more_fragment_content,jokeFragment);
                fragmentTransaction.commit();
                break;
            case R.id.more_nav_pic:
                break;
            case R.id.more_nav_else:
                break;
        }
        return false;
    }*/
}
