package com.example.demo_provider.service;

import java.io.File;
import java.io.IOException;

import param.TestDeleteResp;
import param.TestUpRsp;

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
public interface IDownFileService {
    String findFile(String param1);
    File findFileById(Long fileId);
    String buildFileInfo(File file);
    String findFileNameById(Long fileId);

    void upFileInfo(String fileInfo, String fileName, TestUpRsp upRsp) throws IOException;

    void delFileInfo(Long fileId, TestDeleteResp delRsp);
}
