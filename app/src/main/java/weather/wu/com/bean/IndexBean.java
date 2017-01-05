package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by 吴海辉 on 2016/12/2.
 *
 *   "index":{
 "yh":{
 "title":"适宜",
 "desc":"天气较好，适宜约会。"
 },
 "ls":{
 "title":"适宜晾晒 ",
 "desc":"天气不错，适宜晾晒衣物。"
 },
 "clothes":{
 "title":"较舒适",
 "desc":"建议穿薄外套或牛仔裤等服装。"
 },
 "dy":{
 "title":"适宜钓鱼 ",
 "desc":"风和日丽，适宜钓鱼。"
 },
 "sports":{
 "title":"暂缺",
 "desc":"暂缺"
 },
 "travel":{
 "title":" 适宜旅游 ",
 "desc":"享受大自然的无限风光吧。"
 },
 "beauty":{
 "title":"保湿",
 "desc":"请选用中性保湿型霜类化妆品。"
 },
 "xq":{
 "title":"较好",
 "desc":"温度适宜，心情会不错。"
 },
 "hc":{
 "title":"适宜",
 "desc":"天气较好，适宜划船及嬉玩水上运动。"
 },
 "zs":{
 "title":"无",
 "desc":"气温不高，中暑几率极低。"
 },
 "cold":{
 "title":"少发",
 "desc":"无明显降温，感冒机率较低。"
 },
 "gj":{
 "title":"适宜",
 "desc":"这种好天气去逛街可使身心畅快放松。"
 },
 "uv":{
 "title":"最弱",
 "desc":"辐射弱，涂擦SPF8-12防晒护肤品。"
 },
 "cl":{
 "title":"适宜晨练 ",
 "desc":"天气很好，是晨练的好时机。"
 },
 "glass":{
 "title":"需要",
 "desc":"白天根据户外光线情况佩戴太阳镜"
 },
 "wash_car":{
 "title":"较适宜",
 "desc":"无雨且风力较小，易保持清洁度。"
 },
 "aqi":{
 "title":"低",
 "desc":"空气质量好，无需防护"
 },
 "ac":{
 "title":"较少开启",
 "desc":"体感舒适，不需要开启空调。"
 },
 "mf":{
 "title":"一般",
 "desc":"建议选用防晒滋润型护发品或带遮阳帽。"
 },
 "ag":{
 "title":"不易过敏 ",
 "desc":"天气条件不利于过敏源传播。"
 },
 "pj":{
 "title":"较适宜",
 "desc":"适量的饮用啤酒，注意不要过量。"
 },
 "nl":{
 "title":"较适宜",
 "desc":"只要您稍作准备就可以放心外出。"
 },
 "pk":{
 "title":"较适宜",
 "desc":"风略小，会增加放飞风筝的难度。"
 }
 },
 */
public class IndexBean   extends DataSupport {
    public String mCityName;
    public String mYhTitle;
    public String mYhDesc;
    public String mLsTitle;
    public String mLsDesc;
    public String mClothesTitle;
    public String mClothesDesc;
    public String mDyTitle;
    public String mDyDesc;
    public String mSportsTitle;
    public String mSportsDesc;
    public String mTravelTitle;
    public String mTravelDesc;
    public String mBeautyTitle;
    public String mBeautyDesc;
    public String mXqTitle;
    public String mXqDesc;
    public String mHcTitle;
    public String mHcDesc;
    public String mZsTitle;
    public String mZsDesc;
    public String mColdTitle;
    public String mColdDesc;
    public String mGjTitle;
    public String mGjDesc;
    public String mUvTitle;
    public String mUvDesc;
    public String mClTitle;
    public String mClDesc;
    public String mGlassTitle;
    public String mGlassDesc;
    public String mWash_CarTitle;
    public String mWash_CarDesc;
    public String mAqiTitle;
    public String mAqiDesc;
    public String mAcTitle;//空调
    public String mAcDesc;//空调
    public String mMfTitle;
    public String mMfDesc;
    public String mAgTitle;//过敏
    public String mAgDesc;//过敏
    public String mPjTitle;
    public String mPjDesc;
    public String mNlTitle;
    public String mNlDesc;
    public String mPkTitle;
    public String mPkDesc;

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmYhTitle() {
        return mYhTitle;
    }

