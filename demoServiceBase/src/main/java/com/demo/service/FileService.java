package com.demo.service;

import model.FileInfoDto;
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
public interface FileService {
    String getFileNameById(Long fileId);

    String getFilePathById(Long fileIid);
    
    void addFileInfo(FileInfoDto fileInfoDto);

    void addFileStorage(FileStorageDto fileStorageDto);
    
    void delFileById(Long fileId);
}
