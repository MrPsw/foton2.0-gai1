package cn.com.foton.base;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
   public class Xiaoshoubase implements Serializable  {
   public boolean success  ;
   public List<Msg> msg;
      public class Msg implements Serializable  {
      public String fcmPositionId  ;
      public String fcmUserName  ;
      public String fcmUserId  ;
      public String fcmRealName;
      }
   }
