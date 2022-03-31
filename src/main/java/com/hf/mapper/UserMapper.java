package com.hf.mapper;

import com.hf.commons.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserByName(@Param("username") String username);
}
