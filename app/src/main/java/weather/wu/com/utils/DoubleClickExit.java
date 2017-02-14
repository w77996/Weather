package weather.wu.com.utils;

/**
 * Created by Administrator on 2017/2/14.
 */
public class DoubleClickExit {
    public static long mLastClick = 0L;
    private static final int THRESHOLD = 2000;
    public static boolean check(){
            long now = System.currentTimeMillis();
            boolean c = now - mLastClick <THRESHOLD;
        mLastClick = now;
        return c;
    }
}
