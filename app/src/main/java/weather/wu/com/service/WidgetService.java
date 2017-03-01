package weather.wu.com.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weather.wu.com.activity.SplashActivity;
import weather.wu.com.bean.WeatherBean;
import weather.wu.com.db.WeatherDB;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;
import weather.wu.com.widget.WeatherAppWidget;

/**
 * Created by Administrator on 2017/2/17.
 */
public class WidgetService extends Service {
    private RemoteViews mRemoteViews;
    private AppWidgetTarget mAppWidgetTarget;
    private updateHandler mUpdateHandler;
    int mAppwidgetId;

    private static final int ALAM_DURATION =300* 60*1000;
    private static final int UPDATE_DURATION = 60*1000;
    private static final int ALAM_MESSAGE = 1000;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // AlarmManager alarmManager = (AlarmManager)
        //获取AppWidget的id，service再次运行的时候调用starCommand,为空出现ANR
        if(null!=intent.getStringExtra("id")){
            mAppwidgetId =Integer.parseInt(intent.getStringExtra("id"));
            Logger.i(mAppwidgetId+"");
        }
        Logger.d("AppWidget onStartCommand");
        mRemoteViews = new RemoteViews(getApplicationContext().getPackageName(),R.layout.appwidget_typeone);

       updateAppWidget();
        Intent intent1= new Intent(getApplicationContext(), SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent1,0);
        mRemoteViews.setOnClickPendingIntent(R.id.appwidget_layout,pendingIntent);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(),WidgetService.class);
        PendingIntent alarmPendingIntent = PendingIntent.getService(getApplicationContext(),0,alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ALAM_DURATION,alarmPendingIntent);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Message message =Message.obtain();
        message.what = ALAM_MESSAGE;
        mUpdateHandler = new updateHandler();
        mUpdateHandler.sendMessageDelayed(message,UPDATE_DURATION);
    }
        private void updateAppWidget(){
            List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
            if (weatherDB != null) {
                WeatherBean weatherBean = Utility.handleWeatherResponse(weatherDB.get(0).getmJsonData());
                Logger.d(weatherBean.toString());
                //  Logger.d(weatherBean.getmCityName());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
                Date date = new Date();
                mRemoteViews.setTextViewText(R.id.appwiget_cityname,weatherBean.mCityName);
                mRemoteViews.setTextViewText(R.id.appwiget_time,simpleDateFormat.format(date));
                mRemoteViews.setTextViewText(R.id.appwiget_date,simpleDateFormat2.format(date));
                mRemoteViews.setTextViewText(R.id.appwiget_week,Utility.weakDayInfliter(weatherBean.getmTodayWeatherBean().getmWeekDay()));
                mRemoteViews.setTextViewText(R.id.appwiget_weather_condition,weatherBean.getmNowWeatherBean().getmWeather());
                mRemoteViews.setTextViewText(R.id.appwiget_h,"湿度 "+weatherBean.getmNowWeatherBean().getmSd());
                mRemoteViews.setTextViewText(R.id.appwidget_nowtemp,weatherBean.getmNowWeatherBean().getmTemperature()+"°");
                mRemoteViews.setTextViewText(R.id.appwiget_wind,weatherBean.getmNowWeatherBean().getmWind_Direction()+weatherBean.getmNowWeatherBean().getmWind_Power());

                mRemoteViews.setTextViewText(R.id.appwiget_weather_condition,weatherBean.getmNowWeatherBean().getmWeather());
                //mRemoteViews.setTextViewText(R.id.appwiget_today_temp,weatherBean.getmFutureWeatherBeen().get(0).getmNight_Air_Temperature()+"°/"+weatherBean.getmFutureWeatherBeen().get(0).getmDay_Air_Temperature()+"°");

                 mAppWidgetTarget =new AppWidgetTarget(getApplicationContext(),mRemoteViews,R.id.appwiget_picture,mAppwidgetId);
                Glide.with(getApplicationContext()).load(weatherBean.getmNowWeatherBean().getmWeather_Pic()).asBitmap().into(mAppWidgetTarget);
              //  mRemoteViews.setImageViewBitmap(R.id.appwidget_img,bitmap);
               // Glide.with(getApplicationContext()).load(weatherBean.getmNowWeatherBean().getmWeather_Pic()).asBitmap().into(mAppWidgetTarget);
                ComponentName componentName =new ComponentName(getApplicationContext(), WeatherAppWidget.class);
                AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(componentName,mRemoteViews);

                Message message = mUpdateHandler.obtainMessage();
                message.what=ALAM_MESSAGE;
                mUpdateHandler.sendMessageDelayed(message,UPDATE_DURATION);
        }
    }
    private void updateWeather(){



    }

    private class updateHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ALAM_MESSAGE:
                    updateAppWidget();
                    break;
                default:
                    break;
            }
        }
    }
}
