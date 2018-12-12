package com.jingwei.vega.moudle.bean;

import java.util.List;

public class MarketListBean {

    /**
     * list : {"pageNum":1,"pageSize":10,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"id":3,"name":"\b北京","pic":"admin/d75e2195cb3d4087a773d6dc98e8b91c.jpg","status":1,"createdAt":1544457600000,"updatedAt":1544457600000},{"id":2,"name":"上海","pic":"admin/93f299abb0fc490995cb9aafcfef9e7c.jpg","status":1,"createdAt":1544457600000,"updatedAt":1544457600000},{"id":1,"name":"杭州","pic":"admin/22a45d61284a46f4b962503e9ce2e0b2.png","status":1,"createdAt":1544284800000,"updatedAt":1544284800000}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     */

    private ListBeanX list;

    public ListBeanX getList() {
        return list;
    }

    public void setList(ListBeanX list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 3
         * orderBy : null
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"id":3,"name":"\b北京","pic":"admin/d75e2195cb3d4087a773d6dc98e8b91c.jpg","status":1,"createdAt":1544457600000,"updatedAt":1544457600000},{"id":2,"name":"上海","pic":"admin/93f299abb0fc490995cb9aafcfef9e7c.jpg","status":1,"createdAt":1544457600000,"updatedAt":1544457600000},{"id":1,"name":"杭州","pic":"admin/22a45d61284a46f4b962503e9ce2e0b2.png","status":1,"createdAt":1544284800000,"updatedAt":1544284800000}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 0
         * lastPage : 1
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
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
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 3
             * name : 北京
             * pic : admin/d75e2195cb3d4087a773d6dc98e8b91c.jpg
             * status : 1
             * createdAt : 1544457600000
             * updatedAt : 1544457600000
             */

            private int id;
            private String name;
            private String pic;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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
