package weather.wu.com.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.List;

import weather.wu.com.bean.WeatherBean;
import weather.wu.com.db.WeatherDB;
import weather.wu.com.service.WidgetService;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/1/17.
 */
public class WeatherAppWidget extends AppWidgetProvider {
    private RemoteViews mRemoteViews;
    private AppWidgetTarget mAppWidgetTarget;
    Intent intent;
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
         intent = new Intent(context,WidgetService.class);
        intent.putExtra("id",appWidgetIds[0]+"");
        Logger.e(appWidgetIds[0]+"");
        context.startService(intent);
//        Logger.d(appWidgetIds.toString());
      /*  mRemoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget_type);
        List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
        if (weatherDB != null) {
            WeatherBean weatherBean = Utility.handleWeatherResponse(weatherDB.get(0).getmJsonData());
            Logger.d(weatherBean.toString());
            //  Logger.d(weatherBean.getmCityName());
            mRemoteViews.setTextViewText(R.id.appwidget_city,weatherBean.mCityName);
            mRemoteViews.setTextViewText(R.id.appwidget_temp,weatherBean.getmNowWeatherBean().getmTemperature());
            mAppWidgetTarget =new AppWidgetTarget(context,mRemoteViews,R.id.appwidget_img,appWidgetIds);
            Glide.with(context).load(weatherBean.getmNowWeatherBean().getmWeather_Pic()).asBitmap().into(mAppWidgetTarget);
            ComponentName componentName =new ComponentName(context, WeatherAppWidget.class);
            AppWidgetManager.getInstance(context).updateAppWidget(componentName,mRemoteViews);
        }*/
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public WeatherAppWidget() {
        super();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
       // context.stopService(intent);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
