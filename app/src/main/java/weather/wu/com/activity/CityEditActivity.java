package weather.wu.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.wu.com.adapter.CityEditListAdapter;
import weather.wu.com.bean.CityEditBean;
import weather.wu.com.bean.WeatherBean;
import weather.wu.com.db.WeatherDB;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2017/1/12.
 */
public class CityEditActivity extends Activity {
    List<CityEditBean> mCityList =new ArrayList<>();
    // @BindView(R.id.cityedit_listview)
    SwipeMenuListView mCityEditListview;
    CityEditListAdapter mCityEditListAdapter;
    private static final int REQUEST_CODE_EDIT_CITY =1;
    private android.os.Handler mHandler =new android.os.Handler(){
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.what;
            switch (type){
                case 1:
                    mCityList = (List<CityEditBean>) msg.obj;
                    Logger.d(mCityList.size()+"");
                    mCityEditListAdapter = new CityEditListAdapter(CityEditActivity.this,mCityList);
                    mCityEditListview.setAdapter(mCityEditListAdapter);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityedit);
        ButterKnife.bind(this);
        initData();
        //  mCityEditListview.setMenuCreator(creator);
    }

    private void initData() {
      //  Intent intent = getIntent();
        //  mCityList = intent.getStringArrayListExtra("city");
        //  Logger.d(mCityList.size() + "");
      /*  List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
        Logger.d(mCityList.size() + "");*/
        CityDisPlayThread cityDisPlayThread = new CityDisPlayThread();
        cityDisPlayThread.start();
        mCityEditListview = (SwipeMenuListView) findViewById(R.id.cityedit_listview);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem( getApplicationContext());
                // set item background
                //openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
                // set item width
              //  openItem.setWidth(90);
                // set item title
                // openItem.setTitle("Open");
                // set item title fontsize
                //  openItem.setTitleSize(18);
                // set item title font color
                //  openItem.setTitleColor(Color.WHITE);
                // add to menu
                // menu.addMenuItem(openItem);
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);

                // set a icon
                deleteItem.setIcon(R.drawable.ic_default_big);
                // add to menu
                menu.addMenuItem(deleteItem);
            }

        };
        mCityEditListview.setMenuCreator(creator);
        mCityEditListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getApplicationContext(), position + " menu click", Toast.LENGTH_SHORT).show();
                        if(mCityList.size()>1){
                            CityRmoveThread cityRmoveThread = new CityRmoveThread(mCityList.get(position).getmCityName());
                            cityRmoveThread.start();
                            mCityList.remove(position);
                            mCityEditListAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), position + " 亲，删除了你看啥？", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }
                // false : 会关闭菜单; true ：不会关闭菜单
                return false;
            }
        });
// set SwipeListener
        mCityEditListview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }
            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });
        mCityEditListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
       /* mCityEditListAdapter = new CityEditListAdapter(CityEditActivity.this, mCityList);
        mCityEditListview.setAdapter(mCityEditListAdapter);*/
    }


    private class CityDisPlayThread extends Thread {
        @Override
        public void run() {
            super.run();
            List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
            List<CityEditBean> cityEditBeanList = new ArrayList<>();
            Message message  =Message.obtain();
            if (weatherDB != null) {
                for (WeatherDB wb : weatherDB) {

                    /* Bundle bundle=new Bundle();*/

                    WeatherBean weatherBean = Utility.handleWeatherResponse(wb.getmJsonData());
                   /* bundle.putString("cityname", weatherBean.getmCityName());
                    bundle.putString("imagepic",weatherBean.getmNowWeatherBean().getmWeather_Pic());
                    bundle.putString("temp",weatherBean.getmTodayWeatherBean().getmDay_Air_Temperature()+"°/"+weatherBean.getmTodayWeatherBean().getmNight_Air_Temperature()+"°")*/
                    ;
                    CityEditBean citySelectBean = new CityEditBean();
                    citySelectBean.mCityName = weatherBean.getmCityName();
                    citySelectBean.mWeather_Pic = weatherBean.getmNowWeatherBean().getmWeather_Pic();
                    citySelectBean.mTempture = weatherBean.getmTodayWeatherBean().getmDay_Air_Temperature() + "°/" + weatherBean.getmTodayWeatherBean().getmNight_Air_Temperature() + "°";
                 /*   bundle.putParcelable("citySelectBean",citySelectBean);
                    message.setData(bundle);//bundle传值，耗时，效率低*/


                    cityEditBeanList.add(citySelectBean);
                }
                message.what=1;
                message.obj =cityEditBeanList;
                mHandler.sendMessage(message);

            }

        }

    }
    private class CityRmoveThread extends Thread{
        private String cityName;
        public CityRmoveThread(String cityName) {
            this.cityName = cityName;
        }
        @Override
        public void run() {
            super.run();
            WeatherDB weatherDB = DataSupport.select("id")
                    .where("mCityName = ?", cityName).findFirst(WeatherDB.class);
            Logger.d(weatherDB.toString());
            if(weatherDB!=null){
                DataSupport.delete(WeatherDB.class, weatherDB.getId());
            }
        }
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_OK); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
    }*/
}
