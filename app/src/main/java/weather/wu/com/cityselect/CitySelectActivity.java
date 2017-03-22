package weather.wu.com.cityselect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.orhanobut.logger.Logger;

import java.util.List;

import weather.wu.com.cityselect.adapter.CityListAdapter;
import weather.wu.com.cityselect.adapter.ResultListAdapter;
import weather.wu.com.cityselect.bean.City;
import weather.wu.com.cityselect.db.DBManager;
import weather.wu.com.cityselect.utils.LocateState;
import weather.wu.com.cityselect.utils.StringUtils;
import weather.wu.com.cityselect.view.SideLetterBar;
import weather.wu.com.weather.R;

public class CitySelectActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_PICKED_CITY = "picked_city";

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText mSearchEditText;
    private ImageView mClearImageView;
    private ImageView mBackImageView;
    private ViewGroup mEmptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;

    private AMapLocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityselect_activity_city_list);

        initData();
        initView();
        initLocation();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度
        option.setOnceLocation(true);//只定位一次
        option.setMockEnable(true);//虚拟定位
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        String location = StringUtils.extractLocation(city, district);//市县名称筛选
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        Logger.e(aMapLocation.getErrorInfo()+" "+ aMapLocation.getErrorCode());
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    private void initData() {
        dbManager = new DBManager(this);//初始化数据库
        dbManager.copyDBFile();//复制数据库
        mAllCities = dbManager.getAllCities();//获取到所有城市
        mCityAdapter = new CityListAdapter(this, mAllCities);//设置适配器
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {//通过回调回去城市
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.startLocation();
            }
        });

        mResultAdapter = new ResultListAdapter(this, null);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);//获取到letter所在的位置
                mListView.setSelection(position);
            }
        });

        mSearchEditText = (EditText) findViewById(R.id.et_search);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    mClearImageView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    mClearImageView.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        mEmptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getName());
            }
        });

        mClearImageView = (ImageView) findViewById(R.id.iv_search_clear);
        mBackImageView = (ImageView) findViewById(R.id.back);

        mClearImageView.setOnClickListener(this);
        mBackImageView.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //if(SpUtils.getBoolean(getApplicationContext(), SpUtils.FIRST_START, true))
        super.onBackPressed();

    }

    private void back(String city){
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_search_clear) {
            mSearchEditText.setText("");
            mClearImageView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.GONE);
            mResultListView.setVisibility(View.GONE);
        } else if (i == R.id.back) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
    }
}

