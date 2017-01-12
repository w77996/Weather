package weather.wu.com.weather;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.orhanobut.logger.Logger;
import com.zaaach.citypicker.CityPickerActivity;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.adapter.CityListAdapter;
import weather.wu.com.adapter.HourDataListAdapter;
import weather.wu.com.bean.FutureWeatherBean;
import weather.wu.com.bean.HourDataBean;
import weather.wu.com.bean.WeatherBean;
import weather.wu.com.db.WeatherDB;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SharedPreferencesUtils;
import weather.wu.com.utils.SpUtils;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;

/**
 *
 */
public class MainActivity extends SlidingActivity {

    /**主控件初始化**/
    //标题栏按钮
    @BindView(R.id.nav_button)
    Button mNavButton;
    @BindView(R.id.title_city)
    TextView mTextViewTileCity;
    //下拉刷新控件
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    //侧滑控件
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    //当前天气的RelativeLayout
    @BindView(R.id.main_now_weather)
    public RelativeLayout mNowWeatherRelativeLayout;
    //整体布局的ScrollView
    @BindView(R.id.weather_scrollview_layout)
    public ScrollView mScrollView;
    //半小时更新的RecyclerView
    @BindView(R.id.hourdata_recyclerview)
    public RecyclerView mRecyclerView;
    //未来天气的Linearlayout
    @BindView(R.id.forecast_layout)
    public LinearLayout mForecastLayout;
    //背景的图片
    @BindView(R.id.back_pic_img)
    public ImageView mImageViewBack;
    @BindView(R.id.main_left_menu)
    public LinearLayout mLinearLayoutLeftMenu;
    /*@BindView(R.id.main_right_menu)
    public LinearLayout mLinearLayoutRightMenu;*/

    /**NowWeather 控件初始化**/
    //最后一次更新时间
    @BindView(R.id.last_upate_text)
    TextView mLastUpateText;
    //空气质量
    @BindView(R.id.now_weather_air_quality)
    TextView mNowWeatherAirQuality;
    //空气指数
    @BindView(R.id.now_weather_air_index)
    TextView mNowWeatherAirIndex;
    //当天最高气温
    @BindView(R.id.now_weather_hight_tempture)
    TextView mNowWeatherHightTempture;
    //当天最低气温
    @BindView(R.id.now_weather_low_tempture)
    TextView mNowWeatherLowTempture;
    //当前气温
    @BindView(R.id.now_weather_tempeture)
    TextView mNowWeatherTempeture;
    //当天天气状态
    @BindView(R.id.now_weather_condition_tv)
    TextView mNowWeatherCondition;
    //当前天气图标
    @BindView(R.id.now_weather_img)
    ImageView mNowWeather;

    /**右侧菜单控件初始化**/

    /**左侧菜单控件初始化**/
    @BindView(R.id.left_edit_city)
    LinearLayout mLinearLayoutEditCity;
    @BindView(R.id.left_list_city_select)
    ListView mListViewCity;
    /**空气质量控件初始化**/
    @BindView(R.id.air_weather_condition)
    TextView mAirWeatherCondition;


    private int mNowWeatherHeight = -1;
    private int DisplayHeight;
    private int DisplayWideth;
    private Context mContext = MainActivity.this;
    private HourDataListAdapter mHourDataListAdapter;
    private List<String> datas;
    public static CityListAdapter mCityListAdapter;
    private static final int REQUEST_CODE_PICK_CITY = 0;
    public SQLiteDatabase db;
    public DBThread mDBThread;
    private List<FutureWeatherBean> fff = new ArrayList<FutureWeatherBean>();
    private List<HourDataBean> hhh = new ArrayList<HourDataBean>();
    public static String mCurrentCity;

    //启动
    //  String json;
    String a = "http://route.showapi.com/9-2?showapi_appid=28198&area=广州&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
    private List<String> listData = new ArrayList<>();
    private static List<String> mListCity = new ArrayList<>();
    SharedPreferencesUtils sharedPreferencesUtils;
    private DBThread mThread;
    private static  Handler mHandler =new Handler(){
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            int type = msg.what;
            switch (type){
                case 1:

                  /*  List<WeatherDB> weatherDBs= (List<WeatherDB>) msg.obj;
                    for(WeatherDB weatherDB:weatherDBs){
                        mListCity.add(weatherDB.getmCityName());
                    }*/
            }
            //只要执行到这里就关闭对话框
            //pd.dismiss();
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
         db = Connector.getDatabase();

      //  initData();

    }

