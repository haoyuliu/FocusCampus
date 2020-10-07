package com.sam.rentalcar.ui.fragment;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.List;

/**
 * Fragment的帮助类
 */
public class FragmentManagerHelper {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;

    /**
     * 构造方法
     *
     * @param fragmentManager 管理类
     * @param containerViewId 容器布局ID
     */
    public FragmentManagerHelper(@Nullable FragmentManager fragmentManager, @IdRes int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
    }

    /**
     * 添加Fragment的方法
     *
     * @param fragment
     */
    public void add(Fragment fragment) {
        //开启事务
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //把第一个fragment添加进来,第一个参数是容器ID
        fragmentTransaction.add(mContainerViewId, fragment);
        //一定要进行commit
        fragmentTransaction.commit();
    }

    /**
     * 切换显示Fragment
     *
     * @param fragment
     */
    public void switchFragment(Fragment fragment) {
        //开启事务
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        // 获取所有的Fragment
        List<Fragment> chileFragments = mFragmentManager.getFragments();
        //1. 首先隐藏所有的Fragment
        for (Fragment chileFragment : chileFragments) {
            fragmentTransaction.hide(chileFragment);
        }
        //2. 如果容器中没有，我们就添加
        if (!chileFragments.contains(fragment)) {
            fragmentTransaction.add(mContainerViewId, fragment);
        } else {
            //有，直接展示
            fragmentTransaction.show(fragment);
        }
        //一定要进行commit
        fragmentTransaction.commit();
    }
}
