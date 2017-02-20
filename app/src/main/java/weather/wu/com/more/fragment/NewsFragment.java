package weather.wu.com.more.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.activity.WebViewActivity;
import weather.wu.com.more.adapter.NewsAdapter;
import weather.wu.com.more.bean.News;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SpUtils;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/19.
 */
public class NewsFragment extends Fragment {
    private ListView mListView;
    private News mNews;
    public NewsFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View newsFragment = inflater.inflate(R.layout.more_news_fragment,container,false);
      //  mRecyclerView = (RecyclerView)todayInHistoryFragment.findViewById(R.id.toh_rl);
        mListView = (ListView)newsFragment.findViewById(R.id.more_news_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("url",mNews.getResult().getData().get(i).getUrl());
                startActivity(intent);
            }
        });
        return newsFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNewsJsonData();
    }

    private void getNewsJsonData() {

        String url ="http://v.juhe.cn/toutiao/index?type=top&key=a237730bf654bd58c536ba35868150c4";
        if(!Utility.isNetworkConnected(getActivity())){
            String newsjson = SpUtils.getString(getActivity(),"news","");
            if(!"".equals(newsjson)){
                Gson gson = new Gson();
                mNews = gson.fromJson(newsjson, News.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // editor.putString("weather", responseText);
                        // editor.apply();*//**//**//*
                        // showWeatherInfo(weather);
                        // Logger.d( mTodayInHistory.getResult().size()+"");
                        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), mNews.getResult().getData());
                        mListView.setAdapter(newsAdapter);
                        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                        // mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }else {
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Logger.e(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    try {
                        JSONObject resultJson = new JSONObject(json);
                        String errorcode = resultJson.getInt("error_code") + "";
                        if ("0".equals(errorcode)) {
                            SpUtils.putString(getActivity(), "news", resultJson.toString());
                            Gson gson = new Gson();
                            mNews = gson.fromJson(json, News.class);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // editor.putString("weather", responseText);
                                    // editor.apply();*//**//**//*
                                    // showWeatherInfo(weather);
                                    // Logger.d( mTodayInHistory.getResult().size()+"");
                                    NewsAdapter newsAdapter = new NewsAdapter(getActivity(), mNews.getResult().getData());
                                    mListView.setAdapter(newsAdapter);
                                    //  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                                    // mSwipeRefresh.setRefreshing(false);
                                }
                            });
                            Logger.d(mNews.getResult().getData().size() + "");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Logger.d(json);
                }
            });
        }
    }
}
