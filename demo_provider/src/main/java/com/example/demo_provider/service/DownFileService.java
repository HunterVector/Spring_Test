/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_provider.service;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.service.FileService;
import com.example.demo_provider.config.SystemParamConfig;

import model.FileInfoDto;
import model.FileInfoExample;
import model.FileStorageDto;
import model.FileStorageExample;
import param.TestDeleteResp;
import param.TestUpRsp;

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
@Service("downFileService")
public class DownFileService implements IDownFileService{
    
    @Autowired
    private SystemParamConfig config;
    
    @Autowired
    private FileService fileService;

    @Override 
    public String findFile(String param1) {
        return null;
    }

    public File findFileById(Long fileId) {
        String filePath = fileService.getFilePathById(fileId);
        if (filePath != null && filePath.length() > 0) {
            return new File(filePath);
        }
        return null;
    }
    
    @Override
    public String findFileNameById(Long fileId) {
        String fileName = fileService.getFileNameById(fileId);
        if (fileName != null && fileName.length() > 0) {
            return fileName;
        }
        String filePath = fileService.getFilePathById(fileId);
        if (filePath != null && filePath.length() > 0) {
            return FilenameUtils.getName(filePath);
        }
        return "DefaultFileName";
    }
    
    
    public String buildFileInfo(File file) {
        String test = "";
        if (file != null) {
            byte[] bt = new byte[8192];
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                fis.read(bt);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fis.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            test = Base64.encodeBase64String(bt);
        }
        return test;
    }
    
    @Transactional
    @Override
    public void upFileInfo(String fileInfo, String fileName, TestUpRsp upRsp) {
        String filePath = config.getUploadPath() + fileName;
        File attachFile = new File(filePath);
        if (attachFile.exists()) {
            upRsp.setResultCode("UP-001");
            upRsp.setResultMsg(fileName + " : File exist!");
        }
        else {
            FileOutputStream fos = null;
            try {
                attachFile.createNewFile();
                fos = new FileOutputStream(attachFile);
                fos.write(Base64.decodeBase64(fileInfo));
                fos.close();

                upRsp.setResultCode("UP-000");
                upRsp.setResultMsg("Success!!!");
                upRsp.setFileName(fileName);
                FileStorageDto fileStorageDto = new FileStorageDto();
                fileStorageDto.setFilePath(filePath);
                fileStorageDto.setFileState("A");
                fileService.addFileStorage(fileStorageDto);
                if (fileStorageDto.getFileId() != null) {
                    FileInfoDto fileInfoDto = new FileInfoDto();
                    fileInfoDto.setFileId(fileStorageDto.getFileId());
                    fileInfoDto.setFileState("A");
                    fileInfoDto.setFileName(fileName);
                    fileService.addFileInfo(fileInfoDto);
                    upRsp.setFileId(fileStorageDto.getFileId());
                }
                
            }
            catch (IOException e) {
                e.printStackTrace();
                upRsp.setResultCode("UP-002");
                upRsp.setResultMsg(e.getMessage());
            }
            finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 
        }
    }
    @Transactional
    @Override 
    public void delFileInfo(Long fileId, TestDeleteResp delRsp) {
        String filePath = fileService.getFilePathById(fileId);
        if (filePath != null && filePath.length() > 0) {
            File attachFile = new File(filePath);
            if (attachFile.exists()) {
                if (attachFile.delete()) {
                    fileService.delFileById(fileId);
                    delRsp.setRespCode("DEL-000");
                    delRsp.setRespMsg("Delete success!!");
                }
                else {
                    delRsp.setRespCode("DEL-002");
                    delRsp.setRespMsg("Delete failed!!");
                }
            }
        }
        else {
            delRsp.setRespCode("DEL-001");
            delRsp.setRespMsg("File not exist!!");
        }
    }
}
