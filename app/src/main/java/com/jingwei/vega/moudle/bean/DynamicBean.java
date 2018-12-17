package com.jingwei.vega.moudle.bean;

import java.util.List;

public class DynamicBean {

    /**
     * pageList : {"pageNum":0,"pageSize":0,"size":0,"orderBy":null,"startRow":0,"endRow":0,"total":0,"pages":0,"list":[{"createdAt":"2018/12/17 00:00","product":{"productDesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","productId":2,"pdesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","pictures":[{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}],"productName":"满2件包邮Netty进阶之路 跟着案例学Netty"},"headImg":"admin/c251639f34ba40e1865a979729a2c7d8.png","name":"机汇网","id":1}],"firstPage":0,"prePage":0,"nextPage":0,"lastPage":0,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":0,"navigatepageNums":null}
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
         * list : [{"createdAt":"2018/12/17 00:00","product":{"productDesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","productId":2,"pdesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","pictures":[{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}],"productName":"满2件包邮Netty进阶之路 跟着案例学Netty"},"headImg":"admin/c251639f34ba40e1865a979729a2c7d8.png","name":"机汇网","id":1}]
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
             * createdAt : 2018/12/17 00:00
             * product : {"productDesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","productId":2,"pdesc":"Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解","pictures":[{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}],"productName":"满2件包邮Netty进阶之路 跟着案例学Netty"}
             * headImg : admin/c251639f34ba40e1865a979729a2c7d8.png
             * name : 机汇网
             * id : 1
             */

            private String createdAt;
            private ProductBean product;
            private String headImg;
            private String name;
            private int id;

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
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

            public static class ProductBean {
                /**
                 * productDesc : Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解
                 * productId : 2
                 * pdesc : Netty是Java高性能网络编程的明星框架  在阿里等互联网公司Netty是程序员必须掌握的基础组件  现有Netty图书多是讲解其实现及原理的，缺少对实际应用的指导  在实际使用中遇到Netty故障，需要花大量实践摸索、试验解决  本书作者经过多年的积累，将遇到的问题进行分门别类的讲解
                 * pictures : [{"path":"admin/2018-12/f8b7a909ac5b47189ca34e98524d5c7e.jpg","type":"MAIN_PIC","createdAt":null}]
                 * productName : 满2件包邮Netty进阶之路 跟着案例学Netty
                 */

                private String productDesc;
                private int productId;
                private String pdesc;
                private String productName;
                private List<PicturesBean> pictures;

                public String getProductDesc() {
                    return productDesc;
                }

                public void setProductDesc(String productDesc) {
                    this.productDesc = productDesc;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public String getPdesc() {
                    return pdesc;
                }

                public void setPdesc(String pdesc) {
                    this.pdesc = pdesc;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
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
        }
    }
}

