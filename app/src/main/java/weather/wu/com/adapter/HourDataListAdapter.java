package weather.wu.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2016/12/29.
 */
public class HourDataListAdapter extends RecyclerView.Adapter<HourDataListAdapter.HourDataListViewHolder> {

    private  Context context;
    private  List<String> datas =new ArrayList<>();

    public HourDataListAdapter(Context mContext, List<String> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public HourDataListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View hourDataListItemView = View.inflate(context, R.layout.hourdatalist_item,null);
        return new HourDataListViewHolder(hourDataListItemView);
    }

    @Override
    public void onBindViewHolder(HourDataListViewHolder holder, int position) {
            String data = datas.get(position);
          holder.mHourDataListTemptureTime.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HourDataListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.hourdata_tempture_time)
        TextView mHourDataListTemptureTime;
        @BindView(R.id.hourdata_weather_img)
        ImageView mHourDataWeatherImg;
        @BindView(R.id.hourdata_tempture)
        TextView mHourDataTempture;
        @BindView(R.id.hourdata_wind)
        TextView mHourDataWind;
        public HourDataListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
          //  mHourDataListTemptureTime = (TextView)itemView.findViewById(R.id.hourdata_tempture_time);
        }
    }
}
