package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptSHA256 {
	public static String SHA256(String str){
	  String password = "";
	  try{
	   MessageDigest sh = MessageDigest.getInstance("SHA-256");
	   sh.update(str.getBytes());
	   byte byteData[] = sh.digest();
	   StringBuffer sb = new StringBuffer();
	   for(int i = 0 ; i < byteData.length ; i++){
	    sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	   }
	   password = sb.toString().substring(10,30);
	   
	  }catch(NoSuchAlgorithmException e){
	   e.printStackTrace();
	   password = null;
	  }
	  return password;
	}
}
