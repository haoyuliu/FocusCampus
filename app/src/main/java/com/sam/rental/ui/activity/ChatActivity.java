package com.sam.rental.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.sam.rental.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //use EaseChatFratFragment
        EaseChatFragment chatFragment = new EaseChatFragment();
        // pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat, chatFragment).commit();

    }
}
