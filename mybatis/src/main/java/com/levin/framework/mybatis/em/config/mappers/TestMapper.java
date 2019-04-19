package com.levin.framework.mybatis.em.config.mappers;

import com.levin.framework.mybatis.beans.Test;


public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}
