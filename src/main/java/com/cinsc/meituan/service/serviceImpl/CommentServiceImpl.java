package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.JsonData;
import com.cinsc.meituan.DTO.MTComment;
import com.cinsc.meituan.dao.CommentRepository;
import com.cinsc.meituan.service.CommentService;
import com.cinsc.meituan.util.CipCaterTakeOutCommentQueryRequest;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<MTComment> getCommentByEPoiId(String token, String ePoiId, Long startTime,Long endTime) throws IOException, URISyntaxException {
        CipCaterTakeOutCommentQueryRequest request = new CipCaterTakeOutCommentQueryRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setEPoiId(ePoiId);
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setOffset(0);
        request.setLimit(10);
        String json = request.doRequest();
        log.info("得到查询评论的json:{}",json);
        JsonData data = JsonUtil.parseObject(json,JsonData.class);
        List<MTComment> mtCommentList = JsonUtil.parseArray(data.getData(),MTComment.class);
        return mtCommentList;
    }

    @Override
    public Object saveMTComment(long shopId) {
        return null;
    }
}
