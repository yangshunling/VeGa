package com.jingwei.vega.moudle.bean;

import java.util.List;

public class CategoryByOneBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 分类2
         * icon :
         * id : 746
         * isLeaf : false
         */

        private String name;
        private String icon;
        private int id;
        private boolean isLeaf;
        private boolean isTag;

        public ListBean(String name, String icon, int id, boolean isLeaf, boolean isTag) {
            this.name = name;
            this.icon = icon;
            this.id = id;
            this.isLeaf = isLeaf;
            this.isTag = isTag;
        }

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

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        public boolean isTag() {
            return isTag;
        }

        public void setTag(boolean tag) {
            isTag = tag;
        }
    }
}
