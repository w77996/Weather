package weather.wu.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import weather.wu.com.activity.CityEditActivity;
import weather.wu.com.bean.CityEditBean;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/1/13.
 */
public class CityEditListAdapter extends BaseAdapter {

    private  Context context;
    private  List<CityEditBean> list;

    public CityEditListAdapter(Context context , List<CityEditBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityEditListViewHolde cityEditListViewHolde ;
        if(convertView==null){
            cityEditListViewHolde = new CityEditListViewHolde();
            convertView = LayoutInflater.from(context).inflate(R.layout.cityedit_listview_item,null,false);
            cityEditListViewHolde.mCityName = (TextView)convertView.findViewById(R.id.cityedit_listview_item_cityname);
            cityEditListViewHolde.mImageView = (ImageView)convertView.findViewById(R.id.cityedit_listview_item_img);
            cityEditListViewHolde.mTempture = (TextView)convertView.findViewById(R.id.cityedit_listview_item_temp);
            convertView.setTag(cityEditListViewHolde);
        }else{
            cityEditListViewHolde = (CityEditListViewHolde)convertView.getTag();
        }
        cityEditListViewHolde.mCityName.setText(list.get(position).getmCityName());
        cityEditListViewHolde.mTempture.setText(list.get(position).getmTempture());
        Glide.with(context).load(list.get(position).getmWeather_Pic()).diskCacheStrategy(DiskCacheStrategy.ALL).into(cityEditListViewHolde.mImageView);
        return convertView;
    }
    public class CityEditListViewHolde {
        // @BindView(R.id.left_city_name)
        // public  ImageView mImageViewLocation;
        public  TextView mCityName;
        public ImageView mImageView;
        public TextView mTempture;
    }

}
