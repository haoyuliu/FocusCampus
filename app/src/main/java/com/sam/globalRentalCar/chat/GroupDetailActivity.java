package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.view.View;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.widget.layout.SettingBar;

import butterknife.BindView;

/**
 * 群组详情页面
 */
public class GroupDetailActivity extends MyActivity {

    @BindView(R.id.group_id)
    SettingBar mGroupId;

    @BindView(R.id.group_owner)
    SettingBar mGroupOwner;

    @BindView(R.id.group_detail)
    SettingBar mGroupDetail;

    @BindView(R.id.group_desc_change)
    SettingBar mGroupDescChange;

    @BindView(R.id.group_out)
    SettingBar mGroupOut;

    String groupId;
    String groupName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_detail;
    }

    @Override
    protected void initView() {
        // 获取上个群组信息
        Intent intent = getIntent();
        //获取群组id
        String groupId = intent.getStringExtra(Constant.GROUP_ID);
        if (groupId != null) {
            mGroupId.setRightText(groupId);
            // 获取群组的信息
            EMGroup emGroup = EMClient.getInstance().groupManager().getGroup(groupId);
            //获取群组
            String owner = emGroup.getOwner();
            mGroupOwner.setRightText(owner);
            //获取群组描述信息
            String description = emGroup.getDescription();
            mGroupDetail.setRightText(description);
            // 获取群组名称
            String groupName = emGroup.getGroupName();
            mGroupDescChange.setRightText(groupName);
        }

        mGroupDescChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进去修改群组昵称界面
                Intent GroupDescIntent = new Intent(GroupDetailActivity.this, ModifyGroupNameActivity.class);
                intent.putExtra(Constant.GROUP_ID, groupId);
                intent.putExtra(Constant.GROUP_NAME, groupName);
                startActivity(GroupDescIntent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}