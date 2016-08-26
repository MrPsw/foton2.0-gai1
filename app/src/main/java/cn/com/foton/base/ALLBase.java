package cn.com.foton.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.peng on 2016/8/15.
 */
public class ALLBase implements Serializable {

    /**
     * success : true
     * total : 1
     * msg : {"fcmCustomer":[{"createTime":"","fcmAddress":"","fcmAmount":"","fcmBrandCode":"","fcmBusinessId":"414703757692851971","fcmChangeType":"11021016","fcmCityCode":"","fcmCompanyCode":"1100","fcmCreateDate":"2016-08-0513: 42: 49.285","fcmCustomerClassification":"","fcmCustomerDealNum":"","fcmCustomerDealRate":"","fcmCustomerEffectiveNum":"","fcmCustomerEffectiveRate":"","fcmCustomerFailNum":"","fcmCustomerFailRate":"","fcmCustomerGroup":"30001001","fcmCustomerId":"414703757692851970","fcmCustomerLevel":"11061011","fcmCustomerMobile":"18908040563","fcmCustomerName":"∏ƒ≈…≤‚ ‘1","fcmCustomerNote":"","fcmCustomerNum":"","fcmCustomerOverRate":"","fcmCustomerOverTimeNum":"","fcmCustomerOverTimeRate":"","fcmCustomerQQ":"","fcmCustomerRole":"","fcmCustomerSaleManId":"","fcmCustomerSaleManName":"()","fcmCustomerSex":"","fcmCustomerTel":"","fcmCustomerTotal":"","fcmCustomerType":"","fcmCustomerWechat":"","fcmCustomerfailNum":"","fcmCustomerfailRate":"","fcmDealerCode":"","fcmDealerName":"","fcmDefeatCause":"","fcmDefeatId":"","fcmDriveCode":"","fcmFunctionCode":"","fcmHaveBrandCode":"","fcmHaveCar":[],"fcmHaveCarCount":"","fcmHaveDriveCode":"","fcmHaveFunctionCode":"","fcmHaveModelCode":"","fcmHaveZfdjHorsepowerCode":"","fcmId":"","fcmIndustryCode":"","fcmInfoWay":"11151050","fcmIntention":[],"fcmIntentionDate":"","fcmIntentionSeries":"12051002","fcmIsTimeOver":"","fcmJqQuantity":"","fcmLeadStatus":"","fcmLinkman":[],"fcmLinkmanMobile":"","fcmLinkmanName":"","fcmLinkmanPosition":"","fcmModelCode":"","fcmModelSeries":"","fcmOmQuantity":"","fcmPlanDateBegin":"","fcmPlanDateEnd":"","fcmPlanNote":"","fcmProvinceCode":"","fcmRevisitCount":"8","fcmRevisitGroup":"20001001","fcmRevisitId":"914706451024950021","fcmSalesManId":"","fcmSubmitTime":"","fcmTownCode":"","fcmUpUserId":"414703587111600033","fcmUserId":"","fcmVisitCount":"","fcmZfdjHorsepowerCode":"","functionType":"","isFormulate":""}]}
     */

