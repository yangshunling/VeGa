package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/20.
 * 下载记录
 */
public class DownloadRecordBean {

    /**
     * pageList : {"pageNum":0,"pageSize":0,"size":0,"orderBy":null,"startRow":0,"endRow":0,"total":0,"pages":0,"list":[{"supplierName":"机汇网","mainProducts":"网站","mainPic":{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null},"id":9,"productName":"满2件包邮Netty进阶之路 跟着案例学Netty"},{"supplierName":"机汇网","mainProducts":"网站","mainPic":{"path":"admin/c251639f34ba40e1865a979729a2c7d8.png","type":"MAIN_PIC","createdAt":null},"id":8,"productName":"iphone7 plus"}],"firstPage":0,"prePage":0,"nextPage":0,"lastPage":0,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":0,"navigatepageNums":null}
     */

    private PageListBean pageList;

    public PageListBean getPageList() {
        return pageList;
    }

    public void setPageList(PageListBean pageList) {
        this.pageList = pageList;
    }

    public static class PageListBean {
        /**
         * pageNum : 0
         * pageSize : 0
         * size : 0
         * orderBy : null
         * startRow : 0
         * endRow : 0
         * total : 0
         * pages : 0
         * list : [{"supplierName":"机汇网","mainProducts":"网站","mainPic":{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null},"id":9,"productName":"满2件包邮Netty进阶之路 跟着案例学Netty"},{"supplierName":"机汇网","mainProducts":"网站","mainPic":{"path":"admin/c251639f34ba40e1865a979729a2c7d8.png","type":"MAIN_PIC","createdAt":null},"id":8,"productName":"iphone7 plus"}]
         * firstPage : 0
         * prePage : 0
         * nextPage : 0
         * lastPage : 0
         * isFirstPage : false
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 0
         * navigatepageNums : null
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private Object orderBy;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int firstPage;
        private int prePage;
        private int nextPage;
        private int lastPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private Object navigatepageNums;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public Object getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(Object navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * supplierName : 机汇网
             * mainProducts : 网站
             * mainPic : {"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null}
             * id : 9
             * shopId:1
             * productName : 满2件包邮Netty进阶之路 跟着案例学Netty
             */

            private String supplierName;
            private String mainProducts;
            private MainPicBean mainPic;
            private int id;
            private int shopId;
            private String productName;

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public String getMainProducts() {
                return mainProducts;
            }

            public void setMainProducts(String mainProducts) {
                this.mainProducts = mainProducts;
            }

            public MainPicBean getMainPic() {
                return mainPic;
            }

            public void setMainPic(MainPicBean mainPic) {
                this.mainPic = mainPic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public static class MainPicBean {
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
}
