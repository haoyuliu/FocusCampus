package com.sam.globalRentalCar.http.response;

/**
 * author:sam
 * time:2020/08/23
 * desc:获取视频上传返回的数据实体了
 * * version:1.0
 */
public class UpLoadVideoResponseBean {


    /**
     * success : true
     * data : {"videoId":"eb04b91abc4c4ebb9469d0889087c1dc","uploadAddress":"eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJzdi8xYjM1YTAxMC0xNzQxOTU4Mjk4OC8xYjM1YTAxMC0xNzQxOTU4Mjk4OC5tcDQifQ==","uploadAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNWJGTWNEUmpvcFowSWF6YjBTQWdqZzhPdDlHaTdQSmdEejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWJOWDBycFNtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJqSFhKendLVG1ITXl4VkFDQUtmL1pqWUsxY2VXVVNiaHRsVTM4aWk0NlNOZzB3YTJJL1NIdFgrNzhmVHRQWlJUSWFFUHd2MjNMSlRpcjgwei9yeUZBUmhucm5OOU1BcU9yV2RiN3dJRXB4czluejZSWXF6WVplaFJHaURpN1h6VVNpRGlwS1RaNlpZVHpRMEZpT2VxVWhLMDEwckZmemkraWlGaWhuQWFaVk09IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlVwenplY1V4Z0RYbWIxZjhpNlBqZHZiYiIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA4LTIzVDA0OjIxOjAxWiIsIkFjY2Vzc0tleVNlY3JldCI6IjhOR2tVMndmeFgyUFpwTmdQTjVSc0hDamhydFZkeXRaaURHVnZVNUNka1ZwIiwiRXhwaXJhdGlvbiI6IjM0MTkiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9"}
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
         * videoId : eb04b91abc4c4ebb9469d0889087c1dc
         * uploadAddress : eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJzdi8xYjM1YTAxMC0xNzQxOTU4Mjk4OC8xYjM1YTAxMC0xNzQxOTU4Mjk4OC5tcDQifQ==
         * uploadAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNWJGTWNEUmpvcFowSWF6YjBTQWdqZzhPdDlHaTdQSmdEejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWJOWDBycFNtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJqSFhKendLVG1ITXl4VkFDQUtmL1pqWUsxY2VXVVNiaHRsVTM4aWk0NlNOZzB3YTJJL1NIdFgrNzhmVHRQWlJUSWFFUHd2MjNMSlRpcjgwei9yeUZBUmhucm5OOU1BcU9yV2RiN3dJRXB4czluejZSWXF6WVplaFJHaURpN1h6VVNpRGlwS1RaNlpZVHpRMEZpT2VxVWhLMDEwckZmemkraWlGaWhuQWFaVk09IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlVwenplY1V4Z0RYbWIxZjhpNlBqZHZiYiIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA4LTIzVDA0OjIxOjAxWiIsIkFjY2Vzc0tleVNlY3JldCI6IjhOR2tVMndmeFgyUFpwTmdQTjVSc0hDamhydFZkeXRaaURHVnZVNUNka1ZwIiwiRXhwaXJhdGlvbiI6IjM0MTkiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9
         */

        private String videoId;
        private String uploadAddress;
        private String uploadAuth;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getUploadAddress() {
            return uploadAddress;
        }

        public void setUploadAddress(String uploadAddress) {
            this.uploadAddress = uploadAddress;
        }

        public String getUploadAuth() {
            return uploadAuth;
        }

        public void setUploadAuth(String uploadAuth) {
            this.uploadAuth = uploadAuth;
        }
    }
}
