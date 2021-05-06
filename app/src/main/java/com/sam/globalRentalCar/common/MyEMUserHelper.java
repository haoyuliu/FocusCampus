package com.sam.globalRentalCar.common;

import android.content.Context;
import android.text.TextUtils;

import com.hyphenate.easeui.domain.EaseUser;
import com.sam.globalRentalCar.utils.SPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyEMUserHelper {


    public static void putUser(Context context, String hxUserId, String nickName,String avatar){
        SPUtils.getInstance(context).put(hxUserId,hxUserId);
        SPUtils.getInstance(context).put(hxUserId+"nickName",nickName);
        SPUtils.getInstance(context).put(hxUserId+"avatar",avatar);
    }

    public static EaseUser getEaseUser(Context context,String hxUserId){
        String spHxUserId = SPUtils.getInstance(context).getString(hxUserId);
        EaseUser easeUser = null;
        if (!TextUtils.isEmpty(spHxUserId)){
            easeUser = new EaseUser(spHxUserId);
            easeUser.setNickname(SPUtils.getInstance(context).getString(hxUserId+"nickName"));
            easeUser.setAvatar(SPUtils.getInstance(context).getString(hxUserId + "avatar"));
        } else {
            easeUser = new EaseUser(hxUserId);
        }
        return easeUser;
    }


}
