package cn.com.foton.base;

import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
public class Fail implements Serializable  {
public int total  ;
public boolean success  ;
public List<MsgM> msg;
   public class MsgM implements Serializable  {
   public String fcmAddress  ;
   public String fcmCustomerCreateDate  ;
   public String fcmCustomerSex  ;
   public String fcmInfoWay  ;
   public String fcmCustomerId  ;
   public String fcmCustomerSaleManId  ;
   public String fcmCustomerWechat  ;
   public String fcmChangeType  ;
   public String fcmCustomerQQ  ;
   public String fcmBusinessId  ;
   public String fcmProvinceCode  ;
   public String fcmCustomerLevel  ;
   public String fcmModelSeries  ;
   public String fcmCustomerSaleManName  ;
   public String fcmCityCode  ;
   public String fcmCustomerNote  ;
   public String fcmCustomerName  ;
   public String fcmCustomerMobile  ;
   public String fcmTownCode  ;
   public String fcmDefeatId;
   public String fcmDefeatCause;
   }
}