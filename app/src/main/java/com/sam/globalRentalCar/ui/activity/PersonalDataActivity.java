package com.sam.globalRentalCar.ui.activity;

import android.util.Log;
import android.view.View;

import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClient;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.sam.base.BaseDialog;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.http.glide.GlideApp;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.GetUpLoadImageRequestBean;
import com.sam.globalRentalCar.http.request.ModifyMessageRequestBean;
import com.sam.globalRentalCar.http.response.GetUpLoadImageResponseBean;
import com.sam.globalRentalCar.http.response.ModifyMessageResponseBean;
import com.sam.globalRentalCar.ui.dialog.AddressDialog;
import com.sam.globalRentalCar.ui.dialog.DateDialog;
import com.sam.globalRentalCar.ui.dialog.SelectDialog;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.widget.CircleImageView;
import com.sam.widget.layout.SettingBar;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc   : 编辑个人资料页面
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

    private String imageId;
    private String uploadAddress;
    private String uploadAuth;
    private String imageURL;

    private int mSex = 0;
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
                .load(SPUtils.getInstance(PersonalDataActivity.this).getString("HeadImage"))
                .placeholder(R.drawable.ic_head_placeholder)
                .error(R.drawable.ic_head_placeholder)
                .circleCrop()
                .into(mAvatarView);

        String address = mProvince + mCity + mArea;
        mAddressView.setRightText(address);
        mNameView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("NickName"));
        if (SPUtils.getInstance(PersonalDataActivity.this).getString("userDesc") != null) {
            mSignatureView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userDesc"));
        }
        if (SPUtils.getInstance(PersonalDataActivity.this).getString("userSex").equals("1")) {
            mSexView.setRightText("女");
        } else {
            mSexView.setRightText("男");
        }
        mBirthdayView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userBirthday"));
        mAddressView.setRightText(SPUtils.getInstance(PersonalDataActivity.this).getString("userLocation"));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_person_data_avatar:
                getPreLoadImage();
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelected(List<String> data) {
                        if (true) {
                            mAvatarUrl = data.get(0);
                            GlideApp.with(getActivity())
                                    .load(mAvatarUrl)
                                    .into(mAvatarView);
                            // 上传头像
                            upload();
                            return;
                        }

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.sb_person_data_name:
                startActivity(ModifyNickNameActivity.class);
                break;
            case R.id.sb_person_data_signature:
                startActivity(ModifySignatureActivity.class);
                break;
            case R.id.sb_person_data_sex:
                // 性别
                new SelectDialog.Builder(this)
                        .setTitle("请选择你的性别")
                        .setList("男", "女")
                        // 设置单选模式
                        .setSingleSelect()
                        // 设置默认选中
                        .setSelect(mSex)
                        .setListener(new SelectDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, HashMap<Integer, String> data) {
                              //  toast("确定了：" + data.toString());
                                Set<Integer> integers = data.keySet();
                                for (Integer integerSex : integers) {
                                   // toast("确定了：" + integerSex);
                                    mSex = integerSex;
                                }
                                ModifyMessageRequestBean requestBean = new ModifyMessageRequestBean();
                                requestBean.setUserSex(mSex);
                                requestBean.setUserId(SPUtils.getInstance(PersonalDataActivity.this).getString("UserId"));
                                modifyUserData(requestBean);
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
                              //  toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));

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

    private void getPreLoadImage() {
        GetUpLoadImageRequestBean getUpLoadImageRequestBean = new GetUpLoadImageRequestBean();
        getUpLoadImageRequestBean.setImageExt("png");
        Log.d("PersonalDataActivity", "token" + SPUtils.getInstance(PersonalDataActivity.this).getString("token"));
        RetrofitClient.getRetrofitService().getUpLoadPictureParams(SPUtils.getInstance(PersonalDataActivity.this).getString("token"), getUpLoadImageRequestBean)
                .enqueue(new Callback<GetUpLoadImageResponseBean>() {
                    @Override
                    public void onResponse(Call<GetUpLoadImageResponseBean> call, Response<GetUpLoadImageResponseBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {

                            imageId = response.body().getData().getImageId();
                            uploadAuth = response.body().getData().getUploadAuth();
                            uploadAddress = response.body().getData().getUploadAddress();
                            imageURL = response.body().getData().getImageURL();

                        } else {
                            toast("网络错误");
                        }

                    }

                    @Override
                    public void onFailure(Call<GetUpLoadImageResponseBean> call, Throwable t) {
                        toast("网络错误");
                    }
                });

    }

    private void upload() {
        VODUploadClient uploader = new VODUploadClientImpl(getApplicationContext());
        VODUploadCallback vodUploadCallback = new VODUploadCallback() {
            @Override
            public void onUploadSucceed(UploadFileInfo info) {
                Log.d("图片上传", "成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(PersonalDataActivity.this, "onsucceed ------------------" + info.getFilePath(), Toast.LENGTH_SHORT).show();
                        ModifyMessageRequestBean requestBean = new ModifyMessageRequestBean();
                        requestBean.setHeadImg(imageURL);
                        requestBean.setUserId(SPUtils.getInstance(PersonalDataActivity.this).getString("UserId"));
                        modifyUserData(requestBean);

                    }
                });
            }

            @Override
            public void onUploadFailed(UploadFileInfo info, String code, String message) {
                Log.d("图片上传", "失败" + code + "message" + message);
               // Toast.makeText(PersonalDataActivity.this, "onfailed ------------------ " + info.getFilePath() + " " + code + " " + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
                Log.d("图片上传中", "uploadedSize" + uploadedSize + "uploadedSize" + totalSize);
            }

            @Override
            public void onUploadTokenExpired() {
               // Toast.makeText(PersonalDataActivity.this, "onExpired ------------- ", Toast.LENGTH_SHORT).show();

                uploader.resumeWithAuth(uploadAuth);
            }

            @Override
            public void onUploadRetry(String code, String message) {
                //Toast.makeText(PersonalDataActivity.this, "onUploadRetry ------------- " + code + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadRetryResume() {
               // Toast.makeText(PersonalDataActivity.this, "onUploadRetryResume ------------- ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadStarted(UploadFileInfo uploadFileInfo) {
                Log.d("图片上传", "开始");
               // Toast.makeText(PersonalDataActivity.this, "onUploadStarted ------------- ", Toast.LENGTH_SHORT).show();

                uploader.setUploadAuthAndAddress(uploadFileInfo, uploadAuth, uploadAddress);
            }
        };
        uploader.init(vodUploadCallback);
        String filePath = mAvatarUrl;
        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle("测试个人头像");
        vodInfo.setDesc("个人头像.");
        uploader.addFile(filePath, vodInfo);
        uploader.start();
    }

    private void modifyUserData(ModifyMessageRequestBean modifyMessageRequestBean) {

        RetrofitClient.getRetrofitService().modifyPersonalMessageParams(SPUtils.getInstance(PersonalDataActivity.this).getString("token"), modifyMessageRequestBean)
                .enqueue(new Callback<ModifyMessageResponseBean>() {
                    @Override
                    public void onResponse(Call<ModifyMessageResponseBean> call, Response<ModifyMessageResponseBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            toast("修改成功");
                            if (modifyMessageRequestBean.getHeadImg() != null) {
                                SPUtils.getInstance(PersonalDataActivity.this).put("HeadImage", modifyMessageRequestBean.getHeadImg());
                            }
                            SPUtils.getInstance(PersonalDataActivity.this).put("userSex", modifyMessageRequestBean.getUserSex());
                            GlideApp.with(getActivity())
                                    .load(SPUtils.getInstance(PersonalDataActivity.this).getString("HeadImage"))
                                    .placeholder(R.drawable.ic_head_placeholder)
                                    .error(R.drawable.ic_head_placeholder)
                                    .circleCrop()
                                    .into(mAvatarView);
                            if (modifyMessageRequestBean.getUserSex() == 0) {
                                mSexView.setRightText("男");
                            } else {
                                mSexView.setRightText("女");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ModifyMessageResponseBean> call, Throwable t) {
                        toast("修改失败");
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}