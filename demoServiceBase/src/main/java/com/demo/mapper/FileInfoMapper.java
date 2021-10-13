package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import model.FileInfoDto;

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
@Mapper
public interface FileInfoMapper {
    
    FileInfoDto getFileInfoById(@Param("fileId") Long fileId);
    
    int addFileInfo(FileInfoDto fileInfoDto);
    
    int delFileInfoById(@Param("fileId") Long fileId);
}
