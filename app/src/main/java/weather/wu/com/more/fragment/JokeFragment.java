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
import weather.wu.com.more.adapter.JokeAdapter;
import weather.wu.com.more.bean.Joke;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SpUtils;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/20.
 */
public class JokeFragment extends Fragment {
    private ListView mListView;
    private Joke mJoke;
    public JokeFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View newsFragment = inflater.inflate(R.layout.more_joke_fragment,container,false);
        //  mRecyclerView = (RecyclerView)todayInHistoryFragment.findViewById(R.id.toh_rl);
        mListView = (ListView)newsFragment.findViewById(R.id.more_joke_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("url",mJoke.getShowapi_res_body().getList().get(i).getUrl());
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
    String date = SystemUtils.getCurrentTime();
        String url ="https://route.showapi.com/107-33?showapi_appid=28198&showapi_timestamp="+date+"&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9";
       if(Utility.isNetworkConnected(getActivity())){


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
                    String errorcode = resultJson.getInt("showapi_res_code") + "";
                    if ("0".equals(errorcode)) {
                        SpUtils.putString(getActivity(),"joke",resultJson.toString());
                        Gson gson = new Gson();
                        mJoke = gson.fromJson(json, Joke.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // editor.putString("weather", responseText);
                                // editor.apply();*//**//**//*
                                // showWeatherInfo(weather);
                                // Logger.d( mTodayInHistory.getResult().size()+"");
                               // NewsAdapter newsAdapter = new NewsAdapter(getActivity(), mNews.getResult().getData());
                                JokeAdapter jokeAdapter = new JokeAdapter(getActivity(),mJoke.getShowapi_res_body().getList());
                                mListView.setAdapter(jokeAdapter);
                               // mListView.setAdapter(newsAdapter);
                                //  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                                // mSwipeRefresh.setRefreshing(false);
                            }
                        });
                       // Logger.d(mNews.getResult().getData().size()+"");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Logger.d(json);
            }
        });
       }else {
           String jokejson = SpUtils.getString(getActivity(),"joke","");
           if(!"".equals(jokejson)) {
               Gson gson = new Gson();
               mJoke = gson.fromJson(jokejson, Joke.class);
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       // editor.putString("weather", responseText);
                       // editor.apply();*//**//**//*
                       // showWeatherInfo(weather);
                       // Logger.d( mTodayInHistory.getResult().size()+"");
                       JokeAdapter jokeAdapter = new JokeAdapter(getActivity(), mJoke.getShowapi_res_body().getList());
                       mListView.setAdapter(jokeAdapter);
                       //  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                       // mSwipeRefresh.setRefreshing(false);
                   }

               });
           }
       }
    }

}
