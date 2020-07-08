package com.gyh.mall.model.vo.mall;

import java.sql.Date;

public class GoodsMsgReplyVO {
    private String content;

    private Date time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public GoodsMsgReplyVO() {
    }

    public GoodsMsgReplyVO(String content, Date time) {
        this.content = content;
        this.time = time;
    }
}
