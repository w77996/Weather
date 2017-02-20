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

import weather.wu.com.more.bean.Joke;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/20.
 */
public class JokeAdapter  extends BaseAdapter {
    private List<Joke.ShowapiResBodyBean.ListBean> datas = new ArrayList<>();
    private Context mContext;

    public JokeAdapter(Context mContext, List<Joke.ShowapiResBodyBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
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
        JokeViewHolder mJokeViewHolder;
        if (view == null) {
            mJokeViewHolder = new JokeViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.more_joke_item, null, false);
            mJokeViewHolder.mJokeImage = (ImageView)view.findViewById(R.id.more_joke_img);
            mJokeViewHolder.mJokeTitle = (TextView)view.findViewById(R.id.more_joke_title);
            view.setTag(mJokeViewHolder);
        } else {
            mJokeViewHolder = (JokeViewHolder) view.getTag();
        }
       mJokeViewHolder.mJokeTitle.setText(datas.get(i).getTitle());
       // mNewsViewHolder.mNewsTime.setText(datas.get(i).get());
        Glide.with(mContext).load(datas.get(i).getSourceurl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mJokeViewHolder.mJokeImage);
        return view;
    }

    private static class JokeViewHolder {
       public ImageView mJokeImage;
        public TextView mJokeTitle;
        public TextView mNewsTime;

    }
}
