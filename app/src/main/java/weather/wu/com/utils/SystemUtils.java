package weather.wu.com.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2016/12/24.
 */
public class SystemUtils {
    /**
     * 获取手机屏幕高度
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
    /**
     * 获取手机屏幕宽度
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 反射方法获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 20;
        try {
            Class<?> _class = Class.forName("com.android.internal.R$dimen");
            Object object = _class.newInstance();
            Field field = _class.getField("status_bar_height");
            int restult = Integer.parseInt(field.get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(
                    restult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Toast.makeText(getActivity(), "StatusBarHeight = " + statusBarHeight,
        // Toast.LENGTH_SHORT).show();
        return statusBarHeight;
    }

    /**
     * 获取?attr/actionBarSize高度
     * @param context
     * @return
     */
    public static int getActionBarSize(Context context){
        TypedValue typedValue = new TypedValue();
       context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        return actionBarHeight;
    }
}
