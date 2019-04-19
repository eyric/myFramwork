package com.levin.framework.mybatis.my;

import com.levin.framework.mybatis.beans.Test;


public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}
