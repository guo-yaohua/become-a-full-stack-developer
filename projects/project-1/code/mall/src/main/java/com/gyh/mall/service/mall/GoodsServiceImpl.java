package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.GoodsDao;
import com.gyh.mall.dao.mall.GoodsDaoImpl;
import com.gyh.mall.model.Comment;
import com.gyh.mall.model.Msg;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.GoodsMsgBO;
import com.gyh.mall.model.vo.mall.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    /**
     * 商品留言
     * @param id
     * @return
     */
    @Override
    public List<GoodsMsgVO> getGoodsMsg(int id) {
        List<Msg> msgs = goodsDao.getGoodsMsg(id);
        List<GoodsMsgVO> msgList = new ArrayList<>();

        for (Msg msg : msgs) {
            GoodsMsgReplyVO reply = new GoodsMsgReplyVO(
                    msg.getReplyContent(),
                    msg.getUpdatetime()
            );
            GoodsMsgVO msgVO = new GoodsMsgVO(
                    msg.getId(),
                    msg.getContent(),
                    goodsDao.getUserName(msg.getUserId()),
                    msg.getCreatetime(),
                    reply
            );
            msgList.add(msgVO);
        }
        return msgList;
    }

    /**
     * 获取商品评价
     * @param goodsId
     * @return
     */
    @Override
    public GoodsCommentVO getGoodsComment(int goodsId) {
        List<Comment> comments = goodsDao.getGoodsComment(goodsId);
        List<GoodsCommentInfoVO> commentList = new ArrayList<>();

        int goodComment = 0;
        for (Comment comment : comments) {
            GoodsCommentUserVO user = new GoodsCommentUserVO(goodsDao.getUserName(comment.getUserId()));

            GoodsCommentInfoVO commentVO = new GoodsCommentInfoVO(
                    user,
                    comment.getScore(),
                    comment.getId(),
                    comment.getSpecName(),
                    comment.getComment(),
                    comment.getCreatetime(),
                    comment.getUserId()
            );

            // 好评数目
            if (comment.getScore() >= 60) goodComment++;

            commentList.add(commentVO);
        }
        GoodsCommentVO commentVO = new GoodsCommentVO();
        commentVO.setCommentList(commentList);

        double rate = (double) goodComment / comments.size() * 100;
        commentVO.setRate(rate);
        return commentVO;
    }

    /**
     * 搜索商品
     * @param keyword
     * @return
     */
    @Override
    public List<GoodsSerachVO> searchGoods(String keyword) {
        return goodsDao.searchGoods(keyword);
    }

    @Override
    public void askGoodsMsg(GoodsMsgBO msgBO) {
        User user = goodsDao.getUserByNickname(msgBO.getToken());
        Date date = new Date();

        Msg msg = new Msg(
                user.getId(),
                Integer.parseInt(msgBO.getGoodsId()),
                msgBO.getMsg(),
                1,
                new java.sql.Date(date.getTime())
        );
        goodsDao.askGoodsMsg(msg);
    }
}
