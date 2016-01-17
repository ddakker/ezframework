package pe.kr.ddakker.ezframework.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pe.kr.ddakker.ezframework.web.GlobalsProperties;
import pe.kr.ddakker.ezframework.web.support.util.multipart.MultipartHelper;

/**
 * 공통 Controller
 *
 * @author ddakker 2015. 5. 26.
 *
 */
@Controller
public class EzController {
	private Logger log  = LoggerFactory.getLogger(EzController.class);

	@Resource private GlobalsProperties globalsProperties;

	/**
	 * 임시 파일 업로드
	 * @param model
	 * @auther ddakker 2015. 5. 22.
	 */
	@RequestMapping(value="tmp/upload", method = RequestMethod.POST)
	public void uploadForm(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response,  Model model) {
		PrintWriter out = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			out = response.getWriter();
			
			String fileOriginalName = file.getOriginalFilename();
			String filePhysicalName = UUID.randomUUID().toString();
			String tempUploadDir = MultipartHelper.getUploadTempDir(request);
			
			File saveDir 	= new File(tempUploadDir);
			File saveFile 	= new File(tempUploadDir + File.separator + filePhysicalName);
			if (!saveDir.isDirectory()) {
				saveDir.mkdirs();
			}
			
			if (saveFile.exists()) {
				if ("local".equals(globalsProperties.getProperty("server.type"))) {
					saveFile.delete();
				} else {
					throw new Exception("존재하는 파일입니다.");
				}
			}
			
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(saveFile));
			stream.write(bytes);
			stream.close();
			
			resultMap.put("result", "0000");
			resultMap.put("fileOriginalName", fileOriginalName);
			resultMap.put("filePhysicalName", filePhysicalName);
			resultMap.put("msg", "업로드 성공");
		} catch (Exception e) {
			log.warn("{}", e.toString());
			resultMap.put("result", "9999");
			resultMap.put("fileOriginalName", "");
			resultMap.put("filePhysicalName", "");
			resultMap.put("msg", "파일 업로드에 실패하였습니다.");
			
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = "";
			try {
				jsonStr = mapper.writeValueAsString(resultMap);
			} catch (IOException e) {
				log.error("{}", e.toString());
			}
			out.write("<script>parent.tmpFileUploadSetting(" + jsonStr + ");</script>");
			
			if (out != null) out.close();
		}
		
		//FileUtils.saveTmp();
	}
}
