package weather.wu.com.utils;

import com.orhanobut.logger.Logger;
import com.show.api.ShowApiRequest;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/12/24.
 */
public class HttpUtil {
    public final static String appid = "28198";//要替换成自己的
    public final static String secret = "bd9ad7a172ee4a5a8c57618a248c63e9";//要替换成自己的
   // public static String cityName ="广州";
    public  static String address = "http://route.showapi.com/9-2?showapi_appid="+appid+"&area="+"广州"+"&showapi_sign="+secret
                                     + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static void getWeatherJsonData(final String cityName) {
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/9-2", appid, secret)
                        .addTextPara("areaid", "")
                        .addTextPara("area", cityName)
                        .addTextPara("needMoreDay", "1")
                        .addTextPara("needIndex", "1")
                        .addTextPara("needHourData", "1")
                        .addTextPara("need3HourForcast", "1")
                        .addTextPara("needAlarm", "1")
                        .post();
                Logger.json(res);

               // parseJsonData(res);
                //把返回内容通过handler对象更新到界面
            }
        }.start();

    }

}