    public void setmYhTitle(String mYhTitle) {
        this.mYhTitle = mYhTitle;
    }

    public String getmYhDesc() {
        return mYhDesc;
    }

    public void setmYhDesc(String mYhDesc) {
        this.mYhDesc = mYhDesc;
    }

    public String getmLsTitle() {
        return mLsTitle;
    }

    public void setmLsTitle(String mLsTitle) {
        this.mLsTitle = mLsTitle;
    }

    public String getmLsDesc() {
        return mLsDesc;
    }

    public void setmLsDesc(String mLsDesc) {
        this.mLsDesc = mLsDesc;
    }

    public String getmClothesTitle() {
        return mClothesTitle;
    }

    public void setmClothesTitle(String mClothesTitle) {
        this.mClothesTitle = mClothesTitle;
    }

    public String getmClothesDesc() {
        return mClothesDesc;
    }

    public void setmClothesDesc(String mClothesDesc) {
        this.mClothesDesc = mClothesDesc;
    }

    public String getmDyTitle() {
        return mDyTitle;
    }

    public void setmDyTitle(String mDyTitle) {
        this.mDyTitle = mDyTitle;
    }

    public String getmDyDesc() {
        return mDyDesc;
    }

    public void setmDyDesc(String mDyDesc) {
        this.mDyDesc = mDyDesc;
    }

    public String getmSportsTitle() {
        return mSportsTitle;
    }

    public void setmSportsTitle(String mSportsTitle) {
        this.mSportsTitle = mSportsTitle;
    }

    public String getmSportsDesc() {
        return mSportsDesc;
    }

    public void setmSportsDesc(String mSportsDesc) {
        this.mSportsDesc = mSportsDesc;
    }

    public String getmTravelTitle() {
        return mTravelTitle;
    }

    public void setmTravelTitle(String mTravelTitle) {
        this.mTravelTitle = mTravelTitle;
    }

    public String getmTravelDesc() {
        return mTravelDesc;
    }

    public void setmTravelDesc(String mTravelDesc) {
        this.mTravelDesc = mTravelDesc;
    }

    public String getmBeautyTitle() {
        return mBeautyTitle;
    }

    public void setmBeautyTitle(String mBeautyTitle) {
        this.mBeautyTitle = mBeautyTitle;
    }

    public String getmBeautyDesc() {
        return mBeautyDesc;
    }

    public void setmBeautyDesc(String mBeautyDesc) {
        this.mBeautyDesc = mBeautyDesc;
    }

    public String getmXqTitle() {
        return mXqTitle;
    }

    public void setmXqTitle(String mXqTitle) {
        this.mXqTitle = mXqTitle;
    }

    public String getmXqDesc() {
        return mXqDesc;
    }

    public void setmXqDesc(String mXqDesc) {
        this.mXqDesc = mXqDesc;
    }

    public String getmHcTitle() {
        return mHcTitle;
    }

    public void setmHcTitle(String mHcTitle) {
        this.mHcTitle = mHcTitle;
    }

    public String getmHcDesc() {
        return mHcDesc;
    }

    public void setmHcDesc(String mHcDesc) {
        this.mHcDesc = mHcDesc;
    }

    public String getmZsTitle() {
        return mZsTitle;
    }

    public void setmZsTitle(String mZsTitle) {
        this.mZsTitle = mZsTitle;
    }

    public String getmZsDesc() {
        return mZsDesc;
    }

    public void setmZsDesc(String mZsDesc) {
        this.mZsDesc = mZsDesc;
    }

