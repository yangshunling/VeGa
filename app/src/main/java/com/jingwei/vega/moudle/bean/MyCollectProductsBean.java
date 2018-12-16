package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/16.
 * 我的收藏
 */
public class MyCollectProductsBean {

    /**
     * pageList : {"pageNum":0,"pageSize":0,"size":0,"orderBy":null,"startRow":0,"endRow":0,"total":0,"pages":0,"list":[{"iconImage":[{"path":"admin/c251639f34ba40e1865a979729a2c7d8.png","type":"MAIN_PIC","createdAt":null}],"price":1000,"name":"iphone7 plus","remark":"苹果手机","id":1},{"iconImage":[{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null}],"price":12,"name":"满2件包邮Netty进阶之路 跟着案例学Netty","remark":"Netty进阶之路：跟着案例学Netty（内容精选自1000多个一线业务实际案例，真正从原理到实践全景式讲解Netty项目实践，快速领悟Netty专家花大量时间积累的经验，提高编程水平及分析解决问题的能力","id":2}],"firstPage":0,"prePage":0,"nextPage":0,"lastPage":0,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":0,"navigatepageNums":null}
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
         * list : [{"iconImage":[{"path":"admin/c251639f34ba40e1865a979729a2c7d8.png","type":"MAIN_PIC","createdAt":null}],"price":1000,"name":"iphone7 plus","remark":"苹果手机","id":1},{"iconImage":[{"path":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","type":"MAIN_PIC","createdAt":null}],"price":12,"name":"满2件包邮Netty进阶之路 跟着案例学Netty","remark":"Netty进阶之路：跟着案例学Netty（内容精选自1000多个一线业务实际案例，真正从原理到实践全景式讲解Netty项目实践，快速领悟Netty专家花大量时间积累的经验，提高编程水平及分析解决问题的能力","id":2}]
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
             * iconImage : [{"path":"admin/c251639f34ba40e1865a979729a2c7d8.png","type":"MAIN_PIC","createdAt":null}]
             * price : 1000
             * name : iphone7 plus
             * remark : 苹果手机
             * id : 1
             */

            private int price;
            private String name;
            private String remark;
            private int id;
            private List<IconImageBean> iconImage;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public List<IconImageBean> getIconImage() {
                return iconImage;
            }

            public void setIconImage(List<IconImageBean> iconImage) {
                this.iconImage = iconImage;
            }

            public static class IconImageBean {
                /**
                 * path : admin/c251639f34ba40e1865a979729a2c7d8.png
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
