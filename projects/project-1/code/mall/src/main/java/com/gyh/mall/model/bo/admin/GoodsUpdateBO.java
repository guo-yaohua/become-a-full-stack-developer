package com.gyh.mall.model.bo.admin;

import java.util.List;

public class GoodsUpdateBO {

    private Integer id;

    private String name;

    private Integer typeId;

    private String img;

    private String desc;

    private List<SpecUpdateBO> specList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<SpecUpdateBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecUpdateBO> specList) {
        this.specList = specList;
    }
}
