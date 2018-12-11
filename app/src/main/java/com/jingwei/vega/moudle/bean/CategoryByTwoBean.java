package com.jingwei.vega.moudle.bean;

import java.util.List;

public class CategoryByTwoBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 小米
         * icon :
         * sonList : [{"name":"\b小米6","icon":"","id":743,"isLeaf":true},{"name":"\b小米5","icon":"","sonList":[],"id":742,"isLeaf":false}]
         * id : 741
         * isLeaf : false
         */

        private String name;
        private String icon;
        private int id;
        private boolean isLeaf;
        private List<SonListBean> sonList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(boolean isLeaf) {
            this.isLeaf = isLeaf;
        }

        public List<SonListBean> getSonList() {
            return sonList;
        }

        public void setSonList(List<SonListBean> sonList) {
            this.sonList = sonList;
        }

        public static class SonListBean {
            /**
             * name : 小米6
             * icon :
             * id : 743
             * isLeaf : true
             * sonList : []
             */

            private String name;
            private String icon;
            private int id;
            private boolean isLeaf;
            private List<?> sonList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(boolean isLeaf) {
                this.isLeaf = isLeaf;
            }

            public List<?> getSonList() {
                return sonList;
            }

            public void setSonList(List<?> sonList) {
                this.sonList = sonList;
            }
        }
    }
}
