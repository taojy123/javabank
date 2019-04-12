package pro.bank.utils;

public class StringUtil {
	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else if(Character.isLowerCase(s.charAt(1)))
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	  else//
		  return s;
	}
	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
	  if(Character.isUpperCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
