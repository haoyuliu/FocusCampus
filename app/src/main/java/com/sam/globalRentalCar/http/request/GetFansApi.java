package com.sam.globalRentalCar.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author:sam
 * time:2020/08/09
 * desc:
 * version:1.0
 */
public class GetFansApi implements IRequestApi {
    @Override
    public String getApi() {
        return "/user/getFans";
    }
}
