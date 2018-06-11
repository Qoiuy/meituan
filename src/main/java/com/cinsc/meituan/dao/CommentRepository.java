package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
    Comment findByCommentId(String commentId);

}
