/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_test.controller;/**
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_test.param.GoodsOperMssTransferReqDto;
import com.example.demo_test.param.GoodsOperMssTransferResponseDto;
import com.example.demo_test.service.TestMSSRequest;

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
@RequestMapping("mock/MSS")
public class TestConsumerController {
    
    @Autowired TestMSSRequest testMSSRequest;
    //模拟前台调用
    @RequestMapping(value = "/test/a1", method = RequestMethod.POST)
    public ResponseEntity<String> testMSSApi(GoodsOperMssTransferReqDto req) {
        return testMSSRequest.sendMssA1Api(req);
    }

    @RequestMapping(value = "/test/a2", method = RequestMethod.POST)
    public GoodsOperMssTransferResponseDto testMSS(GoodsOperMssTransferReqDto req) {
        GoodsOperMssTransferResponseDto respDto = testMSSRequest.sendMssA2Api(req);
        return respDto;
    }
}
