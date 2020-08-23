package com.sam.rental.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyActivity;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.http.model.HttpData;
import com.sam.rental.http.request.UpdateImageApi;
import com.sam.rental.ui.dialog.AddressDialog;
import com.sam.rental.ui.dialog.InputDialog;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.widget.CircleImageView;
import com.sam.widget.layout.SettingBar;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * desc   : 个人资料页面
 */
public final class PersonalDataActivity extends MyActivity {

    @BindView(R.id.iv_person_data_avatar)
    CircleImageView mAvatarView;
    @BindView(R.id.sb_person_data_name)
    SettingBar mNameView;

    @BindView(R.id.sb_person_data_signature)
    SettingBar mSignatureView;

    @BindView(R.id.sb_person_data_sex)
    SettingBar mSexView;
    @BindView(R.id.sb_person_data_birthday)
    SettingBar mBirthdayView;
    @BindView(R.id.sb_person_data_address)
    SettingBar mAddressView;

    /**
     * 省
     */
    private String mProvince = "广东省";
    /**
     * 市
     */
    private String mCity = "广州市";
    /**
     * 区
     */
    private String mArea = "天河区";

    /**
     * 头像地址
     */
    private String mAvatarUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.iv_person_data_avatar, R.id.sb_person_data_name,
                R.id.sb_person_data_name, R.id.sb_person_data_address, R.id.sb_person_data_signature, R.id.sb_person_data_sex, R.id.sb_person_data_birthday);
    }

    @Override
    protected void initData() {
        GlideApp.with(getActivity())
                .load(R.drawable.ic_head_placeholder)
                .placeholder(R.drawable.ic_head_placeholder)
                .error(R.drawable.ic_head_placeholder)
                .circleCrop()
                .into(mAvatarView);

        String address = mProvince + mCity + mArea;
        mAddressView.setRightText(address);
        mNameView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("NickName"));
        mSignatureView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userDesc"));
        mSexView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userSex"));
        mBirthdayView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userBirthday"));
        mAddressView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userLocation"));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_person_data_avatar:
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelected(List<String> data) {
                        if (true) {
                            mAvatarUrl = data.get(0);
                            GlideApp.with(getActivity())
                                    .load(mAvatarUrl)
                                    .into(mAvatarView);
                            return;
                        }
                        // 上传头像
                        EasyHttp.post(getActivity())
                                .api(new UpdateImageApi()
                                        .setImage(new File(data.get(0))))
                                .request(new HttpCallback<HttpData<String>>(PersonalDataActivity.this) {

                                    @Override
                                    public void onSucceed(HttpData<String> data) {
                                        mAvatarUrl = data.getData();
                                        GlideApp.with(getActivity())
                                                .load(mAvatarUrl)
                                                .into(mAvatarView);
                                    }
                                });
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.sb_person_data_name:
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(mNameView.getRightText())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener((dialog, content) -> {
                            if (!mNameView.getRightText().equals(content)) {
                                mNameView.setRightText(content);
                            }
                        })
                        .show();
                break;
            case R.id.sb_person_data_address:
                new AddressDialog.Builder(this)
                        //.setTitle("选择地区")
                        // 设置默认省份
                        .setProvince(mProvince)
                        // 设置默认城市（必须要先设置默认省份）
                        .setCity(mCity)
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener((dialog, province, city, area) -> {
                            String address = province + city + area;
                            if (!mAddressView.getRightText().equals(address)) {
                                mProvince = province;
                                mCity = city;
                                mArea = area;
                                mAddressView.setRightText(address);
                            }
                        })
                        .show();
                break;

            default:
                break;
        }
    }
}