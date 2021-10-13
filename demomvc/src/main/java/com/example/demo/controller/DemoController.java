/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo.controller;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.FileService;
import com.demo.service.UserService;
import com.example.demo.service.UploadService;

import model.FileInfoDto;
import model.FileInfoExample;
import model.UserFileDto;
import model.UserFileExample;
import model.UserInfoDto;
import model.UserInfoExample;
import param.TestDeleteResp;
import param.TestQryResult;
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

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UploadService uploadService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FileService fileService;
    
    @RequestMapping(value = "/testQuery", method = RequestMethod.POST)
    @ResponseBody
    public TestQryResult testQry(HttpSession httpSession) {
        List<FileInfoDto> list = uploadService.buildFileList((UserInfoDto) httpSession.getAttribute("userInfo"));
        
        httpSession.setAttribute("fileList", list);
        TestQryResult result = new TestQryResult();
        result.setResult("Y");
        return result;
        
    }

    @RequestMapping(value = "/upload")
    @Transactional()
    public ModelAndView testFileUpload(MultipartFile testFile, HttpSession httpSession) throws IOException {
        TestUpRsp resp = uploadService.testUploadFile(testFile);
        ModelAndView model = null; 
        if ("UP-000".equals(resp.getResultCode())) {
            UserInfoDto userInfoDto = (UserInfoDto) httpSession.getAttribute("userInfo");
            UserFileDto userFileDto = new UserFileDto();
            userFileDto.setFileId(resp.getFileId());
            userFileDto.setUserId(userInfoDto.getUserId());
            userService.addUserFile(userFileDto);
            model = new ModelAndView("redirect:/demo/success.jsp");
            return model;
        }
        else {
            model = new ModelAndView("demo/error");
            model.addObject("erroMsg", resp.getResultMsg());
            return model;
        }
    }

    @RequestMapping(value = "/testDelete")
    @Transactional()
    public @ResponseBody TestDeleteResp testFileDelete(@RequestParam("fileId") Long fileId, HttpSession httpSession){
        UserInfoDto user = (UserInfoDto) httpSession.getAttribute("userInfo");
        TestDeleteResp resp = uploadService.deleteFile(fileId, user);
        return resp;

    }

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public @ResponseBody String testHelloWorld() {
        return "Hello World!!!";
    }

    @RequestMapping(value = "/login")
    public ModelAndView testLogin(HttpServletRequest request, HttpSession httpSession){
        ModelAndView model = null;

        //判断password 匹配，不匹配，返回error
        String userCode = request.getParameter("userCode");
        String userPass = request.getParameter("userPass");
        
        if (userCode == null || userPass == null) {
            model = new ModelAndView("demo/error");
            model.addObject("erroMsg", "userCode is invalid");
            return model;
        }
        UserInfoDto user = userService.getUserInfoBuCode(userCode);
        if (user == null) {
            model = new ModelAndView("demo/error");
            model.addObject("erroMsg", "userCode is invalid");
            return model;
        }
        
        if (!userPass.equals(user.getUserPass())) {
            model = new ModelAndView("demo/error");
            model.addObject("erroMsg", "password not correct");
            return model;
        }
        
        //匹配设置session 属性；
        httpSession.setAttribute("userInfo", user);
        model = new ModelAndView("redirect:/index.jsp");
        return model;
    }
}
