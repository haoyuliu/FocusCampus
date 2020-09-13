package com.sam.rental.common;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.billy.android.swipe.SmartSwipeBack;
import com.bumptech.glide.Glide;
import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestServer;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.sam.rental.R;
import com.sam.rental.action.SwipeAction;
import com.sam.rental.helper.ActivityStackManager;
import com.sam.rental.http.model.RequestHandler;
import com.sam.rental.http.server.ReleaseServer;
import com.sam.rental.http.server.TestServer;
import com.sam.rental.other.AppConfig;
import com.sam.rental.ui.activity.CrashActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.umeng.UmengClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import okhttp3.OkHttpClient;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目中的 Application 基类
 */
public final class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK(this);
        initEaseUi();
    }

    private void initEaseUi() {
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        options.setAutoTransferMessageAttachments(true);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        // 调用初始化方法初始化sdk
        EaseUI.getInstance().init(this, options);
        //设置头像圆角
        EaseAvatarOptions easeAvatarOptions = new EaseAvatarOptions();
        easeAvatarOptions.setAvatarShape(1);
        EaseUI.getInstance().setAvatarOptions(easeAvatarOptions);
        // 设置头像和昵称
        //EaseUI.getInstance().setUserProfileProvider(MyUserProvider.getInstance());
        // 设置开启debug模式
        //  EMClient.getInstance().setDebugMode(true);
    }

    /**
     * 初始化一些第三方框架
     */
    public static void initSDK(Application application) {
        // 友盟统计、登录、分享 SDK
        UmengClient.init(application);

        // 吐司工具类
        ToastUtils.init(application);

        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor() {
            @Override
            public boolean intercept(Toast toast, CharSequence text) {
                boolean intercept = super.intercept(toast, text);
                if (intercept) {
                    Log.e("Toast", "空 Toast");
                } else {
                    Log.i("Toast", text.toString());
                }
                return intercept;
            }
        });

        // 标题栏全局样式
        TitleBar.initStyle(new TitleBarLightStyle(application) {

            @Override
            public Drawable getBackground() {
                return new ColorDrawable(getColor(R.color.colorPrimary));
            }

            @Override
            public Drawable getBackIcon() {
                return getDrawable(R.drawable.ic_back_black);
            }
        });

        // Bugly 异常捕捉
        CrashReport.initCrashReport(application, AppConfig.getBuglyId(), false);

        // Crash 捕捉界面
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
                .enabled(true)
                .trackActivities(true)
                .minTimeBetweenCrashesMs(2000)
                // 重启的 Activity
                .restartActivity(HomeActivity.class)
                // 错误的 Activity
                .errorActivity(CrashActivity.class)
                // 设置监听器
                //.eventListener(new YourCustomEventListener())
                .apply();

        // 设置全局的 Header 构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context).setEnableLastTime(false));
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));

        // Activity 栈管理初始化
        ActivityStackManager.getInstance().init(application);

        // 网络请求框架初始化
        IRequestServer server;
        if (AppConfig.isDebug()) {
            server = new TestServer();
        } else {
            server = new ReleaseServer();
        }

        EasyConfig.with(new OkHttpClient())
                // 是否打印日志
                .setLogEnabled(AppConfig.isDebug())
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler())
                // 设置请求重试次数
                .setRetryCount(3)
                // 添加全局请求参数
                //.addParam("token", "6666666")
                // 添加全局请求头
                //.addHeader("time", "20191030")
                // 启用配置
                .into();

        // Activity 侧滑返回
        SmartSwipeBack.activitySlidingBack(application, activity -> {
            if (activity instanceof SwipeAction) {
                return ((SwipeAction) activity).isSwipeEnable();
            }
            return true;
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        //MultiDex.install(this);
    }
}