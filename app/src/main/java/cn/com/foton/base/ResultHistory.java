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
      public  String fcmResult;//��չ
      public  String fcmLeadLevel;//�����ȼ�
      public  String fcmAttentionModel;//������
      public  String fcmFollowupNote;//������Ϣ
      }
   }