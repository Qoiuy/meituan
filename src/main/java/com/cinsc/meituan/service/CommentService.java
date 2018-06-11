package com.cinsc.meituan.service;

import com.cinsc.meituan.DTO.MTComment;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CommentService {
    public List<MTComment> getCommentByEPoiId(String token,String ePoiId,Long startTime,Long endTime) throws IOException, URISyntaxException;
    public Object saveMTComment(long shopId);
}
