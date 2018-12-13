package com.jingwei.vega.moudle.bean;

/**
 * Created by cxc on 2018/12/13.
 * 商铺详情
 */
public class ShopDetailBean {

    /**
     * detail : {"newProductNumber":0,"headImg":"admin/c251639f34ba40e1865a979729a2c7d8.png","mainProducts":"网站","name":"机汇网","isLove":false,"remark":"我要测试一下","id":1,"productNumber":1,"backgroundPic":"admin/311569339ad44039b22905567f3465cb.png"}
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
         * newProductNumber : 0
         * headImg : admin/c251639f34ba40e1865a979729a2c7d8.png
         * mainProducts : 网站
         * name : 机汇网
         * isLove : false
         * remark : 我要测试一下
         * id : 1
         * productNumber : 1
         * backgroundPic : admin/311569339ad44039b22905567f3465cb.png
         */

        private int newProductNumber;
        private String headImg;
        private String mainProducts;
        private String name;
        private boolean isLove;
        private String remark;
        private int id;
        private int productNumber;
        private String backgroundPic;

        public int getNewProductNumber() {
            return newProductNumber;
        }

        public void setNewProductNumber(int newProductNumber) {
            this.newProductNumber = newProductNumber;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMainProducts() {
            return mainProducts;
        }

        public void setMainProducts(String mainProducts) {
            this.mainProducts = mainProducts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsLove() {
            return isLove;
        }

        public void setIsLove(boolean isLove) {
            this.isLove = isLove;
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

        public int getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public String getBackgroundPic() {
            return backgroundPic;
        }

        public void setBackgroundPic(String backgroundPic) {
            this.backgroundPic = backgroundPic;
        }
    }
}