    public String success;
    public String total;
    public MsgBean msg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean implements Serializable {
        /**
         * createTime :
         * fcmAddress :
         * fcmAmount :
         * fcmBrandCode :
         * fcmBusinessId : 414703757692851971
         * fcmChangeType : 11021016
         * fcmCityCode :
         * fcmCompanyCode : 1100
         * fcmCreateDate : 2016-08-0513: 42: 49.285
         * fcmCustomerClassification :
         * fcmCustomerDealNum :
         * fcmCustomerDealRate :
         * fcmCustomerEffectiveNum :
         * fcmCustomerEffectiveRate :
         * fcmCustomerFailNum :
         * fcmCustomerFailRate :
         * fcmCustomerGroup : 30001001
         * fcmCustomerId : 414703757692851970
         * fcmCustomerLevel : 11061011
         * fcmCustomerMobile : 18908040563
         * fcmCustomerName : ∏ƒ≈…≤‚ ‘1
         * fcmCustomerNote :
         * fcmCustomerNum :
         * fcmCustomerOverRate :
         * fcmCustomerOverTimeNum :
         * fcmCustomerOverTimeRate :
         * fcmCustomerQQ :
         * fcmCustomerRole :
         * fcmCustomerSaleManId :
         * fcmCustomerSaleManName : ()
         * fcmCustomerSex :
         * fcmCustomerTel :
         * fcmCustomerTotal :
         * fcmCustomerType :
         * fcmCustomerWechat :
         * fcmCustomerfailNum :
         * fcmCustomerfailRate :
         * fcmDealerCode :
         * fcmDealerName :
         * fcmDefeatCause :
         * fcmDefeatId :
         * fcmDriveCode :
         * fcmFunctionCode :
         * fcmHaveBrandCode :
         * fcmHaveCar : []
         * fcmHaveCarCount :
         * fcmHaveDriveCode :
         * fcmHaveFunctionCode :
         * fcmHaveModelCode :
         * fcmHaveZfdjHorsepowerCode :
         * fcmId :
         * fcmIndustryCode :
         * fcmInfoWay : 11151050
         * fcmIntention : []
         * fcmIntentionDate :
         * fcmIntentionSeries : 12051002
         * fcmIsTimeOver :
         * fcmJqQuantity :
         * fcmLeadStatus :
         * fcmLinkman : []
         * fcmLinkmanMobile :
         * fcmLinkmanName :
         * fcmLinkmanPosition :
         * fcmModelCode :
         * fcmModelSeries :
         * fcmOmQuantity :
         * fcmPlanDateBegin :
         * fcmPlanDateEnd :
         * fcmPlanNote :
         * fcmProvinceCode :
         * fcmRevisitCount : 8
         * fcmRevisitGroup : 20001001
         * fcmRevisitId : 914706451024950021
         * fcmSalesManId :
         * fcmSubmitTime :
         * fcmTownCode :
         * fcmUpUserId : 414703587111600033
         * fcmUserId :
         * fcmVisitCount :
         * fcmZfdjHorsepowerCode :
         * functionType :
         * isFormulate :
         */

        public List<FcmCustomerBean> fcmCustomer;

        public List<FcmCustomerBean> getFcmCustomer() {
            return fcmCustomer;
        }

        public void setFcmCustomer(List<FcmCustomerBean> fcmCustomer) {
            this.fcmCustomer = fcmCustomer;
        }

        public static class FcmCustomerBean implements Serializable{
            public String createTime;
            public String fcmAddress;
            public String fcmAmount;
            public String fcmBrandCode;
            public String fcmBusinessId;
            public String fcmChangeType;
            public String fcmCityCode;
            public String fcmCompanyCode;
            public String fcmCreateDate;
            public String fcmCustomerClassification;
            public String fcmCustomerDealNum;
            public String fcmCustomerDealRate;
            public String fcmCustomerEffectiveNum;
            public String fcmCustomerEffectiveRate;
            public String fcmCustomerFailNum;
            public String fcmCustomerFailRate;
            public String fcmCustomerGroup;
            public String fcmCustomerId;
            public String fcmCustomerLevel;
            public String fcmCustomerMobile;
            public String fcmCustomerName;
            public String fcmCustomerNote;
            public String fcmCustomerNum;
            public String fcmCustomerOverRate;
            public String fcmCustomerOverTimeNum;
            public String fcmCustomerOverTimeRate;
            public String fcmCustomerQQ;
            public String fcmCustomerRole;
            public String fcmCustomerSaleManId;
            public String fcmCustomerSaleManName;
            public String fcmCustomerSex;
            public String fcmCustomerTel;
            public String fcmCustomerTotal;
            public String fcmCustomerType;
            public String fcmCustomerWechat;
            public String fcmCustomerfailNum;
            public String fcmCustomerfailRate;
            public String fcmDealerCode;
            public String fcmDealerName;
            public String fcmDefeatCause;
            public String fcmDefeatId;
            public String fcmDriveCode;
            public String fcmFunctionCode;
            public String fcmHaveBrandCode;
            public String fcmHaveCarCount;
            public String fcmHaveDriveCode;
            public String fcmHaveFunctionCode;
            public String fcmHaveModelCode;
            public String fcmHaveZfdjHorsepowerCode;
            public String fcmId;
            public String fcmIndustryCode;
            public String fcmInfoWay;
            public String fcmIntentionDate;
            public String fcmIntentionSeries;
            public String fcmIsTimeOver;
            public String fcmJqQuantity;
            public String fcmLeadStatus;
            public String fcmLinkmanMobile;
            public String fcmLinkmanName;
            public String fcmLinkmanPosition;
            public String fcmModelCode;
            public String fcmModelSeries;
            public String fcmOmQuantity;
            public String fcmPlanDateBegin;
            public String fcmPlanDateEnd;
            public String fcmPlanNote;
            public String fcmProvinceCode;
            public String fcmRevisitCount;
            public String fcmRevisitGroup;
            public String fcmRevisitId;
            public String fcmSalesManId;
            public String fcmSubmitTime;
            public String fcmTownCode;
            public String fcmUpUserId;
            public String fcmUserId;
            public String fcmVisitCount;
            public String fcmZfdjHorsepowerCode;
            public String functionType;
            public String isFormulate;
            public List<?> fcmHaveCar;
            public List<?> fcmIntention;
            public List<?> fcmLinkman;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getFcmAddress() {
                return fcmAddress;
            }

            public void setFcmAddress(String fcmAddress) {
                this.fcmAddress = fcmAddress;
            }

            public String getFcmAmount() {
                return fcmAmount;
            }

            public void setFcmAmount(String fcmAmount) {
                this.fcmAmount = fcmAmount;
            }

            public String getFcmBrandCode() {
                return fcmBrandCode;
            }

            public void setFcmBrandCode(String fcmBrandCode) {
                this.fcmBrandCode = fcmBrandCode;
            }

            public String getFcmBusinessId() {
                return fcmBusinessId;
            }

            public void setFcmBusinessId(String fcmBusinessId) {
                this.fcmBusinessId = fcmBusinessId;
            }

            public String getFcmChangeType() {
                return fcmChangeType;
            }

            public void setFcmChangeType(String fcmChangeType) {
                this.fcmChangeType = fcmChangeType;
            }

            public String getFcmCityCode() {
                return fcmCityCode;
            }

            public void setFcmCityCode(String fcmCityCode) {
                this.fcmCityCode = fcmCityCode;
            }

            public String getFcmCompanyCode() {
                return fcmCompanyCode;
            }

            public void setFcmCompanyCode(String fcmCompanyCode) {
                this.fcmCompanyCode = fcmCompanyCode;
            }

            public String getFcmCreateDate() {
                return fcmCreateDate;
            }

            public void setFcmCreateDate(String fcmCreateDate) {
                this.fcmCreateDate = fcmCreateDate;
            }

            public String getFcmCustomerClassification() {
                return fcmCustomerClassification;
            }

            public void setFcmCustomerClassification(String fcmCustomerClassification) {
                this.fcmCustomerClassification = fcmCustomerClassification;
            }

            public String getFcmCustomerDealNum() {
                return fcmCustomerDealNum;
            }

            public void setFcmCustomerDealNum(String fcmCustomerDealNum) {
                this.fcmCustomerDealNum = fcmCustomerDealNum;
            }

            public String getFcmCustomerDealRate() {
                return fcmCustomerDealRate;
            }

            public void setFcmCustomerDealRate(String fcmCustomerDealRate) {
                this.fcmCustomerDealRate = fcmCustomerDealRate;
            }

            public String getFcmCustomerEffectiveNum() {
                return fcmCustomerEffectiveNum;
            }

            public void setFcmCustomerEffectiveNum(String fcmCustomerEffectiveNum) {
                this.fcmCustomerEffectiveNum = fcmCustomerEffectiveNum;
            }

            public String getFcmCustomerEffectiveRate() {
                return fcmCustomerEffectiveRate;
            }

            public void setFcmCustomerEffectiveRate(String fcmCustomerEffectiveRate) {
                this.fcmCustomerEffectiveRate = fcmCustomerEffectiveRate;
            }

            public String getFcmCustomerFailNum() {
                return fcmCustomerFailNum;
            }

            public void setFcmCustomerFailNum(String fcmCustomerFailNum) {
                this.fcmCustomerFailNum = fcmCustomerFailNum;
            }

            public String getFcmCustomerFailRate() {
                return fcmCustomerFailRate;
            }

            public void setFcmCustomerFailRate(String fcmCustomerFailRate) {
                this.fcmCustomerFailRate = fcmCustomerFailRate;
            }

            public String getFcmCustomerGroup() {
                return fcmCustomerGroup;
            }

            public void setFcmCustomerGroup(String fcmCustomerGroup) {
                this.fcmCustomerGroup = fcmCustomerGroup;
            }

            public String getFcmCustomerId() {
                return fcmCustomerId;
            }

            public void setFcmCustomerId(String fcmCustomerId) {
                this.fcmCustomerId = fcmCustomerId;
            }

            public String getFcmCustomerLevel() {
                return fcmCustomerLevel;
            }

            public void setFcmCustomerLevel(String fcmCustomerLevel) {
                this.fcmCustomerLevel = fcmCustomerLevel;
            }

            public String getFcmCustomerMobile() {
                return fcmCustomerMobile;
            }

            public void setFcmCustomerMobile(String fcmCustomerMobile) {
                this.fcmCustomerMobile = fcmCustomerMobile;
            }

            public String getFcmCustomerName() {
                return fcmCustomerName;
            }

            public void setFcmCustomerName(String fcmCustomerName) {
                this.fcmCustomerName = fcmCustomerName;
            }

            public String getFcmCustomerNote() {
                return fcmCustomerNote;
            }

            public void setFcmCustomerNote(String fcmCustomerNote) {
                this.fcmCustomerNote = fcmCustomerNote;
            }

            public String getFcmCustomerNum() {
                return fcmCustomerNum;
            }

            public void setFcmCustomerNum(String fcmCustomerNum) {
                this.fcmCustomerNum = fcmCustomerNum;
            }

            public String getFcmCustomerOverRate() {
                return fcmCustomerOverRate;
            }

            public void setFcmCustomerOverRate(String fcmCustomerOverRate) {
                this.fcmCustomerOverRate = fcmCustomerOverRate;
            }

            public String getFcmCustomerOverTimeNum() {
                return fcmCustomerOverTimeNum;
            }

            public void setFcmCustomerOverTimeNum(String fcmCustomerOverTimeNum) {
                this.fcmCustomerOverTimeNum = fcmCustomerOverTimeNum;
            }

            public String getFcmCustomerOverTimeRate() {
                return fcmCustomerOverTimeRate;
            }

            public void setFcmCustomerOverTimeRate(String fcmCustomerOverTimeRate) {
                this.fcmCustomerOverTimeRate = fcmCustomerOverTimeRate;
            }

            public String getFcmCustomerQQ() {
                return fcmCustomerQQ;
            }

            public void setFcmCustomerQQ(String fcmCustomerQQ) {
                this.fcmCustomerQQ = fcmCustomerQQ;
            }

            public String getFcmCustomerRole() {
                return fcmCustomerRole;
            }

            public void setFcmCustomerRole(String fcmCustomerRole) {
                this.fcmCustomerRole = fcmCustomerRole;
            }

            public String getFcmCustomerSaleManId() {
                return fcmCustomerSaleManId;
            }

            public void setFcmCustomerSaleManId(String fcmCustomerSaleManId) {
                this.fcmCustomerSaleManId = fcmCustomerSaleManId;
            }

            public String getFcmCustomerSaleManName() {
                return fcmCustomerSaleManName;
            }

            public void setFcmCustomerSaleManName(String fcmCustomerSaleManName) {
                this.fcmCustomerSaleManName = fcmCustomerSaleManName;
            }

            public String getFcmCustomerSex() {
                return fcmCustomerSex;
            }

            public void setFcmCustomerSex(String fcmCustomerSex) {
                this.fcmCustomerSex = fcmCustomerSex;
            }

            public String getFcmCustomerTel() {
                return fcmCustomerTel;
            }

            public void setFcmCustomerTel(String fcmCustomerTel) {
                this.fcmCustomerTel = fcmCustomerTel;
            }

            public String getFcmCustomerTotal() {
                return fcmCustomerTotal;
            }

            public void setFcmCustomerTotal(String fcmCustomerTotal) {
                this.fcmCustomerTotal = fcmCustomerTotal;
            }

            public String getFcmCustomerType() {
                return fcmCustomerType;
            }

            public void setFcmCustomerType(String fcmCustomerType) {
                this.fcmCustomerType = fcmCustomerType;
            }

            public String getFcmCustomerWechat() {
                return fcmCustomerWechat;
            }

            public void setFcmCustomerWechat(String fcmCustomerWechat) {
                this.fcmCustomerWechat = fcmCustomerWechat;
            }

            public String getFcmCustomerfailNum() {
                return fcmCustomerfailNum;
            }

            public void setFcmCustomerfailNum(String fcmCustomerfailNum) {
                this.fcmCustomerfailNum = fcmCustomerfailNum;
            }

            public String getFcmCustomerfailRate() {
                return fcmCustomerfailRate;
            }

            public void setFcmCustomerfailRate(String fcmCustomerfailRate) {
                this.fcmCustomerfailRate = fcmCustomerfailRate;
            }

            public String getFcmDealerCode() {
                return fcmDealerCode;
            }

            public void setFcmDealerCode(String fcmDealerCode) {
                this.fcmDealerCode = fcmDealerCode;
            }

            public String getFcmDealerName() {
                return fcmDealerName;
            }

            public void setFcmDealerName(String fcmDealerName) {
                this.fcmDealerName = fcmDealerName;
            }

            public String getFcmDefeatCause() {
                return fcmDefeatCause;
            }

            public void setFcmDefeatCause(String fcmDefeatCause) {
                this.fcmDefeatCause = fcmDefeatCause;
            }

            public String getFcmDefeatId() {
                return fcmDefeatId;
            }

            public void setFcmDefeatId(String fcmDefeatId) {
                this.fcmDefeatId = fcmDefeatId;
            }

            public String getFcmDriveCode() {
                return fcmDriveCode;
            }

            public void setFcmDriveCode(String fcmDriveCode) {
                this.fcmDriveCode = fcmDriveCode;
            }

            public String getFcmFunctionCode() {
                return fcmFunctionCode;
            }

            public void setFcmFunctionCode(String fcmFunctionCode) {
                this.fcmFunctionCode = fcmFunctionCode;
            }

            public String getFcmHaveBrandCode() {
                return fcmHaveBrandCode;
            }

            public void setFcmHaveBrandCode(String fcmHaveBrandCode) {
                this.fcmHaveBrandCode = fcmHaveBrandCode;
            }

            public String getFcmHaveCarCount() {
                return fcmHaveCarCount;
            }

            public void setFcmHaveCarCount(String fcmHaveCarCount) {
                this.fcmHaveCarCount = fcmHaveCarCount;
            }

            public String getFcmHaveDriveCode() {
                return fcmHaveDriveCode;
            }

            public void setFcmHaveDriveCode(String fcmHaveDriveCode) {
                this.fcmHaveDriveCode = fcmHaveDriveCode;
            }

            public String getFcmHaveFunctionCode() {
                return fcmHaveFunctionCode;
            }

            public void setFcmHaveFunctionCode(String fcmHaveFunctionCode) {
                this.fcmHaveFunctionCode = fcmHaveFunctionCode;
            }

            public String getFcmHaveModelCode() {
                return fcmHaveModelCode;
            }

            public void setFcmHaveModelCode(String fcmHaveModelCode) {
                this.fcmHaveModelCode = fcmHaveModelCode;
            }

            public String getFcmHaveZfdjHorsepowerCode() {
                return fcmHaveZfdjHorsepowerCode;
            }

            public void setFcmHaveZfdjHorsepowerCode(String fcmHaveZfdjHorsepowerCode) {
                this.fcmHaveZfdjHorsepowerCode = fcmHaveZfdjHorsepowerCode;
            }

            public String getFcmId() {
                return fcmId;
            }

            public void setFcmId(String fcmId) {
                this.fcmId = fcmId;
            }

            public String getFcmIndustryCode() {
                return fcmIndustryCode;
            }

            public void setFcmIndustryCode(String fcmIndustryCode) {
                this.fcmIndustryCode = fcmIndustryCode;
            }

            public String getFcmInfoWay() {
                return fcmInfoWay;
            }

            public void setFcmInfoWay(String fcmInfoWay) {
                this.fcmInfoWay = fcmInfoWay;
            }

            public String getFcmIntentionDate() {
                return fcmIntentionDate;
            }

            public void setFcmIntentionDate(String fcmIntentionDate) {
                this.fcmIntentionDate = fcmIntentionDate;
            }

            public String getFcmIntentionSeries() {
                return fcmIntentionSeries;
            }

            public void setFcmIntentionSeries(String fcmIntentionSeries) {
                this.fcmIntentionSeries = fcmIntentionSeries;
            }

            public String getFcmIsTimeOver() {
                return fcmIsTimeOver;
            }

            public void setFcmIsTimeOver(String fcmIsTimeOver) {
                this.fcmIsTimeOver = fcmIsTimeOver;
            }

            public String getFcmJqQuantity() {
                return fcmJqQuantity;
            }

            public void setFcmJqQuantity(String fcmJqQuantity) {
                this.fcmJqQuantity = fcmJqQuantity;
            }

            public String getFcmLeadStatus() {
                return fcmLeadStatus;
            }

            public void setFcmLeadStatus(String fcmLeadStatus) {
                this.fcmLeadStatus = fcmLeadStatus;
            }

            public String getFcmLinkmanMobile() {
                return fcmLinkmanMobile;
            }

            public void setFcmLinkmanMobile(String fcmLinkmanMobile) {
                this.fcmLinkmanMobile = fcmLinkmanMobile;
            }

            public String getFcmLinkmanName() {
                return fcmLinkmanName;
            }

            public void setFcmLinkmanName(String fcmLinkmanName) {
                this.fcmLinkmanName = fcmLinkmanName;
            }

            public String getFcmLinkmanPosition() {
                return fcmLinkmanPosition;
            }

            public void setFcmLinkmanPosition(String fcmLinkmanPosition) {
                this.fcmLinkmanPosition = fcmLinkmanPosition;
            }

            public String getFcmModelCode() {
                return fcmModelCode;
            }

            public void setFcmModelCode(String fcmModelCode) {
                this.fcmModelCode = fcmModelCode;
            }

            public String getFcmModelSeries() {
                return fcmModelSeries;
            }

            public void setFcmModelSeries(String fcmModelSeries) {
                this.fcmModelSeries = fcmModelSeries;
            }

            public String getFcmOmQuantity() {
                return fcmOmQuantity;
            }

            public void setFcmOmQuantity(String fcmOmQuantity) {
                this.fcmOmQuantity = fcmOmQuantity;
            }

            public String getFcmPlanDateBegin() {
                return fcmPlanDateBegin;
            }

            public void setFcmPlanDateBegin(String fcmPlanDateBegin) {
                this.fcmPlanDateBegin = fcmPlanDateBegin;
            }

            public String getFcmPlanDateEnd() {
                return fcmPlanDateEnd;
            }

            public void setFcmPlanDateEnd(String fcmPlanDateEnd) {
                this.fcmPlanDateEnd = fcmPlanDateEnd;
            }

            public String getFcmPlanNote() {
                return fcmPlanNote;
            }

            public void setFcmPlanNote(String fcmPlanNote) {
                this.fcmPlanNote = fcmPlanNote;
            }

            public String getFcmProvinceCode() {
                return fcmProvinceCode;
            }

            public void setFcmProvinceCode(String fcmProvinceCode) {
                this.fcmProvinceCode = fcmProvinceCode;
            }

            public String getFcmRevisitCount() {
                return fcmRevisitCount;
            }

            public void setFcmRevisitCount(String fcmRevisitCount) {
                this.fcmRevisitCount = fcmRevisitCount;
            }

            public String getFcmRevisitGroup() {
                return fcmRevisitGroup;
            }

            public void setFcmRevisitGroup(String fcmRevisitGroup) {
                this.fcmRevisitGroup = fcmRevisitGroup;
            }

            public String getFcmRevisitId() {
                return fcmRevisitId;
            }

            public void setFcmRevisitId(String fcmRevisitId) {
                this.fcmRevisitId = fcmRevisitId;
            }

            public String getFcmSalesManId() {
                return fcmSalesManId;
            }

            public void setFcmSalesManId(String fcmSalesManId) {
                this.fcmSalesManId = fcmSalesManId;
            }

            public String getFcmSubmitTime() {
                return fcmSubmitTime;
            }

            public void setFcmSubmitTime(String fcmSubmitTime) {
                this.fcmSubmitTime = fcmSubmitTime;
            }

            public String getFcmTownCode() {
                return fcmTownCode;
            }

            public void setFcmTownCode(String fcmTownCode) {
                this.fcmTownCode = fcmTownCode;
            }

            public String getFcmUpUserId() {
                return fcmUpUserId;
            }

            public void setFcmUpUserId(String fcmUpUserId) {
                this.fcmUpUserId = fcmUpUserId;
            }

            public String getFcmUserId() {
                return fcmUserId;
            }

            public void setFcmUserId(String fcmUserId) {
                this.fcmUserId = fcmUserId;
            }

            public String getFcmVisitCount() {
                return fcmVisitCount;
            }

            public void setFcmVisitCount(String fcmVisitCount) {
                this.fcmVisitCount = fcmVisitCount;
            }

            public String getFcmZfdjHorsepowerCode() {
                return fcmZfdjHorsepowerCode;
            }

            public void setFcmZfdjHorsepowerCode(String fcmZfdjHorsepowerCode) {
                this.fcmZfdjHorsepowerCode = fcmZfdjHorsepowerCode;
            }

            public String getFunctionType() {
                return functionType;
            }

            public void setFunctionType(String functionType) {
                this.functionType = functionType;
            }

            public String getIsFormulate() {
                return isFormulate;
            }

            public void setIsFormulate(String isFormulate) {
                this.isFormulate = isFormulate;
            }

            public List<?> getFcmHaveCar() {
                return fcmHaveCar;
            }

            public void setFcmHaveCar(List<?> fcmHaveCar) {
                this.fcmHaveCar = fcmHaveCar;
            }

            public List<?> getFcmIntention() {
                return fcmIntention;
            }

            public void setFcmIntention(List<?> fcmIntention) {
                this.fcmIntention = fcmIntention;
            }

            public List<?> getFcmLinkman() {
                return fcmLinkman;
            }

            public void setFcmLinkman(List<?> fcmLinkman) {
                this.fcmLinkman = fcmLinkman;
            }
        }
    }
}
