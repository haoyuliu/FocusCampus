package com.sam.rental.ui.fragment;

import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.rental.sam.utils.RxBus;
import com.sam.base.BaseFragment;
import com.sam.rental.R;
import com.sam.rental.bean.PauseVideoEvent;

import butterknife.BindView;

/**
 * 主页面的fragment，这个中又包含底部的五个子
 */
public class MainFragment extends BaseFragment {

    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private HomeFragment mHomeFragment;
    private RentalCarFragment mRentalCarFragment;
    private FragmentManagerHelper mFragmentManagerHelper;

    @BindView(R.id.tab_main_menu)
    XTabLayout tabMainMenu;

    @BindView(R.id.main_tab_content)
    FrameLayout mFrameLayout;

    private void setMainMenu() {
        tabMainMenu.addTab(tabMainMenu.newTab().setText("首页"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("租车"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText(""));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("消息"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("我的"));
    }

    /**
     * 底部按钮点击事件
     */
    private void setOnClick() {
        tabMainMenu.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //首页
                        Toast.makeText(getContext(), "首页", Toast.LENGTH_LONG).show();
                        //判断
                        if (mHomeFragment == null) {
                            mHomeFragment = new HomeFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mHomeFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 1:
                        //租车
                        Toast.makeText(getContext(), "租车", Toast.LENGTH_LONG).show();
                        //判断
                        if (mRentalCarFragment == null) {
                            mRentalCarFragment = new RentalCarFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mRentalCarFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 2:
                        //拍视频
                        Toast.makeText(getContext(), "拍视频", Toast.LENGTH_LONG).show();
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 3:
                        //消息
                        Toast.makeText(getContext(), "消息", Toast.LENGTH_LONG).show();
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        //判断
                        if (mMessageFragment == null) {
                            mMessageFragment = new MessageFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mMessageFragment);
                        break;
                    case 4:
                        //我的
                        Toast.makeText(getContext(), "我的", Toast.LENGTH_LONG).show();
                        if (mMineFragment == null) {
                            mMineFragment = new MineFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mMineFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mFragmentManagerHelper = new FragmentManagerHelper(getChildFragmentManager(), R.id.main_tab_content);
        //默认进来，加载首页
        mHomeFragment = new HomeFragment();
        //把第一个fragment添加进来,第一个参数是容器ID
        mFragmentManagerHelper.add(mHomeFragment);
        setMainMenu();
        setOnClick();
    }

    @Override
    protected void initData() {

    }
}
