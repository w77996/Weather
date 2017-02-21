package weather.wu.com.more.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import weather.wu.com.more.bean.Picture;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/20.
 */
public class PictureAdapter  extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder>{
    private final Context cotext;
    private List<Picture.ResultsBean> datas = new ArrayList<>();

    public PictureAdapter(Context context , List<Picture.ResultsBean> datas){
        this.cotext =context;
        this.datas = datas;
    }


    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cotext).inflate(R.layout.more_picture_item,parent,false);

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Glide.with(cotext).load(datas.get(position).getUrl()).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mImageView);
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public PictureViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.more_picture_img);
        }
    }
}
