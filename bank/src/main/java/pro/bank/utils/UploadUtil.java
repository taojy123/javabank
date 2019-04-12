package pro.bank.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class UploadUtil {
    public static final List<String> ALLOW_TYPES = Arrays.asList(
            "image/jpg","image/jpeg","image/png","image/gif"
    );
    
    /**
     * 重命名文件名
     * @param fileName
     * @return
     */
    public static String rename(String fileName){
        int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime()+""+ new Random().nextInt(99999999) +str;
    }
    /**
     * 限制上传文件名称
     * @param postfix
     * @return
     */
    public static boolean allowUpload(String postfix){
        return ALLOW_TYPES.contains(postfix);
    }
    public synchronized static String getFileName(String fileName){
    	String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		return System.currentTimeMillis()+"."+prefix;
	}
    
    public static void main(String[] args) {
		String a = "csdfdsf.jbp.jpg";
		String fileName = getFileName(a);
		System.out.println(fileName);
	}
    
    
  
}