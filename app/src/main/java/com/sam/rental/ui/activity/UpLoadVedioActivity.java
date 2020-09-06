package com.sam.rental.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClient;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.http.request.UpLoadVideoRequestBean;
import com.sam.rental.http.request.upLoadAfterRequestBean;
import com.sam.rental.http.response.UpLoadVideoResponseBean;
import com.sam.rental.http.response.upLoadAfterResponseBean;
import com.sam.rental.utils.GlideEngine;
import com.sam.rental.utils.SPUtils;

import java.net.HttpURLConnection;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

/**
 * author:sam
 * time:2020/08/02
 * desc:选择视频上传
 * version:1.0
 */
public class UpLoadVedioActivity extends MyActivity {
    private Button mButtonUpLoad;
    private Button mButtonChiose;
    private TextView mTextViewAddress;
    @BindView(R.id.video_title)
    EditText mEditTextVideoDescrition;
    String path;
    private String uploadAddress;
    private String uploadAuth;

    private String videoId;
    //private String uploadAddress = "eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJzdi81NDk3MzdkYS0xNzM4OTZkYTBhNS81NDk3MzdkYS0xNzM4OTZkYTBhNS5tcDQifQ==";
    //private String uploadAuth = "eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNWJnRC9iaGk2eFE3ZlNBT3hQejFYTmlXYzVvZ0lmZG96ejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWJkbDhic3JtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJWQ09BSDN0QUdBek1tWVY2eGx5MWtqSXNYWTFUcXlzalY5N0ZqRjFtdHF4YUVkWjR4dGd2b1IyYlU2Vm1zTkVTbXVtaUdTVElrSi93YVR1Rk9kcEVkODVQV28yTWhJY09Vb1hhWXY3QnlMRS9xcVBzL0tGdlpucXlIYXZLWTR1SnRTQmZSZkFXR1hha2o1Y2I1alVRM01hL3Zra1h3eEZFTVBlalpBRXJrN0k9IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlVVRExVZnNxWjZrOTVCMXM3VUFEb0J2QSIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA3LTI2VDA1OjQyOjA5WiIsIkFjY2Vzc0tleVNlY3JldCI6IjJrWkdqRm1RRUhBdEhWVGJGY3RNdko1TlcxVXJQZ0JZcmphNEdYTFh6TjFmIiwiRXhwaXJhdGlvbiI6IjM2MDAiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_load_vedio;
    }

    @Override
    protected void initView() {
        UpLoadVideoRequestBean requestBean = new UpLoadVideoRequestBean();
        requestBean.setDescription("描述信息");
        requestBean.setFileName("ssss.mp4");
        requestBean.setTitle("测试信息新信息信心");
        RetrofitClient.getRetrofitService().getUpLoadVideoParams(SPUtils.getInstance(UpLoadVedioActivity.this).getString("token"), requestBean)
                .enqueue(new Callback<UpLoadVideoResponseBean>() {
                    @Override
                    public void onResponse(Call<UpLoadVideoResponseBean> call, Response<UpLoadVideoResponseBean> response) {
                        UpLoadVideoResponseBean upLoadVideoResponseBean = response.body();
                        if (upLoadVideoResponseBean.getCode().equals("200")) {

                            uploadAddress = response.body().getData().getUploadAddress();
                            uploadAuth = response.body().getData().getUploadAuth();
                            videoId = response.body().getData().getVideoId();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpLoadVideoResponseBean> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void initData() {
        mButtonUpLoad = findViewById(R.id.but_upLoad);
        mButtonChiose = findViewById(R.id.but_choice_video);
        mTextViewAddress = findViewById(R.id.tv_address);
        mButtonChiose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入选择视屏的页面
                PictureSelector.create(UpLoadVedioActivity.this)
                        .openGallery(PictureMimeType.ofVideo())
                        .maxVideoSelectNum(1)
                        .isCompress(true)
                        .isCamera(false)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                // onResult CallbacK
                                path = result.get(0).getPath();
                                mTextViewAddress.setText(path);
                            }

                            @Override
                            public void onCancel() {
                                // onCancel Callback
                            }
                        });
            }
        });
        mButtonUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isEmpty(mEditTextVideoDescrition.getText().toString())) {
                    toast("请添加视频描述信息");
                    return;
                }
                if (StringUtil.isEmpty(mTextViewAddress.getText().toString())) {
                    toast("请选择视频");
                    return;
                }
                upload();
            }
        });
    }

    private void upload() {
        VODUploadClient uploader = new VODUploadClientImpl(getApplicationContext());
        VODUploadCallback vodUploadCallback = new VODUploadCallback() {
            @Override
            public void onUploadSucceed(UploadFileInfo info) {
                Log.d("文件上传", "成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UpLoadVedioActivity.this, "onsucceed ------------------" + info.getFilePath(), Toast.LENGTH_SHORT).show();
                        upLoadAfterRequestBean requestBean = new upLoadAfterRequestBean();
                        requestBean.setVideoDesc("视频描述信息");
                        requestBean.setVideoId(videoId);
                        RetrofitClient.getRetrofitService().UpLoadVideoAfter(SPUtils.getInstance(UpLoadVedioActivity.this).getString("token"), requestBean)
                                .enqueue(new Callback<upLoadAfterResponseBean>() {
                                    @Override
                                    public void onResponse(Call<upLoadAfterResponseBean> call, Response<upLoadAfterResponseBean> response) {
                                        Log.d("文件保存", "code" + response.code());
                                        if (response.code() == HttpURLConnection.HTTP_OK) {
                                            Toast.makeText(UpLoadVedioActivity.this, "保存成功 ------------------" + response.message(), Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<upLoadAfterResponseBean> call, Throwable t) {

                                    }
                                });

                    }
                });
            }

            @Override
            public void onUploadFailed(UploadFileInfo info, String code, String message) {
                Log.d("文件上传", "失败" + code + "message" + message);
                Toast.makeText(UpLoadVedioActivity.this, "onfailed ------------------ " + info.getFilePath() + " " + code + " " + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
                Log.d("文件上传中", "uploadedSize" + uploadedSize + "uploadedSize" + totalSize);
            }

            @Override
            public void onUploadTokenExpired() {
                Toast.makeText(UpLoadVedioActivity.this, "onExpired ------------- ", Toast.LENGTH_SHORT).show();

                uploader.resumeWithAuth(uploadAuth);
            }

            @Override
            public void onUploadRetry(String code, String message) {
                Toast.makeText(UpLoadVedioActivity.this, "onUploadRetry ------------- " + code + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadRetryResume() {
                Toast.makeText(UpLoadVedioActivity.this, "onUploadRetryResume ------------- ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUploadStarted(UploadFileInfo uploadFileInfo) {
                Log.d("文件上传", "开始");
                Toast.makeText(UpLoadVedioActivity.this, "onUploadStarted ------------- ", Toast.LENGTH_SHORT).show();

                uploader.setUploadAuthAndAddress(uploadFileInfo, uploadAuth, uploadAddress);
            }
        };
        uploader.init(vodUploadCallback);
        String filePath = path;
        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle("测试标题");
        vodInfo.setDesc("测试描述.");
        uploader.addFile(filePath, vodInfo);
        uploader.start();
    }

}
