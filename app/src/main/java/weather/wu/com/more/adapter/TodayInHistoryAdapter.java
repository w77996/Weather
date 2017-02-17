package weather.wu.com.more.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.wu.com.more.bean.TodayInHistory;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/16.
 */
public class TodayInHistoryAdapter extends RecyclerView.Adapter<TodayInHistoryAdapter.TodayInHistoryViewHolder> {

    private Context context;
    private List<TodayInHistory.ResultBean> datas = new ArrayList<TodayInHistory.ResultBean>();

    public TodayInHistoryAdapter(Context mContext, List<TodayInHistory.ResultBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }
    @Override
    public TodayInHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View todayInHistoryItemView = View.inflate(context, R.layout.more_todayinhistory_item,null);
        return new TodayInHistoryViewHolder(todayInHistoryItemView);
    }

    @Override
    public void onBindViewHolder(TodayInHistoryViewHolder holder, int position) {
        TodayInHistory.ResultBean  resultBean = datas.get(position);
        if(!"".equals(resultBean.getPic())&&null !=resultBean.getPic()){
        holder.mMoreTohTitle.setText(resultBean.getTitle());
        holder.mMoreTohDec.setText(resultBean.getDes());
        holder.mMoreTohLunar.setText(resultBean.getLunar());
        holder.mMoreTohDate.setText(resultBean.getYear()+"年"+resultBean.getMonth()+"月"+resultBean.getDay()+"日");
            Glide.with(context).load(resultBean.getPic()).placeholder(R.mipmap.ic_launcher).into(holder.mMoreTohImg);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class TodayInHistoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.more_toh_title)
        TextView mMoreTohTitle;
        @BindView(R.id.more_toh_dec)
        TextView mMoreTohDec;
        @BindView(R.id.more_toh_lunar)
        TextView mMoreTohLunar;
        @BindView(R.id.more_toh_date)
        TextView mMoreTohDate;
        @BindView(R.id.more_toh_img)
        ImageView mMoreTohImg;
        public TodayInHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
