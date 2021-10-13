/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_comsumer.controller;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import com.example.demo_comsumer.param.GoodsOperMssTransferReqDto;
import com.example.demo_comsumer.param.GoodsOperMssTransferResponseDto;
import com.example.demo_comsumer.service.TestMSSRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("ic/goods/GoodsOper")
public class TestConsumerController {


    GoodsOperMssTransferReqDto req1;
    
    @Autowired 
    TestMSSRequest testMSSRequest;
    //模拟前台调用
    @GetMapping("/testHello/{id}")
    public String testHello(@PathVariable("id") Long id) {
        String s = testMSSRequest.sendRequest(id);
        if (!"F".equals(s)) {
            return "接口调用返回成功" + "\n" + s;
        }
        return "接口调用失败！！！！！！";
    }
    @Transactional
    @RequestMapping(value = "/Mss/a1", method = RequestMethod.POST)
    public GoodsOperMssTransferResponseDto testMSSApi(GoodsOperMssTransferReqDto req) {
        req1 = req;
//        GoodsOperMssTransferResponseDto dto = testMSSRequest.sendMssA2Api(req);
//        sendMssA2Api(req);
        GoodsOperMssTransferResponseDto goodsOperMssTransferResponseDto = new GoodsOperMssTransferResponseDto();
        goodsOperMssTransferResponseDto.setStatus("0");
        return goodsOperMssTransferResponseDto;
    }

    @RequestMapping(value = "/mockMss/a1", method = RequestMethod.POST)
    public ResponseEntity<String> testMSSA1Api(GoodsOperMssTransferReqDto req) {
        String test = "test";
        return testMSSRequest.sendMssA1Api(req);
    }

    private void sendMssA2Api(GoodsOperMssTransferReqDto req) {
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(10000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GoodsOperMssTransferResponseDto dto = testMSSRequest.sendMssA2Api(req1);
                if ("0".equals(dto.getStatus())) {
                    System.out.println("==================MSSA2  接口   调用成功 ！！！！=============");
                }
                else {
                    System.out.println("==================MSSA2  失败 ！！！！=============");
                }
            }
        });
        t1.start();
    }

    @GetMapping("/testMSS")
    public String testMSS() {
        GoodsOperMssTransferReqDto req = new GoodsOperMssTransferReqDto();
        req.setBillId(123456L);
        testMSSRequest.sendMssA2Api(req);
        return "接口调用失败！！！！！！";
    }
    
    
}
