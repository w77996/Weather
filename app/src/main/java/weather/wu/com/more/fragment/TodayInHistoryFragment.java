/*
package weather.wu.com.more.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.more.bean.TodayInHistory;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.weather.R;

*/
/**
 * Created by Administrator on 2017/2/16.
 *//*

public class TodayInHistoryFragment extends Fragment {

    RecyclerView mRecyclerView;
     TodayInHistory mTodayInHistory;
    public TodayInHistoryFragment() {
        super();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getHistoryJsonData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View todayInHistoryFragment = inflater.inflate(R.layout.more_todayinhistory_fragment,container,false);
        mRecyclerView = (RecyclerView)todayInHistoryFragment.findViewById(R.id.toh_rl);
        return todayInHistoryFragment;
    }
    private void getHistoryJsonData() {
        long now = System.currentTimeMillis();
        String currentTime = SystemUtils.getCurrentTime();
        String currentDate = SystemUtils.getCurrentDate();
        String currentMonth = SystemUtils.getCurrentMonth();
        //String url = "http://route.showapi.com/119-42?data=" + currentDate + "&showapi_appid=28198&showapi_timestam" + currentTime + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9";
        String url ="http://api.juheapi.com/japi/toh?v=&month="+currentMonth+"&day="+currentDate+"&key=02351f897b139cc86e39a225aaeaa42d";
        Logger.d(url);
        Logger.d(now + " " + currentTime + " " + currentDate);
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
                        Gson gson = new Gson();
                         mTodayInHistory = gson.fromJson(json, TodayInHistory.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                           // editor.putString("weather", responseText);
                           // editor.apply();*//*
*/
/**//*

                                   // showWeatherInfo(weather);
                               Logger.d( mTodayInHistory.getResult().size()+"");
                                TodayInHistoryAdapter todayInHistoryAdapter = new TodayInHistoryAdapter(getActivity(), mTodayInHistory.getResult());
                                mRecyclerView.setAdapter(todayInHistoryAdapter);
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                               // mSwipeRefresh.setRefreshing(false);
                            }
                        });
                        Logger.d(mTodayInHistory.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Logger.d(json);
            }
        });
    }
}
*/
