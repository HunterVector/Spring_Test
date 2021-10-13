package com.example.demo_comsumer.service;

import org.springframework.http.ResponseEntity;

import com.example.demo_comsumer.param.GoodsOperMssTransferReqDto;
import com.example.demo_comsumer.param.GoodsOperMssTransferResponseDto;

/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate ${date} <br>
 * @since V8.0<br>
 */
public interface TestMSSRequest {
    
    
    //提供给内部调用的发送请求的接口方法
    String sendRequest(Long id);

    GoodsOperMssTransferResponseDto sendMssA2Api(GoodsOperMssTransferReqDto reqDto);

    ResponseEntity<String> sendMssA1Api(GoodsOperMssTransferReqDto req);
}
