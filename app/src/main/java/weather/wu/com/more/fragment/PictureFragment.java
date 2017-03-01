package weather.wu.com.more.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.more.adapter.PictureAdapter;
import weather.wu.com.more.bean.Picture;
import weather.wu.com.more.bean.SpacesItemDecoration;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SpUtils;
import weather.wu.com.utils.Utility;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/21.
 */
public class PictureFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Picture mPicture;
   // private SwipeRefreshLayout mSwipeRefresh;
    PictureAdapter pictureAdapter;
    int i=2;
  /*  private Handler mHandler = new Handler() {
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.what;
            switch (type) {
                case 1:
                  *//*  List<WeatherDB> weatherDBs= (List<WeatherDB>) msg.obj;
                    for(WeatherDB weatherDB:weatherDBs){
                        mListCity.add(weatherDB.getmCityName());
                    }*//*
                    break;
                case 2:
                    //requestWeather(mCurrentCity);
                    String url = "http://gank.io/api/data/福利/20/"+i;
                    Logger.d(url);
                    i++;
                    getPictureJsonData(url);

                    mSwipeRefresh.setRefreshing(false);
                    break;
            }
            //只要执行到这里就关闭对话框
            //pd.dismiss();
        }
    };*/
    String url ="http://gank.io/api/data/福利/30/1 ";
    public PictureFragment() {
        super();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pictureFragment = inflater.inflate(R.layout.more_picture_fragment,container,false);
        //  mRecyclerView = (RecyclerView)todayInHistoryFragment.findViewById(R.id.toh_rl);
       // pictureFragment.setLayoutParams(new LinearLayout.LayoutParams(mDisplayWideth/2, LinearLayout.LayoutParams.WRAP_CONTENT));
        mRecyclerView = (RecyclerView) pictureFragment.findViewById(R.id.more_picture_recyclerview);
      /*  mSwipeRefresh = (SwipeRefreshLayout)pictureFragment.findViewById(R.id.picture_swipe);
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //控件拉动是放大放小，起始位置，结束位置
        mSwipeRefresh.setProgressViewOffset(false, 150, 300);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(Utility.isNetworkConnected(getActivity())){
                    Message message = Message.obtain();
                    message.what =2;
                    // requestWeather(mCurrentCity);
                    mRecyclerView.removeAllViews();
                    mHandler.sendMessageDelayed(message,2000);
                }else{
                    Toast.makeText(getActivity(),"请求失败,请检查网络状况",Toast.LENGTH_SHORT).show();
                    if(mSwipeRefresh.isRefreshing()){
                        mSwipeRefresh.setRefreshing(false);
                    }
                }


            }
        });*/
      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("url",mPicture.getResults().get(i).getUrl());
                startActivity(intent);
            }
        });*/

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return pictureFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPictureJsonData(url);
    }

    private void getPictureJsonData(String url) {

        if(!Utility.isNetworkConnected(getActivity())){
            //if(mSwipeRefresh.isRefreshing())
            String newsjson = SpUtils.getString(getActivity(),"picture","");
            if(!"".equals(newsjson)){
                Gson gson = new Gson();

                mPicture = gson.fromJson(newsjson, Picture.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // editor.putString("weather", responseText);
                        // editor.apply();*//**//**//*
                        // showWeatherInfo(weather);
                        // Logger.d( mTodayInHistory.getResult().size()+"");
                        //mRecyclerView.removeAllViews();
                        PictureAdapter pictureAdapter = new PictureAdapter(getActivity(),mPicture.getResults());
                        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                        mRecyclerView.setAdapter(pictureAdapter);
                        pictureAdapter.notifyDataSetChanged();
                     /*   SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(8);
                        mRecyclerView.addItemDecoration(spacesItemDecoration);*/
                      /*  NewsAdapter newsAdapter = new NewsAdapter(getActivity(), mNews.getResult().getData());
                        mListView.setAdapter(newsAdapter);*/
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
                        String errorcode = resultJson.getString("error");
                        if ("false".equals(errorcode)) {
                            SpUtils.putString(getActivity(), "picture", resultJson.toString());
                            Gson gson = new Gson();
                           // mPicture =null;
                            mPicture = gson.fromJson(json, Picture.class);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // editor.putString("weather", responseText);
                                    // editor.apply();*//**//**//*
                                    // showWeatherInfo(weather);
                                    // Logger.d( mTodayInHistory.getResult().size()+"");
                                   // pictureAdapter = null;
                                  //s  Logger.d("7777777777");
                                     pictureAdapter= new PictureAdapter(getActivity(),mPicture.getResults());
                                   /* NewsAdapter newsAdapter = new NewsAdapter(getActivity(), mNews.getResult().getData());
                                    mListView.setAdapter(newsAdapter);*/

                                    mRecyclerView.setAdapter(pictureAdapter);
                                    SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(8);
                                    mRecyclerView.addItemDecoration(spacesItemDecoration);
                                    //  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                                    // mSwipeRefresh.setRefreshing(false);
                                }
                            });
                            Logger.d(mPicture.getResults().size() + "");
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
