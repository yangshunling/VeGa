package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/20.
 * 下载详情
 */

public class DownloadRecordDetailBean {

    /**
     * productDesc : Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解
     * pictures : [{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}]
     */

    private String productDesc;
    private List<PicturesBean> pictures;

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public List<PicturesBean> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesBean> pictures) {
        this.pictures = pictures;
    }

    public static class PicturesBean {
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
}
