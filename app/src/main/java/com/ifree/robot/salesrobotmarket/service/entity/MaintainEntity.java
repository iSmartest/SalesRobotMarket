package com.ifree.robot.salesrobotmarket.service.entity;

import java.util.List;

/**
 * Created by za on 2018/7/3.
 */

public class MaintainEntity {
    /**
     * data : {"maintainList":[{"date":1530079565,"pohone":"18332212560","name":"李亚辉","id":3,"content":"张先生保养了发动机。"},{"date":null,"pohone":"18332212560","name":"李亚辉","id":4,"content":"车比较老"},{"date":null,"pohone":"18332212560","name":"李亚辉","id":5,"content":"共和国计划"}]}
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
        private List<DataBean.MaintainListBean> maintainList;

        public List<DataBean.MaintainListBean> getMaintainList() {
            return maintainList;
        }

        public void setMaintainList(List<DataBean.MaintainListBean> maintainList) {
            this.maintainList = maintainList;
        }

        public static class MaintainListBean {
            /**
             * date : 1530079565
             * pohone : 18332212560
             * name : 李亚辉
             * id : 3
             * content : 张先生保养了发动机。
             */
            private int date;
            private String pohone;
            private String name;
            private int id;
            private String content;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public String getPohone() {
                return pohone;
            }

            public void setPohone(String pohone) {
                this.pohone = pohone;
            }

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
