package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterInformationCheck {
	public boolean isEmpty(String s) throws Exception{
		return ("".equals(s));
	}
	
	
	public boolean isChineseChar(String str){
	       boolean temp = false;
	       Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
	       Matcher m=p.matcher(str);
	       if(m.find()){
	           temp =  true;
	       }
	       return temp;
	   }
	
	
	public boolean isLegalId(String id) throws Exception
	{
		char[] userIDNumbers=id.toCharArray();
  	 	for(int i=0;i<id.length();i++){
  	 		if(userIDNumbers[i]>'9'||userIDNumbers[i]<'0')
  	 		{
  	 			return false;
  	 		}
  	 		}
  	 	return true;
	}
	
	
	public boolean isLegalPassword(String password) throws Exception{
		char[] userPasswordLetters=password.toCharArray();
  	  	boolean hasNumbers=false;
  	  	boolean hasLowercase=false;
  	  	boolean hasUppercase=false;
  	  	boolean hasIllegalLetter=false;
  	  	for(char c:userPasswordLetters){
  	  		if(c>='a'&&c<='z'){
  	  		hasLowercase=true;
  	  		}
  	  		else if(c>='A'&&c<='Z'){
  	  		hasUppercase=true;
  	  		}
  	  		else if(c>='0'&&c<='9'){
  	  		hasNumbers=true;
  	  		}
  	  		else{
  	  		hasIllegalLetter=true;
  	  		}
  	  		
  	  		}
  	  	
  	  	if(hasIllegalLetter)
  	  	{
  	  		return false;
  	  	}
  	  	if (!(hasLowercase&&hasUppercase&&hasNumbers)){
  	  		return false;
  	  	}
  	  	return true;
	}
}
