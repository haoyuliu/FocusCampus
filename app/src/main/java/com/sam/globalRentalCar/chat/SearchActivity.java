package com.sam.globalRentalCar.chat;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;

import butterknife.BindView;

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
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // conversationListView.filter(s);
                if (s.length() > 0) {
                    mSearchImageButton.setVisibility(View.VISIBLE);
                } else {
                    mSearchImageButton.setVisibility(View.INVISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchEditText.getText().clear();
                hideSoftKeyboard();
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