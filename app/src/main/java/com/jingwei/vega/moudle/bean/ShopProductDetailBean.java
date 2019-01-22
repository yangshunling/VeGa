package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/15.
 * 商品详情
 */

public class ShopProductDetailBean {

    /**
     * detail : {"supplierName":"机汇网","picturesList":[{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}],"name":"满2件包邮Netty进阶之路 跟着案例学Netty","pdesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","isCollect":false,"remark":"Netty进阶之路：跟着案例学Netty（内容精选自1000多个一线业务实际案例，真正从原理到实践全景式讲解Netty项目实践，快速领悟Netty专家花大量时间积累的经验，提高编程水平及分析解决问题的能力","id":2,"iconImageList":[{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null}]}
     */

    private DetailBean detail;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * supplierName : 机汇网
         * picturesList : [{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}]
         * name : 满2件包邮Netty进阶之路 跟着案例学Netty
         * pdesc : Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解
         * isCollect : false
         * remark : Netty进阶之路：跟着案例学Netty（内容精选自1000多个一线业务实际案例，真正从原理到实践全景式讲解Netty项目实践，快速领悟Netty专家花大量时间积累的经验，提高编程水平及分析解决问题的能力
         * id : 2
         * iconImageList : [{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null}]
         * price:168
         */

        private String supplierName;
        private String supplierLogo;
        private String name;
        private String pdesc;
        private boolean isCollect;
        private String remark;
        private int id;
        private int price;
        private List<PicturesListBean> picturesList;
        private List<IconImageListBean> iconImageList;

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getName() {
            return name;
        }

        public String getSupplierLogo() {
            return supplierLogo;
        }

        public void setSupplierLogo(String supplierLogo) {
            this.supplierLogo = supplierLogo;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPdesc() {
            return pdesc;
        }

        public void setPdesc(String pdesc) {
            this.pdesc = pdesc;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<PicturesListBean> getPicturesList() {
            return picturesList;
        }

        public void setPicturesList(List<PicturesListBean> picturesList) {
            this.picturesList = picturesList;
        }

        public List<IconImageListBean> getIconImageList() {
            return iconImageList;
        }

        public void setIconImageList(List<IconImageListBean> iconImageList) {
            this.iconImageList = iconImageList;
        }

        public static class PicturesListBean {
            /**
             * path : admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg
             * type : MAIN_PIC
             * createdAt : null
             */

            private String path;
            private String type;
            private Object createdAt;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }
        }

        public static class IconImageListBean {
            /**
             * path : admin/2018-12/559e19e44e7246c286d312483c491169.jpg
             * type : MAIN_PIC
             * createdAt : null
             */

            private String path;
            private String type;
            private Object createdAt;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }
        }
    }
}
