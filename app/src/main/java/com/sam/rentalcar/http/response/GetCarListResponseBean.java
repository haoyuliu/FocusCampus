package com.sam.rentalcar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/09/20
 * desc: 获取可用车辆列表(点击租车后，包括用户选择过滤)
 * version:1.0
 */
public class GetCarListResponseBean {


    /**
     * success : true
     * data : [{"carId":1,"carName":"卡罗拉","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":2,"carName":"轩逸","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":5,"carName":"帝豪","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350}]
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private String msg;
    private String code;
    private Object traceId;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * carId : 1
         * carName : 卡罗拉
         * carPicture : https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png
         * volume : 2L
         * transmission : 自动
         * seat : 5座
         * bodyType : 三厢
         * price : 350
         */

        private int carId;
        private String carName;
        private String carPicture;
        private String volume;
        private String transmission;
        private String seat;
        private String bodyType;
        private int price;

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getCarPicture() {
            return carPicture;
        }

        public void setCarPicture(String carPicture) {
            this.carPicture = carPicture;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getTransmission() {
            return transmission;
        }

        public void setTransmission(String transmission) {
            this.transmission = transmission;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public String getBodyType() {
            return bodyType;
        }

        public void setBodyType(String bodyType) {
            this.bodyType = bodyType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
