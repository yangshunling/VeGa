package com.jingwei.vega.moudle.bean;

import java.util.List;

//以图搜图搜索结果
public class UploadSearchResultBean {

    /**
     * filePath : admin/tmp/2019/04/23/813e954a1c3b4c4d83da6addabfd573e.png
     * list : [{"iconImage":"admin/2019/04/02/7cc8065510114d5f8dcc4441733bd393.jpg","price":40,"name":"Balenciaga/巴黎世家","id":241},{"iconImage":"admin/2019/04/02/eb140bfeec9a489e89f3ce28884443b9.jpg","price":40,"name":"carhartt卡哈特","id":247},{"iconImage":"admin/2019/04/22/e470c165eb2e4e198ec848cc88711614.jpeg","price":70,"name":"Fendi短袖","id":430},{"iconImage":"admin/2019/04/23/d2fae88e981c40849d6674d31257492f.jpeg","price":35,"name":"冠军t恤","id":1031},{"iconImage":"admin/2019/04/22/25ea90e95de2444ca2df5ab035913e02.jpeg","price":38,"name":"巴宝莉短袖","id":506},{"iconImage":"admin/2019/04/23/0de7c4408eb74452968f66857982fafd.jpeg","price":40,"name":"hp短袖","id":859},{"iconImage":"admin/2019/04/22/b146e9409b6145c3ba14fd69fc8e256b.jpeg","price":45,"name":"  匡威情侣运动马裤五分裤，人棉面料，舒适，透气，微弹 面料最大的特点在于不起皱，多重色彩，更简单好搭配，运动，休闲任何场合下都能轻松驾驭，黑 藏青 米白，橘红，m～3XL，版型修身，模特身穿L 最大码能穿170斤左右","id":382},{"iconImage":"admin/2019/04/04/b49d7747ce0a42cea45a6696efb38c49.jpg","price":70,"name":"chrome hearts克罗心","id":288},{"iconImage":"admin/2019/04/22/b70d52315fbf4011acf755e2a9966fb1.jpeg","price":45,"name":"ch@mpion19新款短袖T恤，采用300g舒适纯棉面料，且经过洗水。不变形，不褪色。版型宽松，经典耐看。上身棒棒哒。 颜色：白色 黑色 蓝色","id":367},{"iconImage":"admin/2019/04/04/d4c48494fd2b43029ac79f1db05f2b24.jpg","price":85,"name":"GUCCI/古奇","id":284},{"iconImage":"admin/2019/04/22/ab4c6f837b5e431b90bd2aa9ee11db4e.jpeg","price":85,"name":"彪马裤子","id":492},{"iconImage":"admin/2019/04/22/9b03374739e44a22a04940eb1fa99c07.jpeg","price":35,"name":" Ev1su福神19新款短袖T恤，前襟印有多个口袋图案、海鸥及品牌格言。百搭各种运动裤，穿出动感型格。进口巴沙定制面料，精梭面手感工艺，舒适度极佳。不变形，不褪色。版型宽松，经典耐看。上身棒棒哒。 颜色：黑色 红色","id":330},{"iconImage":"admin/2019/04/22/0fa694f369624ae6a41b8de3d7b154fd.jpeg","price":95,"name":"Aj裤子","id":500},{"iconImage":"admin/2019/04/22/c283467faece4d2caa4196fff351b631.jpeg","price":90,"name":" 高品质福神情侣工装裤 ins潮牌火爆款，质量无话可说，水洗工艺纯棉材质，细节图可以看出品质，不起球，不褪色，束脚工艺，版型超好看，炸街神裤，喜欢修身的可选小一码 s～3XL，最大可穿180斤左右","id":364},{"iconImage":"admin/2019/04/23/3e8b58a3cec34d83babb95cdfa66eef8.jpeg","price":40,"name":"芝麻街短袖","id":662},{"iconImage":"admin/2019/04/22/75dfb3cb99864989aab9aecd143e1f5c.jpeg","price":45,"name":"19B0Y London夏季最新款短袖T恤，印花采用环保进口酱料，选用300g舒适纯棉面料，且经过洗水。不变形，不褪色。版型宽松，经典耐看。上身棒棒哒。 颜色：黑白 黑金 白黑","id":357},{"iconImage":"admin/2019/04/23/f03a04cfca5549debf3f72627ebd846e.jpeg","price":40,"name":"off white 短袖","id":577},{"iconImage":"admin/2019/04/22/41f3b934efed4aca99e5aecc85a24d7e.jpeg","price":30,"name":"19B0Y London夏季最新款短袖T恤，印花采用环保进口酱料，选用300g舒适纯棉面料，且经过洗水。不变形，不褪色。版型宽松，经典耐看。上身棒棒哒。 颜色：黑色","id":352},{"iconImage":"admin/2019/04/01/cfef0daf9d4842cb82e6a2b7c425045e.jpg","price":35,"name":"范斯 Vans","id":176},{"iconImage":"admin/2019/04/22/7174d50053dc47ce9ffb0559d2433581.jpeg","price":55,"name":"巴宝莉短袖","id":476}]
     * isHasNextPage : true
     * pageNumber : 1
     */

    private String filePath;
    private boolean isHasNextPage;
    private int pageNumber;
    private List<ListBean> list;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isIsHasNextPage() {
        return isHasNextPage;
    }

    public void setIsHasNextPage(boolean isHasNextPage) {
        this.isHasNextPage = isHasNextPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * iconImage : admin/2019/04/02/7cc8065510114d5f8dcc4441733bd393.jpg
         * price : 40
         * name : Balenciaga/巴黎世家
         * id : 241
         */

        private String iconImage;
        private int price;
        private String name;
        private int id;

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
    }
}
