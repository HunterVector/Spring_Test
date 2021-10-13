/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo.service;/**
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.demo.service.FileService;
import com.demo.service.UserService;
import com.example.demo.config.DownloadConfig;

import model.FileInfoDto;
import model.FileInfoExample;
import model.UserFileDto;
import model.UserFileExample;
import model.UserInfoDto;
import param.TestDeleteResp;
import param.TestUpReq;
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

@Service("uploadService")
public class UploadService {


    @Autowired
    private DownloadConfig downloadConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;
    
    public TestUpRsp testUploadFile(MultipartFile file) throws IOException {
        String path = downloadConfig.getUploadPath();
        String fileName = "defaultFileName";
        if (file.getOriginalFilename() != null) {
            fileName = file.getOriginalFilename();
        }
        path = path + fileName;
        File fileNew = new File(path);
        if (!fileNew.exists()) {
            try {
                // 创建新文件
                fileNew.createNewFile();
            } catch (IOException e) {
                System.out.println("创建新文件时出现了错误。。。");
                e.printStackTrace();
            }
        }
        file.transferTo(fileNew);
        
        String fileInfo = buildFileInfo(fileNew);
        fileNew.delete();
        return uploadFileToStorage(fileInfo, fileName);
        
        
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
    
    public TestUpRsp uploadFileToStorage(String fileInfo, String fileName) {

        String url = downloadConfig.getDownloadUrl();
        url = url + "/upload";
        TestUpReq convertObject = new TestUpReq();
        convertObject.setFileInfo(fileInfo);
        convertObject.setFileName(fileName);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<TestUpReq> formEntity = new HttpEntity<TestUpReq>(convertObject, headers);
        TestUpRsp resp =  restTemplate.postForObject(url, formEntity, TestUpRsp.class);
        return resp;
    }

    @Transactional()
    public TestDeleteResp deleteFile(Long fileId, UserInfoDto user) {
        String url = downloadConfig.getDownloadUrl();
        url = url + "/delete?fileId={fileId}";
        Map<String, Long> map = new HashMap<>();
        map.put("fileId", fileId);
        TestDeleteResp resp =  restTemplate.getForObject(url, TestDeleteResp.class, map);
        if ("DEL-000".equals(resp.getRespCode())) {
            if (user != null) {
                userService.delUserFileById(user.getUserId(), fileId);
            }
        }
        return resp;
    }

    @Transactional()
    public List<FileInfoDto> buildFileList(UserInfoDto userInfo) {
        List<UserFileDto> list = userService.getUserFileById(userInfo.getUserId());
        List<FileInfoDto> fileList = new ArrayList<FileInfoDto>();
        if (list != null && list.size() > 0) {
            for (UserFileDto userFileDto : list) {
                FileInfoDto fileInfoDto = new FileInfoDto();
                fileInfoDto.setFileId(userFileDto.getFileId());
                fileInfoDto.setFileName(fileService.getFileNameById(userFileDto.getFileId()));
                fileList.add(fileInfoDto);
            }
        }
        return fileList;
    }
}
