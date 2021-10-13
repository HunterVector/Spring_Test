package com.example.demo_test.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo_test.param.GoodsOperMssTransferReqDto;
import com.example.demo_test.param.GoodsOperMssTransferResponseDto;
import com.example.demo_test.param.TestReq;
import com.example.demo_test.param.TestResp;

/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate ${date} <br>
 * @since V8.0<br>
 */
//@FeignClient("eureka-provider")
@FeignClient("ZYY-eureka-provider")
public interface TestConsumer {
    
    
    
    //通过Spring cloud 方式 查找eureka 服务器服务的 接口，即对端接口，@FeignClient("eureka-provider") 对端的client 注册名
    // @GetMapping("/hello")  对应的访问URL
    @RequestMapping("/hello") TestResp hello(@RequestBody TestReq testReq);
    
    //直接调用A2接口
    @RequestMapping(value = "ic/ic/goods/GoodsOper/goodsOperMssSendOut", method = RequestMethod.POST)
    GoodsOperMssTransferResponseDto goodsOperMssSendOut(@RequestBody GoodsOperMssTransferReqDto reqDto);
}
