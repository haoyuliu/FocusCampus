package com.sam.rental.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.base.BaseDialog;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyActivity;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.http.model.HttpData;
import com.sam.rental.http.request.UpdateImageApi;
import com.sam.rental.ui.dialog.AddressDialog;
import com.sam.rental.ui.dialog.DateDialog;
import com.sam.rental.ui.dialog.InputDialog;
import com.sam.rental.ui.dialog.SelectDialog;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.widget.CircleImageView;
import com.sam.widget.layout.SettingBar;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
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
                        .setHint(getString(R.string.personal_data_name_hint))
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        .setAutoDismiss(false)
                        .setListener((dialog, content) -> {
                            if (!mNameView.getRightText().equals(content)) {
                                mNameView.setRightText(content);
                            }
                        })
                        .show();
                break;
            case R.id.sb_person_data_sex:
                // 性别
                new SelectDialog.Builder(this)
                        .setTitle("请选择你的性别")
                        .setList("男", "女")
                        // 设置单选模式
                        .setSingleSelect()
                        // 设置默认选中
                        .setSelect(0)
                        .setListener(new SelectDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, HashMap<Integer, String> data) {
                                toast("确定了：" + data.toString());
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.sb_person_data_birthday:
                // 日期选择对话框
                new DateDialog.Builder(this)
                        .setTitle(getString(R.string.date_title))
                        // 确定按钮文本
                        .setConfirm(getString(R.string.common_confirm))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.common_cancel))
                        // 设置日期
                        //.setDate("2018-12-31")
                        //.setDate("20181231")
                        //.setDate(1546263036137)
                        // 设置年份
                        //.setYear(2018)
                        // 设置月份
                        //.setMonth(2)
                        // 设置天数
                        //.setDay(20)
                        // 不选择天数
                        //.setIgnoreDay()
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
                                toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));

                                // 如果不指定时分秒则默认为现在的时间
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                // 月份从零开始，所以需要减 1
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                toast("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                toast("取消了");
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