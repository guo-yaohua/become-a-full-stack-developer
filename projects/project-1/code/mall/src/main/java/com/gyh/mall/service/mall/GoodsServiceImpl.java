package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.GoodsDao;
import com.gyh.mall.dao.mall.GoodsDaoImpl;
import com.gyh.mall.model.Comment;
import com.gyh.mall.model.Msg;
import com.gyh.mall.model.vo.mall.*;

import java.util.ArrayList;
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

            commentList.add(commentVO);
        }
        GoodsCommentVO commentVO = new GoodsCommentVO();
        commentVO.setCommentList(commentList);
        commentVO.setRate(0.0);
        return commentVO;
    }
}
