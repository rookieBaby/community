package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank
 * @create 2022-04-01 21:21
 */
@Service
public class DiscussPostService {

    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;


    public List<DiscussPost> findDiscussPost(int userId,int offset,int limit){
      return   discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDisCussPostRow(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }



}
