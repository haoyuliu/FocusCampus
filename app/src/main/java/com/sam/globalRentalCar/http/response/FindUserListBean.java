package com.sam.globalRentalCar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/08/16
 * desc: 查找好友数据返回实体类
 * version:1.0
 */
public class FindUserListBean {


    /**
     * success : true
     * data : {"records":[{"userId":368415507088539650,"userCode":23423,"realName":null,"nickName":"隔壁家翠花","headImg":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/ACD17DE0D9614922A5402A6B1A1F3D1B-6-2.png","phone":"15065157316","userSex":0,"userDesc":"卡贴机测序UKKKK路退了吃啦挎邋邋遢遢踏踏额乱","userBirthday":null,"userLocation":"string","token":null,"hxuid":null,"hxpwd":null}],"total":1,"size":50,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"userId":368415507088539650,"userCode":23423,"realName":null,"nickName":"隔壁家翠花","headImg":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/ACD17DE0D9614922A5402A6B1A1F3D1B-6-2.png","phone":"15065157316","userSex":0,"userDesc":"卡贴机测序UKKKK路退了吃啦挎邋邋遢遢踏踏额乱","userBirthday":null,"userLocation":"string","token":null,"hxuid":null,"hxpwd":null}]
         * total : 1
         * size : 50
         * current : 1
         * orders : []
         * optimizeCountSql : true
         * hitCount : false
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean optimizeCountSql;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isOptimizeCountSql() {
            return optimizeCountSql;
        }

        public void setOptimizeCountSql(boolean optimizeCountSql) {
            this.optimizeCountSql = optimizeCountSql;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * userId : 368415507088539650
             * userCode : 23423
             * realName : null
             * nickName : 隔壁家翠花
             * headImg : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/ACD17DE0D9614922A5402A6B1A1F3D1B-6-2.png
             * phone : 15065157316
             * userSex : 0
             * userDesc : 卡贴机测序UKKKK路退了吃啦挎邋邋遢遢踏踏额乱
             * userBirthday : null
             * userLocation : string
             * token : null
             * hxuid : null
             * hxpwd : null
             */

            private long userId;
            private int userCode;
            private Object realName;
            private String nickName;
            private String headImg;
            private String phone;
            private int userSex;
            private String userDesc;
            private Object userBirthday;
            private String userLocation;
            private Object token;
            private Object hxuid;
            private Object hxpwd;

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public int getUserCode() {
                return userCode;
            }

            public void setUserCode(int userCode) {
                this.userCode = userCode;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getUserSex() {
                return userSex;
            }

            public void setUserSex(int userSex) {
                this.userSex = userSex;
            }

            public String getUserDesc() {
                return userDesc;
            }

            public void setUserDesc(String userDesc) {
                this.userDesc = userDesc;
            }

            public Object getUserBirthday() {
                return userBirthday;
            }

            public void setUserBirthday(Object userBirthday) {
                this.userBirthday = userBirthday;
            }

            public String getUserLocation() {
                return userLocation;
            }

            public void setUserLocation(String userLocation) {
                this.userLocation = userLocation;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getHxuid() {
                return hxuid;
            }

            public void setHxuid(Object hxuid) {
                this.hxuid = hxuid;
            }

            public Object getHxpwd() {
                return hxpwd;
            }

            public void setHxpwd(Object hxpwd) {
                this.hxpwd = hxpwd;
            }
        }
    }
}
