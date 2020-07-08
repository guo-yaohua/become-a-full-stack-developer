package com.gyh.mall.model.vo.mall;

import com.gyh.mall.model.vo.admin.SpecVO;

import java.util.List;

public class GoodsInfoVO {
    private String img;

    private String name;

    private String desc;

    private Integer typeId;

    private List<SpecVO> specs;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }

    public GoodsInfoVO() {
    }

    public GoodsInfoVO(String img, String name, String desc, Integer typeId, List<SpecVO> specs) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
        this.specs = specs;
    }
}
