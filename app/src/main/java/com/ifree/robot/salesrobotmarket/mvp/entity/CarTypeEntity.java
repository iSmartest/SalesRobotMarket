package com.ifree.robot.salesrobotmarket.mvp.entity;

import java.util.List;

/**
 * Created by za on 2018/7/23.
 */

public class CarTypeEntity {

    /**
     * data : {"carTypeList":[{"name":"奥迪a1","id":1},{"name":"奥迪a4","id":2},{"name":"奥迪Rs3","id":3}]}
     * resultCode : 0
     * msg : 操作成功
     */

    private DataBean data;
    private String resultCode;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<CarTypeListBean> carTypeList;

        public List<CarTypeListBean> getCarTypeList() {
            return carTypeList;
        }

        public void setCarTypeList(List<CarTypeListBean> carTypeList) {
            this.carTypeList = carTypeList;
        }

        public static class CarTypeListBean {
            /**
             * name : 奥迪a1
             * id : 1
             */

            private String name;
            private int id;
            private String address;
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
