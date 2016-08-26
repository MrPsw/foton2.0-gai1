package cn.com.foton.JPushBase;



import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
public class Banben implements Serializable  {
public String UserId  ;
public  Data Data;
   public static class Data implements Serializable  {
   public String  Version  ;
   public int MobileType  ;
   }
}