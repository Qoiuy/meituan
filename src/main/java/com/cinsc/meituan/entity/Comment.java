package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Comment {
    @Id
    private String commentId;
    private String addComment;
    private String addCommentTime;
    private String commentContent;
    private Integer deliveryCommentScore;//配送评分
    private Integer foodCommentScore;//菜品评分
    private Integer orderCommentScore;//综合评分
}
