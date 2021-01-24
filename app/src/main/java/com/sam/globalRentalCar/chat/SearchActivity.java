package com.sam.globalRentalCar.chat;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.adapter.FindUserAdapter;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.FindUserListBean;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 查找好友功能
 */
public class SearchActivity extends MyActivity {

    @BindView(R.id.query_search)
    EditText mSearchEditText;
    @BindView(R.id.query_search_clear)
    ImageButton mSearchImageButton;
    @BindView(R.id.search_recycle)
    RecyclerView mSearchRecycleView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        Log.d("SearchActivity", "init");
        mSearchRecycleView.setLayoutManager(new LinearLayoutManager(this));
    /*    mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // conversationListView.filter(s);
                if (s.length() > 0) {
                    mSearchImageButton.setVisibility(View.VISIBLE);
                } else {
                    mSearchImageButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchEditText.getText().clear();
                hideSoftKeyboard();
            }
        });*/
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = mSearchEditText.getText().toString().trim();
                    Log.d("SearchActivity", "searchContent" + search);
                    initCaseSearch(search);
                    return true;
                }
                return false;
            }
        });
    }

    private void initCaseSearch(String search) {
        RetrofitClient.getRetrofitService().findUser(search).enqueue(new Callback<FindUserListBean>() {
            @Override
            public void onResponse(Call<FindUserListBean> call, Response<FindUserListBean> response) {
                FindUserListBean findUserListBean = response.body();
                if (findUserListBean.getCode().equals("200")) {
                    FindUserListBean.DataBean data = findUserListBean.getData();
                    FindUserAdapter findUserAdapter = new FindUserAdapter(SearchActivity.this, data.getRecords());
                    mSearchRecycleView.setAdapter(findUserAdapter);

                } else {
                    toast("网络错误");
                }
            }

            @Override
            public void onFailure(Call<FindUserListBean> call, Throwable t) {
                toast("网络错误");
            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftKeyboard() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}