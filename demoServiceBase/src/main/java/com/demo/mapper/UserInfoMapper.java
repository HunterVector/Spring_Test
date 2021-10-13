package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.UserInfoDto;


/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate ${date} <br>
 * @since V8.0<br>
 */
@Mapper
@Repository
public interface UserInfoMapper {

    UserInfoDto getUserInfoByUserCode(@Param("userCode")String userCode);
}
