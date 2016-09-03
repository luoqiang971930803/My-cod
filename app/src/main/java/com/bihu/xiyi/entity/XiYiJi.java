package com.bihu.xiyi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luo on 2016/8/24.
 */
public class XiYiJi {
    @SerializedName("objectId")
    private  String objectId;
    @SerializedName("img")
    private  String img;
    @SerializedName("floor")
    private  String floor;
    private  boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public XiYiJi(String floor, String objectId, String img) {
        this.floor = floor;
        this.objectId = objectId;
        this.img = img;

    }
}
