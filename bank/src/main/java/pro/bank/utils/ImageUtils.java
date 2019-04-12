package pro.bank.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class ImageUtils {
	 public static String writeFileToService(byte[] bt, String filePath,String fileName) {  
	        BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;  
	        File file = null;  
	        try {  
	            File dir = new File(filePath);
	            if(!dir.exists()){//判断文件目录是否存在  
	                dir.mkdirs();  
	            }  
	            file = new File(filePath+"/"+fileName);  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            fos.write(bt);  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       } finally {  
	           if (bos != null) {  
	                try {  
	                    bos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	            if (fos != null) {  
	                try {  
	                    fos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	        }  
	        return filePath+"/"+fileName;
	 }
	 
	 
	/*  public static String getTomcatWebappsPath(HttpServletRequest request)
	    {
	        String tomcatRoot = request.getSession().getServletContext().getRealPath("/");
	        String foo[] = tomcatRoot.split("/");
	        StringBuilder tomcatWebAppsBuilder = new StringBuilder();
	        int i = 0;
	        String arr$[] = foo;
	        int len$ = arr$.length;
	        for(int i$ = 0; i$ < len$; i$++)
	        {
	            String paths = arr$[i$];
	            if(++i != foo.length)
	            {
	                tomcatWebAppsBuilder.append(paths);
	                tomcatWebAppsBuilder.append("/");
	            }
	        }

	        String tomcatWebApps = tomcatWebAppsBuilder.toString();
	        return tomcatWebApps;
	    }
	    
	    
*/
	 
	 public static String getTomcatWebappsPath(HttpServletRequest request)
	    {
		  
	        String tomcatRoot = request.getSession().getServletContext().getRealPath("/");
	     
	        String foo[] = tomcatRoot.split("\\\\");
	        StringBuilder tomcatWebAppsBuilder = new StringBuilder();
	        int i = 0;
	        String arr$[] = foo;
	        int len$ = arr$.length;
	        for(int i$ = 0; i$ < len$; i$++)
	        {
	            String paths = arr$[i$];
	            if(++i != foo.length)
	            {
	                tomcatWebAppsBuilder.append(paths);
	                tomcatWebAppsBuilder.append("\\");
	            }
	        }

	        String tomcatWebApps = tomcatWebAppsBuilder.toString();
	        return tomcatWebApps;
	    }


}
