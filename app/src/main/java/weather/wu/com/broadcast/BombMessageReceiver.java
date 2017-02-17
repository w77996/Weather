package weather.wu.com.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import weather.wu.com.activity.SplashActivity;
import weather.wu.com.weather.R;
import weather.wu.com.weather.WeatherActivity;

/**
 * Created by Administrator on 2017/2/15.
 */
public class BombMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Logger.d(intent.getStringExtra("msg"));
            String content =null;
            String msg = intent.getStringExtra("msg");
            try {
                JSONObject msgJson = new JSONObject(msg);
                 content = msgJson.getString("alert");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            // 使用PendingIntent延时执行Intent
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, SplashActivity.class), 0);
            // 使用Builder构造通知对象
            Notification notify = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher) // 必须设置图片,否则无法正常推送
                    .setContentInfo("Message Content")
                    .setTicker("This is Ticker") // 直接在标题栏里显示的通知
                    .setContentTitle(content)
                    .setContentIntent(pendingIntent)  // 点击事件
                    .setOngoing(false) // 允许用户删除
//                .setNumber(1)
                    .build();
            // 不会自动消失
            // notify.flags |= Notification.FLAG_NO_CLEAR;
            // FLAG的一些熟悉
            //FLAG_AUTO_CANCEL   该通知能被状态栏的清除按钮给清除掉
            //FLAG_NO_CLEAR      该通知不能被状态栏的清除按钮给清除掉
            //FLAG_ONGOING_EVENT 通知放置在正在运行
            //FLAG_INSISTENT     是否一直进行，比如音乐一直播放，知道用户响应
            // 点击通知时,自动清除
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notify.defaults = Notification.DEFAULT_ALL;
            manager.notify(1, notify);
        }
    }
}


