package weather.wu.com.more.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
        mRecyclerView = (RecyclerView) pictureFragment.findViewById(R.id.more_picture_recyclerview);
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
        getNewsJsonData();
    }

    private void getNewsJsonData() {

        String url ="http://gank.io/api/data/福利/20/1 ";
        if(!Utility.isNetworkConnected(getActivity())){
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
                        PictureAdapter pictureAdapter = new PictureAdapter(getActivity(),mPicture.getResults());
                        mRecyclerView.setAdapter(pictureAdapter);
                        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
                        mRecyclerView.addItemDecoration(spacesItemDecoration);
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
                            mPicture = gson.fromJson(json, Picture.class);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // editor.putString("weather", responseText);
                                    // editor.apply();*//**//**//*
                                    // showWeatherInfo(weather);
                                    // Logger.d( mTodayInHistory.getResult().size()+"");
                                    PictureAdapter pictureAdapter = new PictureAdapter(getActivity(),mPicture.getResults());
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
