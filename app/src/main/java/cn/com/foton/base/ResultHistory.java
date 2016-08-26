package cn.com.foton.base;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
   public class ResultHistory implements Serializable  {
   public boolean success  ;
   public List< msg>  msg;
      public  class  msg implements Serializable  {
      public  String fcmPlanId  ;
      public  String fcmPlanType  ;//
      public  String fcmPlanDate;
      public  String fcmPlanIsover;//
      public  String fcmResult;//进展
      public  String fcmLeadLevel;//线索等级
      public  String fcmAttentionModel;//意向车型
      public  String fcmFollowupNote;//跟进信息
      }
   }