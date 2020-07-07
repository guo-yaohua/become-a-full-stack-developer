package com.gyh.mall.model.enumaration;

public enum OrderState {

    UN_PAID(0, "未付款"),

    UN_SHIPED(1, "未发货"),

    DELIVERED(2, "已发货"),

    RECEIVED(3, "已到货");

    private Integer code;

    private String value;

    OrderState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
