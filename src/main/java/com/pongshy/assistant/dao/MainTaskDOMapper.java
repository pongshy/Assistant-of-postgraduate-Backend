package com.pongshy.assistant.dao;

import com.pongshy.assistant.model.entity.MainTaskDO;
import com.pongshy.assistant.model.entity.MainTaskDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MainTaskDOMapper {
    int countByExample(MainTaskDOExample example);

    int deleteByExample(MainTaskDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MainTaskDO record);

    int insertSelective(MainTaskDO record);

    List<MainTaskDO> selectByExample(MainTaskDOExample example);

    MainTaskDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MainTaskDO record, @Param("example") MainTaskDOExample example);

    int updateByExample(@Param("record") MainTaskDO record, @Param("example") MainTaskDOExample example);

    int updateByPrimaryKeySelective(MainTaskDO record);

    int updateByPrimaryKey(MainTaskDO record);
}