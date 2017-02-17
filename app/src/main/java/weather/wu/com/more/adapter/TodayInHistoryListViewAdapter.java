package weather.wu.com.more.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import weather.wu.com.more.bean.TodayInHistory;

/**
 * Created by Administrator on 2017/2/17.
 */
public class TodayInHistoryListViewAdapter extends BaseAdapter {

    private final Context context;
    private List<TodayInHistory.ResultBean> datas =new ArrayList<>();

    public TodayInHistoryListViewAdapter(Context context, List<TodayInHistory.ResultBean> datas){
        this.context = context;
        this.datas =datas;
    }
    @Override
    public int getCount() {
        return 15;
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
        return null;
    }
    static class TodayInHisrotyListViewViewHolder {


    }
}
