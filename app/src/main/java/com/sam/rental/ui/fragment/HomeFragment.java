package com.sam.rental.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.sam.rental.R;
import com.sam.rental.bean.MainPageChangeEvent;
import com.sam.rental.bean.PauseVideoEvent;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目炫酷效果示例
 */
public final class HomeFragment extends MyFragment<HomeActivity> {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static int curMainPage;
    private MainFragment mainFragment = new MainFragment();
    //滑动到当前短视频的个人信息页面
    private PersonalHomeFragment personalHomeFragment = new PersonalHomeFragment();
    /**
     * 上次点击返回键时间
     */
    private long lastTime;
    /**
     * 连续按返回键退出时间
     */
    private final int EXIT_TIME = 2000;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        fragments.add(mainFragment);
        fragments.add(personalHomeFragment);
        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"", ""});
        viewPager.setAdapter(pagerAdapter);

        //点击头像切换页面
        com.rental.sam.utils.RxBus.getDefault().toObservable(MainPageChangeEvent.class)
                .subscribe((Action1<MainPageChangeEvent>) event -> {
                    if (viewPager != null) {
                        viewPager.setCurrentItem(event.getPage());
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curMainPage = position;
                if (position == 0) {
                    com.rental.sam.utils.RxBus.getDefault().post(new PauseVideoEvent(true));
                } else if (position == 1) {
                    com.rental.sam.utils.RxBus.getDefault().post(new PauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}