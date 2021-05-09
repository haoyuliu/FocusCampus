package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.exceptions.HyphenateException;
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

    private String originGroupId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_group_name;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //获取群组id
        originGroupId = getIntent().getStringExtra(Constant.GROUP_ID);
    }

    @Override
    public void onRightClick(View v) {
        // 提交修改后的群组名称
        String groupNameNewTrim = mEditTextGroupName.getText().toString().trim();

        EMClient.getInstance().groupManager().asyncChangeGroupName(originGroupId, groupNameNewTrim, new EMCallBack() {
            @Override
            public void onSuccess() {
                toast("修改成功");
                //将修改后的名称，传递到上一个界面
                Intent intent = new Intent();
                intent.putExtra(Constant.GROUP_NAME, groupNameNewTrim);
                setResult(Constant.REQUEST_GROUP_CODE, intent);
                finish();
            }

            @Override
            public void onError(int i, String s) {
                toast("修改失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
