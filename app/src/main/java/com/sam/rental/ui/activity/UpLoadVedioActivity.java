package com.sam.rental.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sam.rental.R;
import com.sam.rental.common.MyActivity;

/**
 * author:sam
 * time:2020/08/02
 * desc:
 * version:1.0
 */
public class UpLoadVedioActivity extends MyActivity {
    private Button mButtonUpLoad;
    private Button mButtonChiose;
    private TextView mTextViewAddress;
    String path;
    private String uploadAddress = "eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJzdi81NDk3MzdkYS0xNzM4OTZkYTBhNS81NDk3MzdkYS0xNzM4OTZkYTBhNS5tcDQifQ==";
    private String uploadAuth = "eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNWJnRC9iaGk2eFE3ZlNBT3hQejFYTmlXYzVvZ0lmZG96ejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWJkbDhic3JtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJWQ09BSDN0QUdBek1tWVY2eGx5MWtqSXNYWTFUcXlzalY5N0ZqRjFtdHF4YUVkWjR4dGd2b1IyYlU2Vm1zTkVTbXVtaUdTVElrSi93YVR1Rk9kcEVkODVQV28yTWhJY09Vb1hhWXY3QnlMRS9xcVBzL0tGdlpucXlIYXZLWTR1SnRTQmZSZkFXR1hha2o1Y2I1alVRM01hL3Zra1h3eEZFTVBlalpBRXJrN0k9IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlVVRExVZnNxWjZrOTVCMXM3VUFEb0J2QSIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA3LTI2VDA1OjQyOjA5WiIsIkFjY2Vzc0tleVNlY3JldCI6IjJrWkdqRm1RRUhBdEhWVGJGY3RNdko1TlcxVXJQZ0JZcmphNEdYTFh6TjFmIiwiRXhwaXJhdGlvbiI6IjM2MDAiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_load_vedio;
    }

    @Override
    protected void initView() {

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

            }
        });
        mButtonUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }

    private void upload() {

    }

}
