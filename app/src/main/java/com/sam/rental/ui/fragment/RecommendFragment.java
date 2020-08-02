package com.sam.rental.ui.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.VideoView;
import com.sam.rental.R;
import com.sam.rental.adapter.VideoAdapter;
import com.sam.rental.bean.VideoListBean;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.PersonalHomeActivity;
import com.sam.rental.widget.viewpagerlayoutmanager.OnViewPagerListener;
import com.sam.rental.widget.viewpagerlayoutmanager.ViewPagerLayoutManager;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 推荐页
 */
public class RecommendFragment extends MyFragment<HomeActivity> {
    private int pageIndex = 1;
    private int pageSize = 10;
    @BindView(R.id.recommend_recycle)
    VideoView mVideoview;

    @BindView(R.id.iv_personal)
    ImageView mImageView;



    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mVideoview.setUrl("https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/64e15ac-173aa30228f/64e15ac-173aa30228f.mp4");
        mVideoview.start(); //开始播放，不调用则不自动播放
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PersonalHomeActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        getVideoData(pageIndex, pageSize);
    }

    private void getVideoData(int pageIndex, int pageSize) {

    }

}
