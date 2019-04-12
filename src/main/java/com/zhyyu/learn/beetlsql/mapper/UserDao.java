package com.zhyyu.learn.beetlsql.mapper;

import com.zhyyu.learn.beetlsql.po.User;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    List<User> select(String name);

}
