/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo.servlet;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import com.example.demo.config.DownloadConfig;

import param.TestDownReq;
import param.TestDownRsp;

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

@WebServlet(name = "downloadServlet", urlPatterns = "/downloadAttachment")
public class DownloadServlet extends HttpServlet {

    @Autowired 
    private RestTemplate restTemplate;
    
    @Autowired
    private DownloadConfig downloadConfig;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DownloadConfig getDownloadConfig() {
        return downloadConfig;
    }

    public void setDownloadConfig(DownloadConfig downloadConfig) {
        this.downloadConfig = downloadConfig;
    }

    @Override
    public void init() {
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        restTemplate = webApplicationContext.getBean("restTemplate", RestTemplate.class);
        downloadConfig = webApplicationContext.getBean("downloadConfig", DownloadConfig.class);
    }
    
    
//    @Override 
//    protected void doService(HttpServletRequest request, HttpServletResponse response)
//        throws Exception {
//        String url = "http://localhost:8020/download";
//        TestDownReq convertObject = new TestDownReq();
//        convertObject.setParam1("1");
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<TestDownReq> formEntity = new HttpEntity<TestDownReq>(convertObject, headers);
//        TestDownRsp resp =  restTemplate.postForObject(url, formEntity, TestDownRsp.class);
//        String fileUrl = "D:\\logs\\file\\";
//        String fileInfo = resp.getDocInfo();
//        if (null != fileInfo && fileInfo.length() > 0) {
//            String fileName = "testUpload1.txt";
//            fileUrl = fileUrl + fileName;
//            File attachFile = new File(fileUrl);
//            attachFile.createNewFile();
//            FileOutputStream fos = new FileOutputStream(attachFile);
//            fos.write(Base64.decodeBase64(fileInfo));
//            fos.close();
//
//            response.setContentType("APPLICATION/OCTET-STREAM;charset=" + "UTF-8");
//            response.setHeader("Content-Disposition", safeHttpHeader("attachment; filename=\"" + "testDownLoad1.txt" + "\""));
//            response.setContentLength((int) attachFile.length());
//
//            int i = 0;
//            byte[] bt = new byte[8192];
//            ServletOutputStream sos = null;
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(attachFile);
//                sos = response.getOutputStream();
//                while ((i = fis.read(bt)) != -1) {
//                    sos.write(bt, 0, i);
//                }
//            }
//            catch (FileNotFoundException e) {
//                logger.error("Download file error!", e);
//            }
//            finally {
//                try {
//                    fis.close();
//                }
//                catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
        ServletException {
        
        String url = downloadConfig.getDownloadUrl();
        url = url + "/download";
        String fileId = request.getParameter("fileId");
        TestDownReq convertObject = new TestDownReq();
        convertObject.setParam1("1");
        if (fileId != null && fileId.length() > 0) {
            convertObject.setFileId(Long.valueOf(fileId));
        }
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<TestDownReq> formEntity = new HttpEntity<TestDownReq>(convertObject, headers);
        TestDownRsp resp =  restTemplate.postForObject(url, formEntity, TestDownRsp.class);
        if (!"DOWN-000".equals(resp.getResultCode())) {
//            response.sendRedirect("demo/error.jsp");
            request.setAttribute("erroMsg", resp.getResultMsg());
            request.getRequestDispatcher("demo/error.jsp").forward(request, response);
        }
        String fileInfo = resp.getDocInfo();
        if (null != fileInfo && fileInfo.length() > 0) {
            response.setContentType("APPLICATION/OCTET-STREAM;charset=" + "UTF-8");
            response.setHeader("Content-Disposition", safeHttpHeader("attachment; filename=\"" + resp.getFileName() + "\""));
            
            int i = 0;
            OutputStream bos = null;
            try {
                bos = new BufferedOutputStream(response.getOutputStream());
                bos.write(Base64.decodeBase64(fileInfo));
                bos.flush();
                bos.close();
            }
            catch (IOException e) {
//                logger.error("Download file error!", e);
            }
            finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private String safeHttpHeader(String value) {
        String result = "";
        if (value != null) {
            char[] chars = value.toCharArray();
            StringBuilder buffer = new StringBuilder(chars.length);
            for (int i = 0; i < chars.length; i++) {
                switch (chars[i]) {
                    case '\r':
                        buffer.append('%');
                        buffer.append('0');
                        buffer.append('D');
                        break;
                    case '\n':
                        buffer.append('%');
                        buffer.append('0');
                        buffer.append('A');
                        break;
                    default:
                        buffer.append(chars[i]);
                        break;
                }
            }
            result = buffer.toString();
        }
        return result;
    }
}
