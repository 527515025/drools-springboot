package com.us.drools.dao;

import com.us.drools.bean.Rules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface RulesDao {
     /**
      * 通过id 获取规则
      * @param id
      * @return
      */
     Rules getById (@Param("id")Integer id);
}
