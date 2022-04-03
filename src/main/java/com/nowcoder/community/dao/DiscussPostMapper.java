package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Frank
 * @create 2022-04-01 20:31
 */
@Mapper
public interface DiscussPostMapper {

    /**
     *查询每条帖子，放在list集合中
     * @param userId  用户id
     * @param offset  每页起始行行号
     * @param limit   每页最多显示多少条数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);



    /**  @Param注解用于给参数取别名,
     *   如果只有一个参数,并且在<if>里使用,则必须加别名.
     * 查询帖子的总行数
     * @param userId  用户id
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
