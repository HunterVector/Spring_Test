/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.example.demo_test.param;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.util.List;

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

public class GoodsOperMssTransferReqDto {
    private Long billId;
    private String billNo;
    private String applyer;
    private Long storeIdIn;
    private Long storeIdOut;
    private String applyDate;
    private String applyUserId;
    private String applyAreaCode;
    private String remark;
    private String applyUserTel;
    private String renderType;
    private String moveType;
    private String callType;
    private String storeIdInSdms;
    private String storeNameInSdms;
    private String ykType;
    private String applyDept;
    private List<MssTrasnferGoodsItem> itemList;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Long getStoreIdIn() {
        return storeIdIn;
    }

    public void setStoreIdIn(Long storeIdIn) {
        this.storeIdIn = storeIdIn;
    }

    public Long getStoreIdOut() {
        return storeIdOut;
    }

    public void setStoreIdOut(Long storeIdOut) {
        this.storeIdOut = storeIdOut;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyAreaCode() {
        return applyAreaCode;
    }

    public void setApplyAreaCode(String applyAreaCode) {
        this.applyAreaCode = applyAreaCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplyUserTel() {
        return applyUserTel;
    }

    public void setApplyUserTel(String applyUserTel) {
        this.applyUserTel = applyUserTel;
    }

    public String getRenderType() {
        return renderType;
    }

    public void setRenderType(String renderType) {
        this.renderType = renderType;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getStoreIdInSdms() {
        return storeIdInSdms;
    }

    public void setStoreIdInSdms(String storeIdInSdms) {
        this.storeIdInSdms = storeIdInSdms;
    }

    public String getStoreNameInSdms() {
        return storeNameInSdms;
    }

    public void setStoreNameInSdms(String storeNameInSdms) {
        this.storeNameInSdms = storeNameInSdms;
    }

    public String getYkType() {
        return ykType;
    }

    public void setYkType(String ykType) {
        this.ykType = ykType;
    }

    public String getApplyDept() {
        return applyDept;
    }

    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept;
    }

    public List<MssTrasnferGoodsItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MssTrasnferGoodsItem> itemList) {
        this.itemList = itemList;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }
}
