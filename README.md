# 基于Android的天气APP

## 前言
项目：https://github.com/w77996/Weather

多知天气，主要是给大家学习一下。

项目主要是12月23日开始建立，春节半个多月回家了，就没有写了。三月初完成了整个项目。耗时近两个月。平时在公司也就做点测试的任务，开发的任务还得等到毕业后才有TAT不知道毕业后会不会被留下，所以也是练练手。基于Rxjava+Retrofif+Ok之后也写了一个是http+Mvp的阅读类APP，链接在这里：[HiReader](https://github.com/w77996/HiReader)，项目还没有完全写完，一直在更新，主要是自己学习一些主流的东西。

---

## 感谢开源
看别人的代码慢慢学习，你会看到自己的成长的~
现在的我看这个毕业设计项目会觉得没有当初想的那么难了，可以用更高的效率，更好的方式去实现这个项目。
### 借鉴过的项目
[就看天气](https://github.com/xcc3641/SeeWeather)

[高仿雅虎天气](http://blog.csdn.net/way_ping_li/article/details/38963807)

还有郭神的第二行代码，不解释
### 开源框架
- Butterknife注解式框架 http://jakewharton.github.io/butterknife/
- Glide图片加载框架 https://github.com/bumptech/glide 
- Okhttp网络请求框架 http://square.github.io/okhttp/ 
- LitePal数据库操作框架 https://github.com/LitePalFramework/LitePal 
- Logger 开发日志框架 https://github.com/orhanobut/logger 
- Gson json数据解析框架 https://github.com/google/gson 
- SlidingMenu侧拉菜单框架 https://github.com/jfeinstein10/SlidingMenu 
- SwipeMenuListView侧滑删除框架 https://github.com/baoyongzhang/SwipeMenuListView/ 
- 易源数据提供的天气数据 https://www.showapi.com/ 
- 极光推送服务 https://www.jiguang.cn/accounts/platform 
- 有米广告平台 https://www.youmi.net/ 
- 高德地图 http://lbs.amap.com/ 

---
# 功能
1. 第一次打开APP引导页，缓冲加载
2. 天气信息的显示
3. 广告，推送
4. 桌面小工具
5. 新闻资讯的查看
6. 蓝牙串口传输温度
---

那时候还不大会使用Gson,简直就是Android开发的败笔呀~傻乎乎的自己去解析近千的json信息，也是醉了。
# 准备
易源数据中的天气Json如果请求的是15天的Json数据那有近千行，所以取自己有用的。



- 开发环境：Android studio
- 数据获取：易源数据SDK
## 帮助工具
[Json在线解析](http://www.json.cn/)

[易源数据官网](https://www.showapi.com/)

---

# Json数据分析


![image](http://img.blog.csdn.net/20161201125159764)

这么多Json,细思极恐啊~
不过我们一步步来分析，key的作用可以看易源的文档。
1. cityInfo 城市信息
2. time  时间
3. now  现在的天气
4. f1~f6  近一星期的天气预报信息
5. alarmList  预警信息
6. hourDataList  半小时更新一次的天气信息
7. aqi  空气质量


## 天气封装
在目录下新建com.weather.entity

其实觉得Gson挺好用的……
那我为何不用Gson呢。。。因为那时候还不会用@Serializedname，易源数据的json竟然还有用数字开头的key  ┭┮﹏┭┮

然后经过了一番的倒腾，终于一个个把数据给对上了= =，当做自己解析json数据的练手吧


## 网络请求
主要是用okhttp，用到的是郭神的几行代码


```
 public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    
```
直接传入地址就请求到数据了，

不过这边有个坑！！
有个坑！！！

有个坑！！！

重要的事情所三遍

在请求后不能直接使用response.body().string(),要缓存一下才能使用，不然为空。
```
  //使用okhttp的封装进行请求
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //这里有坑
                String responseText = response.body().string();
```

## 数据库

本人数据库超级烂，不过不算渣渣，还是能写点东西，数据库我设计的十分简单，只有两个重要的属性，就是城市名和json数据，为的是在无网络状态下从数据库直接获取到json数据，解析后显示到界面上。
框架的话本来打算用GreenDao的，不过后面改成LitePal了。

```
public class WeatherDB extends DataSupport {
    public int id;
    public String mCityName;
    public String mJsonData;
    ...
    
```
## 广告，推送
广告用的是有米广告，直接对着SDK把代码加进去就好了，想用腾讯的广告，但是打电话给我问我有没营业执照。。。。晕，没有所以没有审核通过。

推送使用的是极光推送，也是直接使用SDK,本来使用的是BOMB的，但是Bomb SDK中的okhttp和我自己的依赖起冲突了，而且还有一些机子无法推送成功，所以最后改成了极光推送，这都是血淋淋的坑啊，一步步踩过来简直吐血。

```
    //广告条初始化
        View bannerView = BannerManager.getInstance(WeatherActivity.this)
                .getBannerView(WeatherActivity.this, new BannerViewListener() {
                    @Override
                    public void onRequestSuccess() {}
                    @Override
                    public void onSwitchBanner() {}
                    @Override
                    public void onRequestFailed() {}
                });
        // 获取要嵌入广告条的布局
        LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);
        // 将广告条加入到布局中
        bannerLayout.addView(bannerView);
```
嵌入广告就是这么简单暴力，但是建议用积分墙，我这个是广告条。。。。。

因为。。。
他们广告条好像没啥广告。。。可能在实际中显示不出来，不过在初始化时使用测试广告的话那就可以看到了。



## 城市选择
这里推荐别人写的一个依赖，直接传送门 [CityPicker](https://github.com/zaaach/CityPicker)

用的是高德地图定位
![image](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/F31138F211A4AAC11E58A2579BF321BA.jpg)

## 桌面小工具
这方面我需要学习的东西还有很多的，开启服务在后台更新，想想后期如果能加入Rxjava，看看能不能优化一些操作，不过在使用Glide加载图片到AppWidget时需要获取到ImageView控件，所以有折腾了一下。
![image](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/D3D307BF3279B61CF6206E23E7335A5F.jpg)


```
   //通过APPWIdgetTarget获取到Image控件
                 mAppWidgetTarget =new AppWidgetTarget(getApplicationContext(),mRemoteViews,R.id.appwiget_picture,mAppwidgetId);
                Glide.with(getApplicationContext()).load(weatherBean.getmNowWeatherBean().getmWeather_Pic()).asBitmap().into(mAppWidgetTarget);
```

## 蓝牙和单片机通信模块
因为本人学过嵌入式开发，在机缘巧合的时候接触了Android，所以现在做Android开发，单片机上使用的是DS18B20温度传感器，蓝牙是HC-05,通过串口进行温度传输，不要觉得很难，其实很简单，嗯，说笑的，基础好就很简单啦，代码并不多，曾经试过一次返回数据一直都是乱码，找了一个星期的问题都没找到，最后发现是波特率的问题，太感动了，[传送门](http://note.youdao.com/)
## 界面
进入APP加载界面，淡入淡出效果，弱引用持有Activity对象，文字动画效果
![缓冲界面](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/11100A500F4EDDA45C3461786DB02552.jpg)

```
  //activity切换的淡入淡出效果
       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
       
```

```
//弱引用的使用
 private static class SwitchHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        SwitchHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mWeakReference.get();
            if (activity != null) {
                WeatherActivity.launch(activity);
                activity.finish();
            }
        }
    }
```
## 主界面
![image](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/2F981EF1CC18B1F1945AF9B93BFD0FCA.jpg)

这边其实没有按照Material design的要求透明化状态栏，主界面填充整个手机屏幕，通过计算手机屏幕总高度，减去状态栏的高度和ActionBar的高度，得出了主界面视图的高度，就做到了在任何分辨率下都只显示这样的界面的效果（原谅我这种拗口的表达，你懂就好哈哈哈哈~~）

```
  /**
     * 获取手机屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取手机屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 反射方法获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 20;
        try {
            Class<?> _class = Class.forName("com.android.internal.R$dimen");
            Object object = _class.newInstance();
            Field field = _class.getField("status_bar_height");
            int restult = Integer.parseInt(field.get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(
                    restult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Toast.makeText(getActivity(), "StatusBarHeight = " + statusBarHeight,
        // Toast.LENGTH_SHORT).show();
        return statusBarHeight;
    }

    /**
     * 获取?attr/actionBarSize高度
     *
     * @param context
     * @return
     */
    public static int getActionBarSize(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        return actionBarHeight;
    }
        // mNowWeatherHeight高度=屏幕高度-标题栏高度-状态栏高度
        mNowWeatherHeight = SystemUtils.getDisplayHeight(mContext) - SystemUtils.getActionBarSize(mContext) - SystemUtils.getStatusBarHeight(mContext);
```

## 每半小时更新的数据list和未来天气预报
这边是RecyclerView,在绘制每个Item的时候也是计算了屏幕的宽度，每个item占屏幕的1/5，所以
## 空气指数和生活指数

UI 很多都是从Android Studio中的Vetor assert里面找的，大家也可以去找找适合自己的UI,还有阿里的iconfont[阿里巴巴矢量图标库](http://www.iconfont.cn/)

![image](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/7BF01413872517AF752776C47756FDAE.jpg)

## 城市编辑界面

背景Activity半透明，填充屏幕，listview侧滑删除

进入Activity后从数据库中获取到数据，然后显示到listview中，进行操作后再主界面的onActivityResult中重新获取数据库中的内容，更新UI.

![image](https://raw.githubusercontent.com/w77996/BlogsImage/master/MoreKonwWeather/9CE5A2FCAC8C42F173BA51D0CC75F2AB.jpg)


``` 
//侧滑删除
SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(60));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);

            }
        };
        mCityEditListview.setMenuCreator(creator);
        mCityEditListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getApplicationContext(), position + " menu click", Toast.LENGTH_SHORT).show();
                        if(mCityList.size()>1){
                            CityRmoveThread cityRmoveThread = new CityRmoveThread(mCityList.get(position).getmCityName());
                            cityRmoveThread.start();
                            mCityList.remove(position);
                            mCityEditListAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), position + " 亲，删除了你看啥？", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                // false : 会关闭菜单; true ：不会关闭菜单
                return false;
            }
        });

```
## 新闻，笑话，美图
这个模块写的说实话我自己都看不下去了，那时候为了赶进度，，哎，意思一下。用的是sharepreference进行很简单的存储。嗯，就是这样的



# 总结

仅供参考，不得用于商业项目，如果觉得对你有帮助，给个Star吧亲~