    public String getmColdTitle() {
        return mColdTitle;
    }

    public void setmColdTitle(String mColdTitle) {
        this.mColdTitle = mColdTitle;
    }

    public String getmColdDesc() {
        return mColdDesc;
    }

    public void setmColdDesc(String mColdDesc) {
        this.mColdDesc = mColdDesc;
    }

    public String getmGjTitle() {
        return mGjTitle;
    }

    public void setmGjTitle(String mGjTitle) {
        this.mGjTitle = mGjTitle;
    }

    public String getmGjDesc() {
        return mGjDesc;
    }

    public void setmGjDesc(String mGjDesc) {
        this.mGjDesc = mGjDesc;
    }

    public String getmUvTitle() {
        return mUvTitle;
    }

    public void setmUvTitle(String mUvTitle) {
        this.mUvTitle = mUvTitle;
    }

    public String getmUvDesc() {
        return mUvDesc;
    }

    public void setmUvDesc(String mUvDesc) {
        this.mUvDesc = mUvDesc;
    }

    public String getmClTitle() {
        return mClTitle;
    }

    public void setmClTitle(String mClTitle) {
        this.mClTitle = mClTitle;
    }

    public String getmClDesc() {
        return mClDesc;
    }

    public void setmClDesc(String mClDesc) {
        this.mClDesc = mClDesc;
    }

    public String getmGlassTitle() {
        return mGlassTitle;
    }

    public void setmGlassTitle(String mGlassTitle) {
        this.mGlassTitle = mGlassTitle;
    }

    public String getmGlassDesc() {
        return mGlassDesc;
    }

    public void setmGlassDesc(String mGlassDesc) {
        this.mGlassDesc = mGlassDesc;
    }

    public String getmWash_CarTitle() {
        return mWash_CarTitle;
    }

    public void setmWash_CarTitle(String mWash_CarTitle) {
        this.mWash_CarTitle = mWash_CarTitle;
    }

    public String getmWash_CarDesc() {
        return mWash_CarDesc;
    }

    public void setmWash_CarDesc(String mWash_CarDesc) {
        this.mWash_CarDesc = mWash_CarDesc;
    }

    public String getmAqiTitle() {
        return mAqiTitle;
    }

    public void setmAqiTitle(String mAqiTitle) {
        this.mAqiTitle = mAqiTitle;
    }

    public String getmAqiDesc() {
        return mAqiDesc;
    }

    public void setmAqiDesc(String mAqiDesc) {
        this.mAqiDesc = mAqiDesc;
    }

    public String getmAcTitle() {
        return mAcTitle;
    }

    public void setmAcTitle(String mAcTitle) {
        this.mAcTitle = mAcTitle;
    }

    public String getmAcDesc() {
        return mAcDesc;
    }

    public void setmAcDesc(String mAcDesc) {
        this.mAcDesc = mAcDesc;
    }

    public String getmMfTitle() {
        return mMfTitle;
    }

    public void setmMfTitle(String mMfTitle) {
        this.mMfTitle = mMfTitle;
    }

    public String getmMfDesc() {
        return mMfDesc;
    }

    public void setmMfDesc(String mMfDesc) {
        this.mMfDesc = mMfDesc;
    }

    public String getmAgTitle() {
        return mAgTitle;
    }

    public void setmAgTitle(String mAgTitle) {
        this.mAgTitle = mAgTitle;
    }

    public String getmAgDesc() {
        return mAgDesc;
    }

    public void setmAgDesc(String mAgDesc) {
        this.mAgDesc = mAgDesc;
    }

    public String getmPjTitle() {
        return mPjTitle;
    }

    public void setmPjTitle(String mPjTitle) {
        this.mPjTitle = mPjTitle;
    }

    public String getmPjDesc() {
        return mPjDesc;
    }

