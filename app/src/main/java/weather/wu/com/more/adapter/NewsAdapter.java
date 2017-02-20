package weather.wu.com.more.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import weather.wu.com.more.bean.News;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/17.
 */
public class NewsAdapter extends BaseAdapter {
    private  List<News.ResultBean.DataBean> datas =new ArrayList<>();
    private  Context mContext;

    public NewsAdapter(Context mContext, List<News.ResultBean.DataBean> datas) {
        this.mContext = mContext;
        this.datas =datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewsViewHolder mNewsViewHolder;
        if(view==null){
            mNewsViewHolder = new NewsViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.more_news_item,null,false);
            mNewsViewHolder.mNewsImage = (ImageView)view.findViewById(R.id.more_news_img);
            mNewsViewHolder.mNewsTitle = (TextView)view.findViewById(R.id.more_news_title);
            mNewsViewHolder.mNewsTime = (TextView)view.findViewById(R.id.more_news_lunar);
            view.setTag(mNewsViewHolder);
        }else{
            mNewsViewHolder = (NewsViewHolder) view.getTag();
        }
        mNewsViewHolder.mNewsTitle.setText(datas.get(i).getTitle());
        mNewsViewHolder.mNewsTime.setText(datas.get(i).getDate());
        Glide.with(mContext).load(datas.get(i).getThumbnail_pic_s()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mNewsViewHolder.mNewsImage);
        return view;
    }

    private static class NewsViewHolder{
        public ImageView mNewsImage;
        public TextView mNewsTitle;
        public TextView mNewsTime;

    }
}
