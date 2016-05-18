package org.ezdevgroup.ezframework.web.support.util.multipart;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.ezdevgroup.ezframework.web.GlobalProperties;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 파일 업로드
 * 		- filupload tag lib 와 상호 연관
 * @author ddakker 2015. 5. 22.
 */
public class MultipartHelper {
	public static List<FileItem> getUploadFiles(HttpServletRequest request) {
		String tempDir = getUploadTempDir(request);
		
		String fileOriginalNames[] = request.getParameterValues("fileOriginalName");
		String filePhysicalNames[] = request.getParameterValues("filePhysicalName");
		
		List<FileItem> fileItemList = new ArrayList<>();
		
		if (fileOriginalNames != null && fileOriginalNames.length > 0) {
			for (int i=0,size=fileOriginalNames.length; i<size; i++) {
				FileItem fileItem = new FileItem();
				fileItem.setOriginalName(fileOriginalNames[0]);
				fileItem.setPhysicalName(filePhysicalNames[0]);
				fileItem.setFile(new File(tempDir + File.separator + filePhysicalNames[0]));
				
				fileItemList.add(fileItem);
			}
		}
		return fileItemList;
	}
	
	public static String getUploadTempDir(HttpServletRequest request) {
		String tempUploadDir = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean("globalProperties", GlobalProperties.class).getProperty("upload.temp.dir");
		if (StringUtils.isEmpty(tempUploadDir)) {
			throw new RuntimeException("임시 업로드 디렉토리는 globalProperties['upload.temp.dir'] 필수로 설정되어야 합니다.");
		}
		
		GregorianCalendar today = new GregorianCalendar();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		return tempUploadDir + File.separator + year + File.separator + month + File.separator + date;
	}
	
	public static String getUploadDir(String uploadDir, String target) {
		GregorianCalendar today = new GregorianCalendar();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		return uploadDir + File.separator + target + File.separator + year + File.separator + month + File.separator + date;
	}
	
	public static String save(File src, String destFileName, String uploadDir, String target) {
		File destPath = new File(getUploadDir(uploadDir, target));
		File dest = new File(getUploadDir(uploadDir, target) + File.separator + destFileName);
		if (!destPath.isDirectory()) {
			destPath.mkdirs();
		}
		src.renameTo(dest);
		
		return dest.getPath();
	}
}
