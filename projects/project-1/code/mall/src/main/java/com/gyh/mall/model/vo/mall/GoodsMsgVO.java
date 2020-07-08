package com.gyh.mall.model.vo.mall;

import java.sql.Date;

public class GoodsMsgVO {

    private Integer id;

    private String content;

    private String asker;

    private Date time;

    private GoodsMsgReplyVO reply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public GoodsMsgReplyVO getReply() {
        return reply;
    }

    public void setReply(GoodsMsgReplyVO reply) {
        this.reply = reply;
    }

    public GoodsMsgVO() {
    }

    public GoodsMsgVO(Integer id, String content, String asker, Date time, GoodsMsgReplyVO reply) {
        this.id = id;
        this.content = content;
        this.asker = asker;
        this.time = time;
        this.reply = reply;
    }
}
