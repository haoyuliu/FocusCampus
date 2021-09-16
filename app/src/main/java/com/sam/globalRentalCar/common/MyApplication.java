package com.sam.globalRentalCar.common;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.billy.android.swipe.SmartSwipeBack;
import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.qiniu.pili.droid.shortvideo.PLShortVideoEnv;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.action.SwipeAction;
import com.sam.globalRentalCar.helper.ActivityStackManager;
import com.sam.globalRentalCar.ui.activity.CrashActivity;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;

import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * time   : 2018/10/18
 * desc   : 项目中的 Application 基类
 */
public final class MyApplication extends Application {
    public static final String TAG = "MyApplication";

    private static MyApplication mInstance;

    private static Context mContext;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        mInstance = this;
        super.onCreate();
        //initSDK(this);
        setContext(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    @Override
    public Context getApplicationContext() {

        return this;
    }

    private static void initShortVideo(Application application) {
        PLShortVideoEnv.init(application);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    private static void initEaseUi(Application application) {
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(false);
        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
        options.setRequireDeliveryAck(false);
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
        EaseUI.getInstance().init(application, null);

        //设置头像圆角
        EaseAvatarOptions easeAvatarOptions = new EaseAvatarOptions();
        easeAvatarOptions.setAvatarShape(1);
        EaseUI.getInstance().setAvatarOptions(easeAvatarOptions);
        // 设置头像和昵称
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                Log.d("username", username);
//                EaseUser easeUser = null;
//                if (username.equals(EMClient.getInstance().getCurrentUser())) {
//                    // 如果用户是本人，就设置自己的头像和昵称
//                    easeUser = new EaseUser(username);
//
//                    easeUser.setNickname(SPUtils.getInstance(mInstance).getString("NickName"));
//                   easeUser.setAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp9.itc.cn%2Fq_70%2Fimages03%2F20200618%2F46a8c11bfe9e4d75859493227184f6b7.jpeg&refer=http%3A%2F%2Fp9.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622788028&t=140aa7a9f441581604cae138940f6c92");
//                   // return easeUser;
//                } else {
//                    // 收到别人的消息，就设置别人的头像和昵称
//                    easeUser = new EaseUser(username);
//                    easeUser.setNickname(SPUtils.getInstance(mInstance).getString("OtherNickName"));
//                    easeUser.setAvatar(SPUtils.getInstance(mInstance).getString("OtherHeadImage"));
//                }

                //easeUser.setAvatar(e);
                //直接从存下下来的EaseUser里面获取
                return MyEMUserHelper.getEaseUser(getContext(), username);
                //return getus
            }
        });
        // 设置开启debug模式
        //  EMClient.getInstance().setDebugMode(true);
    }

    /**
     * 初始化一些第三方框架
     */
    public static void initSDK(Application application) {
        Log.d(TAG, "initSDK");
        initEaseUi(application);
        // 初始化七牛云短视频sdk
        initShortVideo(application);
//        // 友盟统计、登录、分享 SDK
//        UmengClient.init(application);

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
        CrashReport.initCrashReport(application, "5d2b2b733a", false);

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