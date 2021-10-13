/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_test.service;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo_test.controller.TestConsumer;
import com.example.demo_test.param.GoodsOperMssTransferReqDto;
import com.example.demo_test.param.GoodsOperMssTransferResponseDto;
import com.example.demo_test.param.MssTrasnferGoodsItem;

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
@Service("testMSSRequest")
public class TestMSSRequestImpl implements TestMSSRequest{

    @Autowired TestConsumer testConsumer;

    @Autowired
    private RestTemplate restTemplate;

    @Override 
    public ResponseEntity<String> sendMssA1Api(GoodsOperMssTransferReqDto reqDto) {
                String url = "http://10.45.5.170:8097/MSS";
        return restTemplate.postForEntity(url, reqDto.toString(), String.class);
    }

    @Override 
    public GoodsOperMssTransferResponseDto sendMssA2Api(GoodsOperMssTransferReqDto reqDto) {
        reqDto.setRenderType("BYK");
        List<MssTrasnferGoodsItem> itemList = reqDto.getItemList();
        if (null == itemList) {
            itemList = new ArrayList<>();
            MssTrasnferGoodsItem mssTrasnferGoodsItem = new MssTrasnferGoodsItem();
            itemList.add(mssTrasnferGoodsItem);
        }
        Iterator<MssTrasnferGoodsItem> it = itemList.iterator();
        while (it.hasNext()) {
            MssTrasnferGoodsItem item = it.next();
            item.setOrgDemandBillId(reqDto.getBillId());
            item.setSerialNo("MSStest01");
        }
        GoodsOperMssTransferResponseDto respDto = testConsumer.goodsOperMssSendOut(reqDto);
//        String url = "http://ZYY-eureka-provider/ic/ic/goods/GoodsOper/goodsOperMssSendOut";
//        String reqJson = "{\"billId\":6682,\"billNo\":null,\"applyer\":\"Administrator\",\"storeIdIn\":null,\"storeIdOut\":null,\"applyDate\":\"2020-03-17\",\"applyUserId\":\"3044336\",\"applyAreaCode\":null,\"remark\":null,\"applyUserTel\":null,\"renderType\":\"BYKSQ\",\"moveType\":null,\"callType\":null,\"storeIdInSdms\":null,\"storeNameInSdms\":\"Mss_Test\",\"ykType\":\"1\",\"applyDept\":null,\"itemList\":[{\"billRowId\":7036,\"materialCode\":\"Apple6s_Black\",\"applyNum\":4,\"price\":null,\"degree\":null,\"thflag\":null,\"serialNo\":null,\"notes\":null,\"orgDemandBillId\":null,\"orgDemandBillNo\":null,\"orgDemandBillRowId\":null,\"remark1\":null,\"remark2\":null,\"remark3\":null,\"remark4\":null,\"remark5\":null}],\"remark1\":null,\"remark2\":null,\"remark3\":null,\"remark4\":null,\"remark5\":null}";
//        //设置请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//
//        //请求体
//        HttpEntity<String> formEntity = new HttpEntity<String>(reqJson, headers);
//        
//        restTemplate.postForEntity(url, formEntity, String.class);
//        GoodsOperMssTransferResponseDto respDto = new GoodsOperMssTransferResponseDto();
        return respDto;
    }
}
