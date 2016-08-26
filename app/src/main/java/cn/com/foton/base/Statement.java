package cn.com.foton.base;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
public class Statement implements Serializable  {
public boolean success  ;
public List<Msg> msg;
   public class Msg implements Serializable  {
	   public String fcmCustomerNum  ;
   public String fcmCompanyCode  ;
   public String fcmDealerCode  ;
   public String fcmCustomerEffectiveNum  ;
   public String fcmCustomerFailNum  ;
   public String fcmCustomerFailRate  ;
   public String fcmCustomerDealNum  ;
   public String fcmSalesManId  ;
   public String fcmCustomerDealRate  ;
   public String fcmCustomerEffectiveRate  ;
   public String fcmCustomerOverRate  ;
   public String fcmCustomerOverTimeNum  ;
   }
}