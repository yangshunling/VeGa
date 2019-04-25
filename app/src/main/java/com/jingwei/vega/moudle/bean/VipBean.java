package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/19.
 * vip等级
 */

public class VipBean {

    /**
     * remark : 权益
     * memberGradeList : [{"id":2,"name":"二级会员","serialNo":"Am_181208203336382596","amount":50,"status":1,"createdAt":1544272416000,"updatedAt":1544272416000},{"id":1,"name":"一级会员","serialNo":"Am_181208203336393074","amount":100,"status":1,"createdAt":1544272416000,"updatedAt":1544272416000}]
     */

    private String remark;
    private List<MemberGradeListBean> memberGradeList;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MemberGradeListBean> getMemberGradeList() {
        return memberGradeList;
    }

    public void setMemberGradeList(List<MemberGradeListBean> memberGradeList) {
        this.memberGradeList = memberGradeList;
    }

    public static class MemberGradeListBean {
        /**
         * id : 2
         * name : 二级会员
         * serialNo : Am_181208203336382596
         * amount : 50
         * status : 1
         * createdAt : 1544272416000
         * updatedAt : 1544272416000
         */

        private int id;
        private String name;
        private String serialNo;
        private double amount;
        private int status;
        private long createdAt;
        private long updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
