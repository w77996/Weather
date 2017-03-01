package weather.wu.com.more.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    private SwipeRefreshLayout mSwipeRefresh;
    private Handler mHandler = new Handler() {
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.what;
            switch (type) {
                case 2:
                    //requestWeather(mCurrentCity);

                    getJokeJsonData();
                    //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    mSwipeRefresh.setRefreshing(false);
                    break;
            }
            //只要执行到这里就关闭对话框
            //pd.dismiss();
        }
    };
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
        View jokeFragment = inflater.inflate(R.layout.more_joke_fragment,container,false);
        //  mRecyclerView = (RecyclerView)todayInHistoryFragment.findViewById(R.id.toh_rl);
        mListView = (ListView)jokeFragment.findViewById(R.id.more_joke_listview);

        mSwipeRefresh = (SwipeRefreshLayout)jokeFragment.findViewById(R.id.joke_swpsh);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(mListView != null && mListView.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = mListView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = mListView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeRefresh.setEnabled(enable);
            }});


        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //控件拉动是放大放小，起始位置，结束位置
        //   mSwipeRefresh.setProgressViewOffset(false, 150, 300);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(Utility.isNetworkConnected(getActivity())){
                    Message message = Message.obtain();
                    message.what =2;
                    // requestWeather(mCurrentCity);
                    mHandler.sendMessageDelayed(message,2000);
                }else{
                    Toast.makeText(getActivity(),"请求失败,请检查网络状况",Toast.LENGTH_SHORT).show();
                    if(mSwipeRefresh.isRefreshing()){
                        mSwipeRefresh.setRefreshing(false);
                    }
                }


            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("url",mJoke.getShowapi_res_body().getList().get(i).getUrl());
                startActivity(intent);
            }
        });
        return jokeFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getJokeJsonData();
    }

    private void getJokeJsonData() {
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