    public void setmPjDesc(String mPjDesc) {
        this.mPjDesc = mPjDesc;
    }

    public String getmNlTitle() {
        return mNlTitle;
    }

    public void setmNlTitle(String mNlTitle) {
        this.mNlTitle = mNlTitle;
    }

    public String getmNlDesc() {
        return mNlDesc;
    }

    public void setmNlDesc(String mNlDesc) {
        this.mNlDesc = mNlDesc;
    }

    public String getmPkTitle() {
        return mPkTitle;
    }

    public void setmPkTitle(String mPkTitle) {
        this.mPkTitle = mPkTitle;
    }

    public String getmPkDesc() {
        return mPkDesc;
    }

    public void setmPkDesc(String mPkDesc) {
        this.mPkDesc = mPkDesc;
    }

    @Override
    public String toString() {
        return "IndexBean{" +
                "mCityName='" + mCityName + '\'' +
                ", mYhTitle='" + mYhTitle + '\'' +
                ", mYhDesc='" + mYhDesc + '\'' +
                ", mLsTitle='" + mLsTitle + '\'' +
                ", mLsDesc='" + mLsDesc + '\'' +
                ", mClothesTitle='" + mClothesTitle + '\'' +
                ", mClothesDesc='" + mClothesDesc + '\'' +
                ", mDyTitle='" + mDyTitle + '\'' +
                ", mDyDesc='" + mDyDesc + '\'' +
                ", mSportsTitle='" + mSportsTitle + '\'' +
                ", mSportsDesc='" + mSportsDesc + '\'' +
                ", mTravelTitle='" + mTravelTitle + '\'' +
                ", mTravelDesc='" + mTravelDesc + '\'' +
                ", mBeautyTitle='" + mBeautyTitle + '\'' +
                ", mBeautyDesc='" + mBeautyDesc + '\'' +
                ", mXqTitle='" + mXqTitle + '\'' +
                ", mXqDesc='" + mXqDesc + '\'' +
                ", mHcTitle='" + mHcTitle + '\'' +
                ", mHcDesc='" + mHcDesc + '\'' +
                ", mZsTitle='" + mZsTitle + '\'' +
                ", mZsDesc='" + mZsDesc + '\'' +
                ", mColdTitle='" + mColdTitle + '\'' +
                ", mColdDesc='" + mColdDesc + '\'' +
                ", mGjTitle='" + mGjTitle + '\'' +
                ", mGjDesc='" + mGjDesc + '\'' +
                ", mUvTitle='" + mUvTitle + '\'' +
                ", mUvDesc='" + mUvDesc + '\'' +
                ", mClTitle='" + mClTitle + '\'' +
                ", mClDesc='" + mClDesc + '\'' +
                ", mGlassTitle='" + mGlassTitle + '\'' +
                ", mGlassDesc='" + mGlassDesc + '\'' +
                ", mWash_CarTitle='" + mWash_CarTitle + '\'' +
                ", mWash_CarDesc='" + mWash_CarDesc + '\'' +
                ", mAqiTitle='" + mAqiTitle + '\'' +
                ", mAqiDesc='" + mAqiDesc + '\'' +
                ", mAcTitle='" + mAcTitle + '\'' +
                ", mAcDesc='" + mAcDesc + '\'' +
                ", mMfTitle='" + mMfTitle + '\'' +
                ", mMfDesc='" + mMfDesc + '\'' +
                ", mAgTitle='" + mAgTitle + '\'' +
                ", mAgDesc='" + mAgDesc + '\'' +
                ", mPjTitle='" + mPjTitle + '\'' +
                ", mPjDesc='" + mPjDesc + '\'' +
                ", mNlTitle='" + mNlTitle + '\'' +
                ", mNlDesc='" + mNlDesc + '\'' +
                ", mPkTitle='" + mPkTitle + '\'' +
                ", mPkDesc='" + mPkDesc + '\'' +
                '}';
    }
}
