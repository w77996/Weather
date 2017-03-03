package weather.wu.com;

import android.app.Application;

import net.youmi.android.AdManager;

import org.litepal.LitePalApplication;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/1/5.
 */
public class WeatherApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        /*Bmob.initialize(this, "d9cf386c0792498c6ff73d3f1e816fe4");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务

        BmobPush.startWork(this);*/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        AdManager.getInstance(getApplicationContext()).init("9392bd24660e693f","b55642a79da8ae7b", true, true);
    }
}
