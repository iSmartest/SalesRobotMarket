package com.ifree.robot.salesrobotmarket.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class InsuranceEntity {

    /**
     * data : {"repairList":[{"date":1530062169000,"records":["张献忠进行了保险！！！"],"name":"中国人寿","items":"全险 强险 局部险 "},{"date":1529545290000,"records":[],"name":"中国人寿","items":"全险 强险 局部险 强险 "}]}
     * resultCode : 0
     * msg : 查询成功
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
        private List<RepairListBean> repairList;

        public List<RepairListBean> getRepairList() {
            return repairList;
        }

        public void setRepairList(List<RepairListBean> repairList) {
            this.repairList = repairList;
        }

        public static class RepairListBean {
            /**
             * date : 1530062169000
             * records : ["张献忠进行了保险！！！"]
             * name : 中国人寿
             * items : 全险 强险 局部险
             */

            private long date;
            private String name;
            private String items;
            private List<String> records;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getItems() {
                return items;
            }

            public void setItems(String items) {
                this.items = items;
            }

            public List<String> getRecords() {
                return records;
            }

            public void setRecords(List<String> records) {
                this.records = records;
            }
        }
    }
}
