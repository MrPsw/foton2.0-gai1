package cn.com.foton.JPushBase;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
public class MsgListBanben implements Serializable  {
public String Message  ;
public int Code  ;
public String ServerTime  ;
public  Data2 Data;
   public class Data2 implements Serializable  {
   public int PageSize  ;
   public int PageIndex  ;
   public int Total  ;
   public List<Data> Data;
      public class Data implements Serializable  {
      public String Phone  ;
      public String Message  ;
      public String MessageResult  ;
      public String FromUserId  ;
      public String MessageResultJson  ;
      public String CustomerName  ;
      public String Title  ;
      public String FailReason  ;
      public String CustomerId  ;
      public String BusinessId  ;
      public String CreateTime  ;
      public String ToUserId  ;
      public String CarType  ;
      public int Id  ;
      public String BuyTime  ;
      public int MessageType  ;
      }
   }
}