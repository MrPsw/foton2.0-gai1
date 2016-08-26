package cn.com.foton.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrySHA {


	    /**
	     * TODO(description of this method)
	     * 
	     * @param args
	     * @authorؼ���� 2015-4-29 ����5:12:17
	     * @since v1.0
	     */
	    
	    //byte�ֽ�ת����16���Ƶ��ַ�MD5Utils.hexString  
	    public byte[] eccrypt(String info, String shaType) throws NoSuchAlgorithmException {
	        MessageDigest sha = MessageDigest.getInstance(shaType);
	        byte[] srcBytes = info.getBytes();
	        // ʹ��srcBytes����ժҪ
	        sha.update(srcBytes);
	        // ��ɹ�ϣ���㣬�õ�result
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

