package com.gyh.mall.model.bo.admin;

import java.util.List;

public class GoodsAddBO {
    private String name;

    private Integer typeId;

    private String img;

    private String desc;

    private List<SpecBO> specList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecBO> specList) {
        this.specList = specList;
    }
}
