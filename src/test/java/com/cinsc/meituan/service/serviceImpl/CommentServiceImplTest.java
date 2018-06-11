package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.MTComment;
import com.cinsc.meituan.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;
    @Test
    public void getCommentByEPoiId() {
        try {
            List<MTComment> mtCommentList = commentService.getCommentByEPoiId("dbe26360771b4c67ee32a5fc62ad8fec34ac26259f7815eaea3cab05c8938aa7d096d32becdfb625e67b4cc77172b502",
                    "1526615352730257824",1480936199L,1481195399L);
            System.out.println(mtCommentList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveMTComment() {
    }
}