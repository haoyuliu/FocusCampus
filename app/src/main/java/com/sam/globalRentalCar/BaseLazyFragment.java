package com.sam.globalRentalCar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


/**
 * author:sam
 * time:2020/09/08
 * desc:懒加载
 */
public abstract class BaseLazyFragment extends Fragment {
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

}
