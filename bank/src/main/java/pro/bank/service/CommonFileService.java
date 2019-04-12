package pro.bank.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pro.bank.exception.UploadFileException;


/**
 * 文件上传工具类
 * 
 * @author Administrator
 *
 */
@Component
public class CommonFileService {
	private static final Logger log = LoggerFactory.getLogger(CommonFileService.class);
	
	@Value("${image.path}")
	private String imagePath;
	@Value("${image.url}")
	private String imageUrl;
	private static final String SEPARATOR = "/";
	
	public void delFile(String path) throws IOException {
		log.info("删除文件 imagePath:{}, path:{}", imagePath, path);
		File file = new File(imagePath + path);
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
	}


	public String uploadFile(String path, MultipartFile[] mpFile) throws IOException {
		for (MultipartFile multipartFile : mpFile) {
			log.info("上传文件 imagePath:{},path:{},file:{}", imagePath, path, multipartFile.getOriginalFilename());
			String originalFilename = multipartFile.getOriginalFilename();
			int lastIndexOf = originalFilename.lastIndexOf(SEPARATOR);
			if(originalFilename.indexOf(SEPARATOR)>-1){
				originalFilename = originalFilename.substring(lastIndexOf+1, originalFilename.length());
				log.info("文件名称有错误，此处带路径 进行分割 分割后的文件名：{}",originalFilename);
			}
			File file = new File(imagePath + path + SEPARATOR + originalFilename);
			if (file.exists()) {
				FileUtils.forceDelete(file);
			}
			FileOutputStream output = FileUtils.openOutputStream(file);
			IOUtils.copy(multipartFile.getInputStream(), output);
			IOUtils.closeQuietly(output);
		}
		return path;
	}
	
	public String uploadFile(String path,MultipartFile mpFile) throws IOException {
		log.info("上传文件 imagePath:{},path:{},file:{} ", imagePath, path, mpFile.getOriginalFilename());
		//String tomcatWebappsPath = ImageUtils.getTomcatWebappsPath(request);
		path = imageUrl+SEPARATOR+imagePath +SEPARATOR+ path;//+ SEPARATOR + mpFile.getOriginalFilename();
		File file = new File(path);
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		FileOutputStream output = FileUtils.openOutputStream(file);
		IOUtils.copy(mpFile.getInputStream(), output);
		IOUtils.closeQuietly(output);
		return path;
	}

	
	/**
	 * 文件大小检验
	 * @param file
	 */
	public void checkFileSize(MultipartFile file){
		long sizeMb = file.getSize() / 1024 / 1024;//mb
			if(sizeMb>1024)
				throw new UploadFileException("文件 ["+file.getOriginalFilename()+"] 过大，单文件最大不能超过1G");
	}
	/**
	 * 所有文件校验
	 * @param mpFile
	 */
	public void checkAllFileSize(MultipartFile[] mpFile){
		long sizeAll = 0;
		for (int i = 0; i < mpFile.length; i++) {
			MultipartFile multipartFile = mpFile[i];
			long sizeMb = multipartFile.getSize() / 1024 / 1024;//mb
			sizeAll+=sizeMb;
		}
		if(sizeAll > 2048)
			throw new UploadFileException("文件总大小过大，总文件最大不能超过2G");
	}
}
