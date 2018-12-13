package com.jingwei.vega.moudle.bean;

import java.util.List;

/**
 * Created by cxc on 2018/12/13.
 * 商铺新品
 */
public class ShopNewBean {

    /**
     * pageList : {"pageNum":0,"pageSize":0,"size":0,"orderBy":null,"startRow":0,"endRow":0,"total":0,"pages":0,"list":[{"iconImage":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","price":12,"name":"满2件包邮Netty进阶之路 跟着案例学Netty","id":2,"categoryId":751,"tags":[{"id":9,"name":"新品","status":1,"createdAt":1544630400000,"updatedAt":1544630400000}]},{"iconImage":"admin/c251639f34ba40e1865a979729a2c7d8.png","price":1000,"name":"iphone7 plus","id":1,"categoryId":743,"tags":[{"id":1,"name":"热门","status":1,"createdAt":1544198400000,"updatedAt":1544198400000}]}],"firstPage":0,"prePage":0,"nextPage":0,"lastPage":0,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":0,"navigatepageNums":null}
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
         * list : [{"iconImage":"admin/2018-12/559e19e44e7246c286d312483c491169.jpg","price":12,"name":"满2件包邮Netty进阶之路 跟着案例学Netty","id":2,"categoryId":751,"tags":[{"id":9,"name":"新品","status":1,"createdAt":1544630400000,"updatedAt":1544630400000}]},{"iconImage":"admin/c251639f34ba40e1865a979729a2c7d8.png","price":1000,"name":"iphone7 plus","id":1,"categoryId":743,"tags":[{"id":1,"name":"热门","status":1,"createdAt":1544198400000,"updatedAt":1544198400000}]}]
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
             * iconImage : admin/2018-12/559e19e44e7246c286d312483c491169.jpg
             * price : 12
             * name : 满2件包邮Netty进阶之路 跟着案例学Netty
             * id : 2
             * categoryId : 751
             * tags : [{"id":9,"name":"新品","status":1,"createdAt":1544630400000,"updatedAt":1544630400000}]
             */

            private String iconImage;
            private int price;
            private String name;
            private int id;
            private int categoryId;
            private List<TagsBean> tags;

            public String getIconImage() {
                return iconImage;
            }

            public void setIconImage(String iconImage) {
                this.iconImage = iconImage;
            }

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * id : 9
                 * name : 新品
                 * status : 1
                 * createdAt : 1544630400000
                 * updatedAt : 1544630400000
                 */

                private int id;
                private String name;
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
    }
}
