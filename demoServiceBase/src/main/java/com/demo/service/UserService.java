package com.demo.service;

import java.util.List;

import model.UserFileDto;
import model.UserInfoDto;

/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate ${date} <br>
 * @see ${package_name} <br>
 * @since V8.0<br>
 */
public interface UserService {

    UserInfoDto getUserInfoBuCode(String userCode);
    
    List<UserFileDto> getUserFileById(Long userId);
    
    void addUserFile(UserFileDto userFileDto);
    
    void delUserFileById(Long userId, Long fileId);
}