    private void initData() {
        mListCity.add("深圳");
    }

    private void initView() {


        if(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)){
            startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                    REQUEST_CODE_PICK_CITY);
            //sharedPreferencesUtils.put("first_start",true);
        }else{
            mDBThread = new DBThread();
            mDBThread.start();
            mCityListAdapter = new  CityListAdapter(MainActivity.this,mListCity);
            mListViewCity.setAdapter(mCityListAdapter);
            mCityListAdapter.notifyDataSetChanged();

           // requestWeather(mCurrentCity);
            //mCurrentCity = mListCity.get(mListCity.size()).toString();

        }
        //  = SystemUtils.getDisplayHeight(getActivity());
        // Logger.d("hello");
        //NowWeather主RelativeLayout
        //   mNowWeatherRelativeLayout = (RelativeLayout)findViewById(R.id.main_now_weather);
        // mScrollView = (ScrollView)findViewById(R.id.weather_scrollview_layout);

        SlidingMenu mRightMenu = getSlidingMenu();
        setBehindContentView(R.layout.main_right_menu);
        mRightMenu.setMode(SlidingMenu.RIGHT);
        mRightMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mRightMenu.setFadeDegree(0.35f);
        mScrollView.smoothScrollTo(0, 0);
        //   mRecyclerView = (RecyclerView)findViewById(R.id.hourdata_recyclerview);
        //  mForecastLayout = (LinearLayout)findViewById(R.id.forecast_layout);
        mSwipeRefresh.setColorSchemeResources(R.color.color_main);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestWeather(mCurrentCity);
            }
        });
        //NowWeather主RelativeLayout中的RecycleView
        // mRecyclerView = (RecyclerView)findViewById(R.id.now_weather_recyclerview);
        //获取屏幕高度
       /* int displayHeight = SystemUtils.getDisplayHeight(MainActivity.this);
        Log.e("Log displayHeight",displayHeight+"");

        TypedValue typedValue = new TypedValue();
         MainActivity.this.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
         int[] attribute = new int[] { android.R.attr.textSize };
       TypedArray array =   MainActivity.this.obtainStyledAttributes(typedValue.resourceId, attribute);
        Log.e("Log array",array+"");
        int textSize = array.getDimensionPixelSize(0 *//* index *//*, -1 *//* default size *//*);
        array.recycle();
        Log.e("Log typedValue",textSize+"");
        int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, MainActivity.this.getResources().getDisplayMetrics());
        Log.e("Log actionBarHeight",actionBarHeight+"");
       Log.e("Log System.getActionBarHeight",);*/
        // mNowWeatherHeight高度=屏幕高度-标题栏高度-状态栏高度
        mNowWeatherHeight = SystemUtils.getDisplayHeight(mContext) - SystemUtils.getActionBarSize(mContext) - SystemUtils.getStatusBarHeight(mContext);
        DisplayHeight = SystemUtils.getDisplayHeight(mContext);
        DisplayWideth = SystemUtils.getDisplayWidth(mContext);
        Logger.d(DisplayHeight+"  "+DisplayWideth);
        //设置当前天气信息RelativeLayout的高度
        mNowWeatherRelativeLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, mNowWeatherHeight));
        mImageViewBack.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,mNowWeatherHeight/2));

      //  mLinearLayoutLeftMenu.setLayoutParams(new DrawerLayout.LayoutParams(DisplayWideth/2, DrawerLayout.LayoutParams.MATCH_PARENT));

        mListViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mCurrentCity =mListCity.get(position);
                requestWeather(mCurrentCity);
            }
        });
    }
    /**
     * 根据城市名请求城市天气信息。
     */
    public void requestWeather(final String cityName) {
        String weatherUrl = "http://route.showapi.com/9-2?showapi_appid=28198&area=" + cityName + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9"
                + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
        mScrollView.smoothScrollTo(0, 0);
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final WeatherBean weather = Utility.handleWeatherResponse(responseText);
                 WeatherDB weatherDB  =new WeatherDB();
                WeatherDB weatherData= DataSupport.where("mCityName = ?", cityName).findFirst(WeatherDB.class);
           // if(Utility.isNetworkConnected(MainActivity.this)){
                if(weatherData!=null&&"0".equals(weather.mShowapi_Res_Code)){
                    weatherDB.setmJsonData(responseText);
                    weatherDB.update(weatherData.id);
                }else if(weatherData==null&&"0".equals(weather.mShowapi_Res_Code)){
                    weatherDB.mCityName = cityName;
                    weatherDB.mJsonData = responseText;
                    weatherDB.save();
                }
          //  }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!mSwipeRefresh.isRefreshing()){
                            mSwipeRefresh.setRefreshing(true);
                        }
                        if (weather != null && "0".equals(weather.mShowapi_Res_Code)) {
                        /*   *//* SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();*//**/

                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
                WeatherDB weatherDB  =new WeatherDB();
                WeatherDB weatherData= DataSupport.where("mCityName = ?", cityName).findFirst(WeatherDB.class);
                final WeatherBean weather = Utility.handleWeatherResponse(weatherData.getmJsonData());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showWeatherInfo(weather);
                        Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        //  loadBingPic();
    }

    private void showWeatherInfo(WeatherBean weather) {
        mTextViewTileCity.setText(weather.getmCityName());
        mLastUpateText.setText(weather.getmNowWeatherBean().getmTemperature_Time());
        mNowWeatherAirQuality.setText("空气" + weather.getmAqiDetailBean().getmQuality());
        mNowWeatherAirIndex.setText("指数" + weather.getmAqiDetailBean().getmAqi());
        mNowWeatherHightTempture.setText(weather.getmTodayWeatherBean().getmDay_Air_Temperature()+"°");
        mNowWeatherLowTempture.setText(weather.getmTodayWeatherBean().getmNight_Air_Temperature()+"°");
        mNowWeatherTempeture.setText(weather.getmNowWeatherBean().getmTemperature());
        mNowWeatherCondition.setText(weather.getmNowWeatherBean().getmWeather());
        Glide.with(this).load(weather.getmNowWeatherBean().getmWeather_Pic()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mNowWeather);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mRecyclerView.setAdapter(new HourDataListAdapter(mContext, weather.getmHourDataList()));
        mRecyclerView.scrollToPosition(weather.getmHourDataList().size() - 1);
        mForecastLayout.removeAllViews();
        for (FutureWeatherBean futureWeatherBean : weather.getmFutureWeatherBeen()) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, mForecastLayout, false);
            view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayHeight / 12));
            TextView dataText = (TextView)view.findViewById(R.id.forecast_week_tv);
            ImageView weatherImg = (ImageView) view.findViewById(R.id.forecast_icon);
            TextView lowTempText = (TextView)view.findViewById(R.id.forecast_low_temp_tv);
            TextView hightTempText = (TextView)view.findViewById(R.id.forecast_high_temp_tv);
            dataText.setText(Utility.weakDayInfliter(futureWeatherBean.getmWeekDay()));
            Glide.with(this).load(futureWeatherBean.getmDay_Weather_Pic()).into(weatherImg);
            lowTempText.setText(futureWeatherBean.getmDay_Air_Temperature()+"°");
            hightTempText.setText(futureWeatherBean.getmNight_Air_Temperature()+"°");
            mForecastLayout.addView(view);
        }
        mForecastLayout.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayHeight / 2)));
    }

    @OnClick(R.id.nav_button)
    public void onOpenDrawerLayout() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
    @OnClick(R.id.title_city)
    public void onSelectCity() {
        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }

    /**
     * 开启线程对数据库进行操作
     */
    private  class DBThread extends Thread {
        @Override
        public void run() {
            List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
            if(weatherDB!=null) {
                for (WeatherDB wb : weatherDB) {
                    mListCity.add(wb.getmCityName());
                }
                mCurrentCity = mListCity.get(0).toString();
                requestWeather(mCurrentCity);
               // mCityListAdapter.notifyDataSetChanged();
             /* Message message = Message.obtain();
                message.obj = weatherDB;
                message.what = 1;
                mHandler.sendMessage(message);
            }else {
                Message message = Message.obtain();
                message.what = 0;
                mHandler.sendMessage(message);
            }*/
            }
        }
    }
    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                if(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)){
                    SpUtils.putBoolean(getApplicationContext(),SpUtils.FIRST_START,false);
                }
                Logger.d(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)+"");
                mCityListAdapter = new  CityListAdapter(MainActivity.this,mListCity);
                mListViewCity.setAdapter(mCityListAdapter);
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                // resultTV.setText("当前选择：" + city);
                if(!mListCity.contains(city)){
                    mListCity.add(city);
                    mCityListAdapter.notifyDataSetChanged();
                    mCurrentCity  = city;
                    requestWeather(mCurrentCity);
                }
                Logger.d(city);

               // WeatherBean weatherBean = DataSupport.find(WeatherBean.class,);
            }
        }
    }
}
