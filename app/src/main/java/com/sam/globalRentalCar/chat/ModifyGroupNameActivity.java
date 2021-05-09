package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.ModifyMessageRequestBean;
import com.sam.globalRentalCar.http.response.ModifyMessageResponseBean;
import com.sam.globalRentalCar.utils.SPUtils;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 修改群组名称
 */
public class ModifyGroupNameActivity extends MyActivity {

    @BindView(R.id.modify_group_name)
    EditText mEditTextGroupName;

    String groupid;

    String groupname;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_group_name;
    }

    @Override
    protected void initView() {
        //获取群组名称和群组id
        Intent intent = getIntent();
        groupid = intent.getStringExtra(Constant.GROUP_ID);
        groupname = intent.getStringExtra(Constant.GROUP_NAME);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        String trim = mEditTextGroupName.getText().toString().trim();

    }
}
