package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class MTComment {
    private String commentId;
    private String addComment;
    private String addCommentTime;
    private String commentContent;
    private String deliveryCommentScore;//配送评分
    private String foodCommentScore;//菜品评分
    private String orderCommentScore;//综合评分
    private String result;
}
