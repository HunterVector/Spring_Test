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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo_provider.config.Config;
import com.example.demo_provider.param.TestResp;
import com.example.demo_provider.service.DownFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.minidev.json.JSONObject;
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
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class TestProviderControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private DownFileService downFileService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestProviderController()).build();
    }

    @Test
    public void testDownloadProv1() throws Exception {
        TestDownReq testResp = new TestDownReq();
        new TestProviderController().testDownloadProv(testResp);
    }
    
    @Test
    public void testDownloadProv() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        
        TestDownReq test = new TestDownReq();
        test.setParam1("test");
        String requestJson = ow.writeValueAsString(test);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/download")
            // 设置返回值类型为utf-8，否则默认为ISO-8859-1
            .contentType(MediaType.APPLICATION_JSON).content(requestJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
            .andDo(MockMvcResultHandlers.print());
    }
}
