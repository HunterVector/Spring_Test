package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import model.FileStorageDto;

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
public interface FileStorageMapper {
    
    FileStorageDto getFileStorageById(@Param("fileId") Long fileId);
    
    int addFileStorage(FileStorageDto fileStorageDto);

    void delFileStorageById(@Param("fileId") Long fileId);
}
