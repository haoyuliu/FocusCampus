package com.sam.rental.http.response;

/**
 * author:sam
 * time:2020/08/30
 * desc: 获取指定用户的个人主页信息
 * version:1.0
 */
public class GetUserHomePagerMessageResponseBean {


    /**
     * success : true
     * data : {"userId":368415507088539650,"userDesc":"榻榻米","nickName":"来劲","headImg":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/372F30FB78904BE694CCA97F8262868A-6-2.png","userSex":0,"userLocationCity":"北京","userLocationCityCode":"1010","userLocationProvince":"北京","userLocationProvinceCode":"10","userBirthday":"1989-11-12","userAge":30,"phone":"15065157316","hxuid":"hqyc15065157316","hasFollowed":0,"likesCount":0,"followCount":0,"fansCount":2}
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private DataBean data;
    private String msg;
    private String code;
    private Object traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getTraceId() {
        return traceId;
    }

    public void setTraceId(Object traceId) {
        this.traceId = traceId;
    }

    public static class DataBean {
        /**
         * userId : 368415507088539650
         * userDesc : 榻榻米
         * nickName : 来劲
         * headImg : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/372F30FB78904BE694CCA97F8262868A-6-2.png
         * userSex : 0
         * userLocationCity : 北京
         * userLocationCityCode : 1010
         * userLocationProvince : 北京
         * userLocationProvinceCode : 10
         * userBirthday : 1989-11-12
         * userAge : 30
         * phone : 15065157316
         * hxuid : hqyc15065157316
         * hasFollowed : 0
         * likesCount : 0
         * followCount : 0
         * fansCount : 2
         */

        private long userId;
        private String userDesc;
        private String nickName;
        private String headImg;
        private int userSex;
        private String userLocationCity;
        private String userLocationCityCode;
        private String userLocationProvince;
        private String userLocationProvinceCode;
        private String userBirthday;
        private int userAge;
        private String phone;
        private String hxuid;
        private int hasFollowed;
        private int likesCount;
        private int followCount;
        private int fansCount;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserDesc() {
            return userDesc;
        }

        public void setUserDesc(String userDesc) {
            this.userDesc = userDesc;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public String getUserLocationCity() {
            return userLocationCity;
        }

        public void setUserLocationCity(String userLocationCity) {
            this.userLocationCity = userLocationCity;
        }

        public String getUserLocationCityCode() {
            return userLocationCityCode;
        }

        public void setUserLocationCityCode(String userLocationCityCode) {
            this.userLocationCityCode = userLocationCityCode;
        }

        public String getUserLocationProvince() {
            return userLocationProvince;
        }

        public void setUserLocationProvince(String userLocationProvince) {
            this.userLocationProvince = userLocationProvince;
        }

        public String getUserLocationProvinceCode() {
            return userLocationProvinceCode;
        }

        public void setUserLocationProvinceCode(String userLocationProvinceCode) {
            this.userLocationProvinceCode = userLocationProvinceCode;
        }

        public String getUserBirthday() {
            return userBirthday;
        }

        public void setUserBirthday(String userBirthday) {
            this.userBirthday = userBirthday;
        }

        public int getUserAge() {
            return userAge;
        }

        public void setUserAge(int userAge) {
            this.userAge = userAge;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHxuid() {
            return hxuid;
        }

        public void setHxuid(String hxuid) {
            this.hxuid = hxuid;
        }

        public int getHasFollowed() {
            return hasFollowed;
        }

        public void setHasFollowed(int hasFollowed) {
            this.hasFollowed = hasFollowed;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }
    }
}
