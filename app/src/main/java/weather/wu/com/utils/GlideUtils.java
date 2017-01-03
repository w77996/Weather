package weather.wu.com.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/1/3.
 */
public class GlideUtils {
    public static  void loaderImager(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
