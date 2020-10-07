package com.sam.rentalcar.common;

import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;

import java.util.HashMap;
import java.util.Map;

/**
 * author:sam
 * time:2020/09/13
 * desc:
 * version:1.0
 */
class MyUserProvider implements EaseUI.EaseUserProfileProvider {
    private static MyUserProvider myUserProvider;
    // 设计成了单类。。
    private Map<String, EaseUser> userList = new HashMap<>();
    // 我服务器上获得头像的url
    private String ImgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1599919588265&di=0681b748d0c67a6fd80648503bcec053&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg";


    @Override
    public EaseUser getUser(String username) {
        if (userList.containsKey(username))
            //有就返归这个对象。。
            return userList.get(username);
        return null;
    }

    // 封装了一下
    public void setUser(String username, String nickname) {
        if (!userList.containsKey(username)) {
            EaseUser easeUser = new EaseUser(username);
            userList.put(username, easeUser);
        }
        EaseUser easeUser = getUser(username);
        // 不用怀疑。环信的easerUser的父类有一个setNickname 的方法可以用来设置昵称，直接调用就好。。
        easeUser.setNickname(nickname);
        // 同理，设置一个图像的url就好，因为他加载头像是使用glide加载的
        easeUser.setAvatar(ImgUrl + username);
    }

    // 按照你喜欢的修改一下初始化函数吧，
    private MyUserProvider() {

    }

    // 获取单类。。
    public static MyUserProvider getInstance() {
        if (myUserProvider == null) {
            myUserProvider = new MyUserProvider();
        }
        return myUserProvider;
    }
}
