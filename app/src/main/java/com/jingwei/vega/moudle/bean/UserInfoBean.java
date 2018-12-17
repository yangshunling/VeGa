package com.jingwei.vega.moudle.bean;

import java.io.Serializable;

/**
 * Created by cxc on 2018/12/16.
 */

public class UserInfoBean implements Serializable{
    /**
     * nickName : 15988161520
     * mobile : 15988161520
     * headImg : null
     * sex : null
     * birth : null
     * isMember : true
     * memberTag : 会员试用期
     * startAt : 2018-12-17
     * endAt : 2018-12-24
     */

    private String nickName;
    private String mobile;
    private String headImg;
    //1男，2女
    private int sex;
    private String birth;
    //是否是会员
    private boolean isMember;
    private String memberTag;
    private String startAt;
    private String endAt;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public boolean isIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public String getMemberTag() {
        return memberTag;
    }

    public void setMemberTag(String memberTag) {
        this.memberTag = memberTag;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
