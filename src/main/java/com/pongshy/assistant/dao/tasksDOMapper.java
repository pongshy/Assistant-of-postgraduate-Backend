package com.pongshy.assistant.dao;

import com.pongshy.assistant.model.entity.tasksDO;
import com.pongshy.assistant.model.entity.tasksDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface tasksDOMapper {
    int countByExample(tasksDOExample example);

    int deleteByExample(tasksDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(tasksDO record);

    int insertSelective(tasksDO record);

    List<tasksDO> selectByExample(tasksDOExample example);

    tasksDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") tasksDO record, @Param("example") tasksDOExample example);

    int updateByExample(@Param("record") tasksDO record, @Param("example") tasksDOExample example);

    int updateByPrimaryKeySelective(tasksDO record);

    int updateByPrimaryKey(tasksDO record);
}