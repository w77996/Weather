package weather.wu.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/1/4.
 */
public class CityListAdapter extends BaseAdapter {

    private  Context context;
    private  List<String> listdata = new ArrayList<>();

    public CityListAdapter(Context context, List<String> listdata){
        this.context = context;
        this.listdata =listdata;
    }
    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityListViewHolde cityListViewHolde;
        if(convertView==null){
             cityListViewHolde = new CityListViewHolde();
            convertView = LayoutInflater.from(context).inflate(R.layout.left_listview_city_item,null,false);
            cityListViewHolde.mCityName = (TextView)convertView.findViewById(R.id.left_city_name);
            convertView.setTag(cityListViewHolde);
        }else{
            cityListViewHolde = (CityListViewHolde)convertView.getTag();
        }
        cityListViewHolde.mCityName.setText(listdata.get(position));
        return convertView;
    }
    public class CityListViewHolde {
       // @BindView(R.id.left_city_name)
           // public  ImageView mImageViewLocation;
        public  TextView mCityName;
    }
}
