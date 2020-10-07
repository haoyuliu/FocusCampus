package com.sam.rentalcar.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.CommentAdapter;
import com.sam.rentalcar.bean.VideoListBean;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.request.VideoCommentRequestBean;
import com.sam.rentalcar.http.response.CommentListBean;
import com.sam.rentalcar.http.response.VideoCommentResponseBean;
import com.sam.rentalcar.utils.SPUtils;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * create sam
 * description 评论弹框
 */
public class CommentDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_comment)
    TextView mTextViewComment;

    @BindView(R.id.et_comment)
    EditText mEditTextComment;
    private CommentAdapter commentAdapter;
    private List<CommentListBean.DataBean> datas = new ArrayList<>();
    private Long mUserId = null;
    private String mvideoId = null;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_comment, container);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), datas);
        recyclerView.setAdapter(commentAdapter);
        mTextViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoCommentRequestBean requestBean = new VideoCommentRequestBean();
                if (StringUtil.isEmpty(mEditTextComment.getText().toString())) {
                    Toast.makeText(getContext(), "请输入评论内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestBean.setContent(mEditTextComment.getText().toString());
                requestBean.setId(SPUtils.getInstance(getContext()).getString("UserId"));
                requestBean.setUserId(mUserId);
                requestBean.setVideoId(mvideoId);
                RetrofitClient.getRetrofitService().postVideoCommon(requestBean).enqueue(new Callback<VideoCommentResponseBean>() {
                    @Override
                    public void onResponse(Call<VideoCommentResponseBean> call, Response<VideoCommentResponseBean> response) {
                        VideoCommentResponseBean commentResponseBean = response.body();
                        if (commentResponseBean.getCode().equals("200")) {
                            Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
                            commentAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "评论失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VideoCommentResponseBean> call, Throwable t) {
                        Toast.makeText(getContext(), "评论失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 600;
    }

    public void setData(List<CommentListBean.DataBean> data) {
        this.datas = data;
    }


    public void setVideoid(String videoId) {
        this.mvideoId = videoId;
    }

    public void setUserid(Long userId) {
        this.mUserId = userId;
    }

}
