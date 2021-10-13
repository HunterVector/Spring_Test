/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_provider.controller;/**
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


import com.example.demo_provider.param.TestReq;
import com.example.demo_provider.param.TestResp;
import com.example.demo_provider.service.DownFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.FileInfoExample;
import model.FileStorageExample;
import param.TestDeleteResp;
import param.TestDownReq;
import param.TestDownRsp;
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
@RestController
public class TestProviderController {
    
    @Autowired
    private DownFileService downFileService;
    
    //对端接口URL所对应的方法，这里模拟传一个参数id 过来，对端处理之后返回对应的结果
    @RequestMapping("/hello")
    public TestResp testProvideMethod(@RequestBody TestReq req) {
        // service调⽤
        String msg = "error!";
        TestResp testResp = new TestResp();
        if (null != req && null != req.getId()) {
            testResp.setId(req.getId());
            if (req.getId() > 10) {
                msg = "Best Result: " + "{"+ req.getMsg() + ":" + req.getParam() + "}";
            }
            else {
                msg = "normal Result" + "{"+ req.getMsg() + ":" + req.getParam() + "}";;
            }
            testResp.setMsg(msg);
        }
        testResp.setMsg(msg);
        return testResp;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public @ResponseBody TestDownRsp testDownloadProv(@RequestBody TestDownReq req) {
        // service调⽤
        TestDownRsp testResp = new TestDownRsp();
        String fileInfo = downFileService.buildFileInfo(downFileService.findFileById(req.getFileId()));
        if (fileInfo != null && fileInfo.length() > 0) {
            testResp.setDocInfo(fileInfo);
            testResp.setFileName(downFileService.findFileNameById(req.getFileId()));
            testResp.setResultCode("DOWN-000");
            testResp.setResultMsg("success");
        }
        else {
            testResp.setResultCode("DOWN-001");
            testResp.setResultMsg("can not find file:" + FileInfoExample.getInstance().getFileNameById(req.getFileId()));
        }
        return testResp;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody TestUpRsp testUploadProv(@RequestBody TestUpReq req) {
        // service调⽤
        TestUpRsp upRsp = new TestUpRsp();
        downFileService.upFileInfo(req.getFileInfo(), req.getFileName(), upRsp);
        return upRsp;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody TestDeleteResp testDeleteProv(@RequestParam("fileId") Long fileId) {
        // service调⽤
        TestDeleteResp delRsp = new TestDeleteResp();
        downFileService.delFileInfo(fileId, delRsp);
        return delRsp;
    }
    

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public @ResponseBody String testHelloWorld() {
        return "Hello World!!!";
    }

    @RequestMapping(value = "/helloWorld1", method = RequestMethod.GET)
    public @ResponseBody String testHelloWorld1() {
        return "Hello World1111111";
    }
}
