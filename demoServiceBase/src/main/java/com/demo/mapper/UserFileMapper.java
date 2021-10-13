package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import model.UserFileDto;

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
public interface UserFileMapper {

    List<UserFileDto> getUserFileById(@Param("userId") Long userId);
    
    int addUserFile(UserFileDto userFileDto);
    
    int deleteUserFileById(@Param("userId") Long userId, @Param("fileId") Long fileId);
    
}
