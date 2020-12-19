package com.sam.globalRentalCar.http.response;

/**
 * author:sam
 * time:2020/08/26
 * desc: 上传头像获取参数返回的实体类
 * version:1.0
 */
public class GetUpLoadImageResponseBean {

    /**
     * success : true
     * data : {"imageId":"7f748586e048449b920195ae805f5239","uploadAddress":"eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJpbWFnZS9kZWZhdWx0L0Q4MDk5OTc2RjcxMjQ3QTBBNjBDNTVEOUIyQ0M1QjNFLTYtMi5wbmcifQ==","uploadAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzB3UjFxNkZ0NUIyeWZTaklyNWIrUC83NWhZeDN4SkN6YjJ2SXJqSVVWTTVoallUdHNEejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWRod3VheFJtcDI4V2Y3d2FmK0FVQkxHQ1RtZDVNQVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1Qwa0ZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0pWS0RwaUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudC8yOHg5ZFNmdmFzM3NCYzdKS2o4M3JYTjVHZU81Q3pCUDVOVXdwbUhCRGRkSmoyc1lHRjh6ZnlvZ1hZS21nc01pV25jT1d4RXN3bk1qem50SVpWQWlLM1dsaU1lVS8xSjVjM2NTaWE5K0Z0bkJlbUE2cTB3UmZoWWUrUkRRajQxV0wydlExdU5Hb0FCRFJSUTFWZUhHTHZ1RjNJQTA2bmN2K3YzNVRJQUZGRkNETXJjVWNlTlo0eThyaTV5K2o2WkxkUlZwOVhBVzdxTWZDT3ArVmlCc2FkY2ZJUWV3dXNsU0ZUTVNaWXZjM1NzUE5hQ1lMc3ZoREhUSGhWb0RiWWpZT1VXRndvVFczLzlJbVgrcERFZUNoNDJMZjYySzdFSHlEalVDU0gxUWx1NUlmRmRYYzhjUU9NPSIsIkFjY2Vzc0tleUlkIjoiU1RTLk5VS3RETWhTVnNSWG1NeUoyQVhBTWJBRlIiLCJFeHBpcmVVVENUaW1lIjoiMjAyMC0wOC0yNlQxNToxNToyNFoiLCJBY2Nlc3NLZXlTZWNyZXQiOiJCZFRLWVRhQ2pzaURFeUFiVGpuRlAxZVpUUmltVjVDYjRVMmZLTFNBUWh2QyIsIkV4cGlyYXRpb24iOiIzNjAwIiwiUmVnaW9uIjoiY24tc2hhbmdoYWkifQ==","imageURL":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/D8099976F71247A0A60C55D9B2CC5B3E-6-2.png"}
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
         * imageId : 7f748586e048449b920195ae805f5239
         * uploadAddress : eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTg5MTlkNDZlY2JlMjExZWE4OWZmMDAxNjNlMDI0YzZhIiwiRmlsZU5hbWUiOiJpbWFnZS9kZWZhdWx0L0Q4MDk5OTc2RjcxMjQ3QTBBNjBDNTVEOUIyQ0M1QjNFLTYtMi5wbmcifQ==
         * uploadAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzB3UjFxNkZ0NUIyeWZTaklyNWIrUC83NWhZeDN4SkN6YjJ2SXJqSVVWTTVoallUdHNEejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRWM4VkdCZWJNcEFydE1nS3JGUHhKcGZadjh1ODRZQURpNUNqUWRod3VheFJtcDI4V2Y3d2FmK0FVQkxHQ1RtZDVNQVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1Qwa0ZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0pWS0RwaUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VuUDJWb3A4OFRnWWw3TjBGVkZpSWVJWThvVkkrdS9Mc3RCbkxxclBvREhudC8yOHg5ZFNmdmFzM3NCYzdKS2o4M3JYTjVHZU81Q3pCUDVOVXdwbUhCRGRkSmoyc1lHRjh6ZnlvZ1hZS21nc01pV25jT1d4RXN3bk1qem50SVpWQWlLM1dsaU1lVS8xSjVjM2NTaWE5K0Z0bkJlbUE2cTB3UmZoWWUrUkRRajQxV0wydlExdU5Hb0FCRFJSUTFWZUhHTHZ1RjNJQTA2bmN2K3YzNVRJQUZGRkNETXJjVWNlTlo0eThyaTV5K2o2WkxkUlZwOVhBVzdxTWZDT3ArVmlCc2FkY2ZJUWV3dXNsU0ZUTVNaWXZjM1NzUE5hQ1lMc3ZoREhUSGhWb0RiWWpZT1VXRndvVFczLzlJbVgrcERFZUNoNDJMZjYySzdFSHlEalVDU0gxUWx1NUlmRmRYYzhjUU9NPSIsIkFjY2Vzc0tleUlkIjoiU1RTLk5VS3RETWhTVnNSWG1NeUoyQVhBTWJBRlIiLCJFeHBpcmVVVENUaW1lIjoiMjAyMC0wOC0yNlQxNToxNToyNFoiLCJBY2Nlc3NLZXlTZWNyZXQiOiJCZFRLWVRhQ2pzaURFeUFiVGpuRlAxZVpUUmltVjVDYjRVMmZLTFNBUWh2QyIsIkV4cGlyYXRpb24iOiIzNjAwIiwiUmVnaW9uIjoiY24tc2hhbmdoYWkifQ==
         * imageURL : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/D8099976F71247A0A60C55D9B2CC5B3E-6-2.png
         */

        private String imageId;
        private String uploadAddress;
        private String uploadAuth;
        private String imageURL;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
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

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }
}
