package com.sam.rental.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/09/20
 * desc: 获取租车首页的信息列表
 * version:1.0
 */
public class GetRentalCarHomeMessageResponseBean {

    /**
     * success : true
     * data : {"lowPriceCar":[{"carId":1,"carName":"卡罗拉","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":2,"carName":"轩逸","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":3,"carName":"速腾","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":4,"carName":"迈腾","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":5,"carName":"帝豪","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350}],"myOrderCount":0}
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
         * lowPriceCar : [{"carId":1,"carName":"卡罗拉","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":2,"carName":"轩逸","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":3,"carName":"速腾","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":4,"carName":"迈腾","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350},{"carId":5,"carName":"帝豪","carPicture":"https://worldcar.oss-cn-beijing.aliyuncs.com/defaultCar.png","volume":"2L","transmission":"自动","seat":"5座","bodyType":"三厢","price":350}]
         * myOrderCount : 0
         */

        private int myOrderCount;
        private List<LowPriceCarBean> lowPriceCar;

        public int getMyOrderCount() {
            return myOrderCount;
        }

        public void setMyOrderCount(int myOrderCount) {
            this.myOrderCount = myOrderCount;
        }

        public List<LowPriceCarBean> getLowPriceCar() {
            return lowPriceCar;
        }

        public void setLowPriceCar(List<LowPriceCarBean> lowPriceCar) {
            this.lowPriceCar = lowPriceCar;
        }

        public static class LowPriceCarBean {
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
}
