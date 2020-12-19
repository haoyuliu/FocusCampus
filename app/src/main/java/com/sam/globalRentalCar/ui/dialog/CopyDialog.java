package com.sam.globalRentalCar.ui.dialog;

import android.content.Context;
import android.view.Gravity;

import com.sam.base.BaseDialog;
import com.sam.base.action.AnimAction;
import com.sam.globalRentalCar.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.dialog_copy);
            setAnimStyle(AnimAction.BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}