package weather.wu.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import weather.wu.com.activity.GudieActivity;
import weather.wu.com.fragment.GuideFirstFragment;
import weather.wu.com.fragment.GuideSecondFragment;
import weather.wu.com.fragment.GuideThirdFragment;
import weather.wu.com.fragment.TestFragment;


public class GuideFragmentAdapter extends FragmentPagerAdapter {
//    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", "fsdaf"};

    private GuideFirstFragment mGudieFirstFragment = new GuideFirstFragment();
    private GuideSecondFragment mGudieSecondFragment = new GuideSecondFragment();
    private GuideThirdFragment mGudieThirdFragment = new GuideThirdFragment();

  //  private int mCount = CONTENT.length;

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
        return mGudieFirstFragment;
        }else if(position==1){
        return mGudieSecondFragment;
        }else if(position==2){
        return mGudieThirdFragment;
        }
        return  null;
    }

    @Override
    public int getCount() {
        return 3;
    }




}