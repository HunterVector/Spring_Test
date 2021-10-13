/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.demo.service;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.FileInfoMapper;
import com.demo.mapper.FileStorageMapper;

import model.FileInfoDto;
import model.FileStorageDto;

/**
 * <Description> <br> 
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate  <br>
 * @since V8.0<br>
 * @see  <br>
 */
@Service("fileService")
public class FileServiceImpl implements FileService{
    
    @Autowired
    private FileInfoMapper fileInfoMapper;
    
    @Autowired
    private FileStorageMapper fileStorageMapper;
    
    
    @Override 
    public String getFileNameById(Long fileId) {
        String name = "";
        FileInfoDto fileInfoDto = fileInfoMapper.getFileInfoById(fileId);
        if (fileInfoDto != null) {
            name = fileInfoDto.getFileName();
        }
        return name;
    }
    
    @Override
    public String getFilePathById(Long fileIid) {
        String path = "";
        FileStorageDto fileStorageDto = fileStorageMapper.getFileStorageById(fileIid);
        if (fileStorageDto != null) {
            path = fileStorageDto.getFilePath();
        }
        return path;
    }

    @Override public void addFileInfo(FileInfoDto fileInfoDto) {
        fileInfoMapper.addFileInfo(fileInfoDto);
    }

    @Override public void addFileStorage(FileStorageDto fileStorageDto) {
        fileStorageMapper.addFileStorage(fileStorageDto);
    }

    @Override public void delFileById(Long fileId) {
        fileStorageMapper.delFileStorageById(fileId);
        fileInfoMapper.delFileInfoById(fileId);
    }
}
