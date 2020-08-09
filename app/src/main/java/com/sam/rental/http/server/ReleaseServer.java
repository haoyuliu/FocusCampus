package com.sam.rental.http.server;

import com.hjq.http.config.IRequestServer;
import com.sam.rental.http.net.NetApiConstants;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 正式环境
 */
public class ReleaseServer implements IRequestServer {

    @Override
    public String getHost() {
        return NetApiConstants.BASE_URL;
    }

    @Override
    public String getPath() {
        return "api/";
    }
}