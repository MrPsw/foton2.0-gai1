package cn.com.foton.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrySHA {


	    /**
	     * TODO(description of this method)
	     * 
	     * @param args
	     * @author丶贰九 2015-4-29 下午5:12:17
	     * @since v1.0
	     */
	    
	    //byte字节转换成16进制的字符串MD5Utils.hexString  
	    public byte[] eccrypt(String info, String shaType) throws NoSuchAlgorithmException {
	        MessageDigest sha = MessageDigest.getInstance(shaType);
	        byte[] srcBytes = info.getBytes();
	        // 使用srcBytes更新摘要
	        sha.update(srcBytes);
	        // 完成哈希计算，得到result
	        byte[] resultBytes = sha.digest();
	        return resultBytes;
	    }

	    public byte[] eccryptSHA1(String info) throws NoSuchAlgorithmException {
	        return eccrypt(info, "SHA1");
	    }

	    public byte[] eccryptSHA256(String info) throws NoSuchAlgorithmException {
	        return eccrypt(info, "SHA-256");
	    }

	    public byte[] eccryptSHA384(String info) throws NoSuchAlgorithmException {
	        return eccrypt(info, "SHA-384");
	    }

	    public byte[] eccryptSHA512(String info) throws NoSuchAlgorithmException {
	        return eccrypt(info, "SHA-512");
	    }

	  
	    
	    public static String hexString(byte[] bytes){  
	        StringBuffer hexValue = new StringBuffer();  
	  
	        for (int i = 0; i < bytes.length; i++) {  
	            int val = ((int) bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	    }
	}

