package org.ezdevgroup.sample.controller.sample;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ezdevgroup.ezframework.web.GlobalProperties;
import org.ezdevgroup.ezframework.web.support.util.multipart.FileItem;
import org.ezdevgroup.ezframework.web.support.util.multipart.MultipartHelper;
import org.ezdevgroup.ezframework.web.vo.Paging;
import org.ezdevgroup.sample.domain.EzMap;
import org.ezdevgroup.sample.domain.entity.TestVo;
import org.ezdevgroup.sample.domain.entity.User;
import org.ezdevgroup.sample.service.log.LogService;
import org.ezdevgroup.sample.service.sample.SampleService;
import org.ezdevgroup.sample.service.sample.dto.SampleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/sample")
public class SampleController {
	private static Logger log = LoggerFactory.getLogger(SampleController.class);

	@Value("#{global['jdbc.default.driverClassName']}") private String driverClassName;
	@Resource GlobalProperties globalProperties;
	@Resource MessageSource messageSource;


	@Resource SampleService 	sampleService;
	@Resource LogService		logService;


	/**
	 * 셈플 메인 및 메시지소스 셈플
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) {

		log.debug("msg.test1: {}", messageSource.getMessage("msg.test1", null, Locale.getDefault()));
		log.debug("msg.test2: {}", messageSource.getMessage("msg.test2", new Object[]{"첫번째","두번째"}, Locale.getDefault()));

		log.debug("jdbc.default.driverClassName: {}", globalProperties.getProperty("jdbc.default.driverClassName"));
		log.debug("driverClassName: {}", driverClassName);


		return "sample/home";
	}

	/**
	 * MIME type 별 통신 셈플
	 * 		- html, json, jsonp, xml
	 * @param paramsVo
	 * @param isEx
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/resultCase")
	public String resultCase(@RequestParam(defaultValue="false") boolean isEx, Model model) {
		//System.out.1p1r13i111113n111111111222231tln("msgKeTest: " + msgKeTest);
		//System.out.println("con");
		model.addAttribute("a", 1);
		model.addAttribute("b", 2);

		if( isEx ) throw new RuntimeException("임의로 에러 발생");

		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("key1", "m1");
			resultMap.put("key", "m2");
			model.addAttribute("resultMap", resultMap);

			List<Map<String, Object>> sampleList = new ArrayList<Map<String, Object>>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("seq", 1);
			params.put("name", "ddakker");
			sampleList.add(params);

			params = new HashMap<String, Object>();
			params.put("seq", 2);
			params.put("name", "ddakker2");
			sampleList.add(params);
			model.addAttribute("sampleList", sampleList);
		} catch (Exception e) {
			log.error("e", e);
		}


		return "sample/resultCase";
	}


	@RequestMapping(value = "/springElCase")
	public String springElCase(Model model) {
		return "sample/springElCase";
	}

	@RequestMapping(value = "/listTran")
	public String listTran(@ModelAttribute SampleDto sampleDto, Model model) {
		log.debug("sampleDto: " + sampleDto);
		sampleDto.setEmail("ddakker@gmail.com");
		List<EzMap> samplesJava = sampleService.getSampleListJava(sampleDto);
		List<EzMap> samplesXml = sampleService.getSampleListXml(sampleDto);
		List<TestVo> samplesVo = sampleService.getSampleListVo(sampleDto);
		List<Map<String, Object>> samplesMap = sampleService.getSampleListHashMap(sampleDto);





		log.debug("samplesJava: " + samplesJava);
		log.debug("samplesXml: " + samplesXml);
		log.debug("samplesVo: " + samplesVo);
		log.debug("samplesMap: " + samplesMap);

		model.addAttribute("samplesJava", samplesJava);
		model.addAttribute("samplesXml", samplesXml);

		model.addAttribute("logs", logService.getLogs());

		return "sample/listTran";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute SampleDto sampleDto, Model model) {
		Paging<EzMap> paging = sampleService.getSampleList(sampleDto);

		model.addAttribute("paging", paging);
		return "sample/list";
	}

	@RequestMapping(value = "/list/{seq}", method = RequestMethod.GET)
	public String listView(@PathVariable("seq") Integer seq, Model model) {
		EzMap sample = sampleService.getSample(seq);

		model.addAttribute("sample", sample);
		return "sample/listView";
	}

	/**
	 * 관계 예제
	 * 		- MyBatis ResultMap association, collection 사용금지
	 * 				  ResultType 만 사용합니다.
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/relation", method = RequestMethod.GET)
	public String relation(Model model) {
		// My


		List<EzMap> sampleOneToOne = sampleService.getSampleOneToOne();
		model.addAttribute("sampleOneToOne", sampleOneToOne);

		Map<String, String> params = new HashMap<String, String>();
		params.put("seq", "1");

		EzMap sampleOneToMany = sampleService.getSample(params);
		sampleOneToMany.put("sampleDescList", sampleService.getSampleDesc(sampleOneToMany.getInteger("seq")));


		model.addAttribute("sampleOneToMany", sampleOneToMany);

		return "sample/relation";
	}



	/**
	 * 등록
	 * @param params
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute SampleDto sampleDto, Model model) {
		sampleService.addSample(sampleDto);
		return "redirect:list";
	}

	/**
	 * 서비스 레이어를 통한 커밋 케이스
	 * @param params
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/tranTestServiceCommit", method = RequestMethod.POST)
	public String tranTestServiceCommit(@ModelAttribute SampleDto sampleDto, Model model) {
		sampleService.serviceTranCommitTest(sampleDto);
		return "redirect:listTran";
	}

	/**
	 * 서비스 레이어를 통한 롤백 케이스
	 * @param params
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/tranTestServiceRollback")
	public String tranTestServiceRollback(@ModelAttribute SampleDto sampleDto, Model model) {
		sampleService.serviceTranRollbackTest(sampleDto);
		return "redirect:/list";
	}


	/**
	 * 수정 - commom package 수정 관련 참조
	 * @param params
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public String modify(@ModelAttribute SampleDto sampleDto, Model model) {
		sampleService.modifySample(sampleDto);
		return "redirect:/list";
	}

	/**
	 * 삭제 - commom package 삭제 관련 참조
	 * @param params
	 * @param model
	 * @return
	 * @auther ddakker 2015. 3. 24.
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public String remove(@ModelAttribute SampleDto sampleDto, Model model) {
		sampleService.modifySample(sampleDto);
		return "redirect:/list";
	}

	@RequestMapping(value = "/customTag")
	public String customTag(Model model) {
		return "sample/customTag";
	}

	@RequestMapping(value = "/onlyAdmin")
	public String onlyAdmin(Model model) {
		return "sample/onlyAdmin";
	}

	@RequestMapping(value = "/onlyUser")
	public String onlyUser(Model model) {
		return "sample/onlyUser";
	}

	@RequestMapping(value="/rest")
	public void testParams1(@RequestParam(defaultValue="냠냠") String test, Model model) {
		log.debug("test: {}", test);
	}

	@RequestMapping(value="/testParams2")
	public void testParams2(@RequestParam Map<String, Object> params, Model model) {

		log.debug("params: {}", params);
	}

	@RequestMapping(value="/testParams3")
	public void testParams3(@RequestParam MultiValueMap<String, String> params, Model model) {

		log.debug("params: {}", params);
	}

	@RequestMapping(value="/testParams4")
	public void testParams4(@ModelAttribute("sample") SampleDto sampleDto, Model model) {

		log.debug("params: {}", sampleDto);
	}

	@Resource Validator validator;

	@RequestMapping(value = "bind/vali", method = { RequestMethod.GET, RequestMethod.POST })
	public String method_name(@Valid SampleDto sampleDto, BindingResult result, Model model) {
		log.info("sampleDto: {}", sampleDto);
		log.info("result.hasErrors(): {}", result.hasErrors());
		log.info("result.getErrorCount(): {}", result.getErrorCount());
		if (result.hasErrors()) {
			log.info("-- 에러");
			throw new RuntimeException("유효성 에러");
		} else {
			log.info("-- 성공");
		}
		return "redirect:/";
	}

	@RequestMapping(value="/write/form", method = RequestMethod.GET)
	public String writeForm(@ModelAttribute SampleDto sampleDto, Model model) {
		return "sample/writeForm";
	}

	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@ModelAttribute SampleDto sampleDto, HttpServletRequest request, Model model) {
		String uploadDir = globalProperties.getProperty("upload.dir");

		List<FileItem> fileItemList = MultipartHelper.getUploadFiles(request);
		for (FileItem fileItem : fileItemList) {
			String userId = "ddakker"; //UserDetailsHelper.getAuthenticatedUser().getUserId();
			String originalName		= fileItem.getOriginalName();
			String physicalName		= fileItem.getPhysicalName();
			// 1. File 이동
			String savePhysicalPath = MultipartHelper.save(fileItem.getFile(), userId + "_" + physicalName, uploadDir, "bbs");


			log.debug("originalName: {}", fileItem.getOriginalName());
			log.debug("physicalName: {}", fileItem.getPhysicalName());
			log.debug("savePhysicalPath: {}", savePhysicalPath);


			// 2. DB 저장
			// insert (board_seq, name, email) values (1, params.getName(), params.getEmail())
			// insert (seq, board_seq, originalName, physicalPath, board_file) values (1, 1, originalName, savePhysicalPath)

		}
		return "redirect:/sample/write/form";
	}


	@RequestMapping(value="/edit/form", method = RequestMethod.GET)
	public String editForm(@ModelAttribute("params") HashMap<String, String> params, Model model) {
		List<Map<String, Object>> boardFileList = new ArrayList<>();

		Map<String, Object> boardFileMap = new HashMap<>();
		boardFileMap.put("seq", 34);
		boardFileMap.put("fileName", "업로드된 파일 1.txt");
		boardFileList.add(boardFileMap);

		boardFileMap = new HashMap<>();
		boardFileMap.put("seq", 37);
		boardFileMap.put("fileName", "업로드된 파일 2.png");
		boardFileList.add(boardFileMap);


		model.addAttribute("boardFileList", boardFileList);
		return "sample/editForm";
	}

	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("params") HashMap<String, String> params, HttpServletRequest request, Model model) {
		String uploadDir = globalProperties.getProperty("upload.dir");

		List<FileItem> fileItemList = MultipartHelper.getUploadFiles(request);
		for (FileItem fileItem : fileItemList) {
			String userId = "ddakker"; //UserDetailsHelper.getAuthenticatedUser().getUserId();
			String originalName		= fileItem.getOriginalName();
			String physicalName		= fileItem.getPhysicalName();
			// 1. File 이동
			String savePhysicalPath = MultipartHelper.save(fileItem.getFile(), userId + "_" + physicalName, uploadDir, "bbs");


			log.debug("originalName: {}", fileItem.getOriginalName());
			log.debug("physicalName: {}", fileItem.getPhysicalName());
			log.debug("savePhysicalPath: {}", savePhysicalPath);


			// 2. DB 저장
			// insert board (board_seq, name, email) values (1, params.getName(), params.getEmail())
			// insert board_file (seq, board_seq, originalName, physicalPath, board_file) values (1, 1, originalName, savePhysicalPath)
		}

		// 3. 삭제 파일 목록
		String seqs[] = request.getParameterValues("seq");	// file db table pk - ui:fileForm pkKey 정보
		for (String seq : seqs) {
			log.debug("del file seq: " + seq);

			// 4. 파일 PK 정보를 활용하여 DB 조회
			// select physicalPath from board_file where seq = #{seq}
			String physicalPath = "";
			new File(physicalPath).delete();

			// 4. 파일 PK 정보를 활용하여 DB 삭제
			// delete from board_file where seq = #{seq}
		}


		return "redirect:/sample/edit/form";
	}

	@RequestMapping(value="/session/test")
	public void sessionTest1(@RequestParam Map<String, String> params, HttpSession session, Model model) {

		log.debug("params: {}", params);

		log.info("test1: {}", sampleService.getTest1());
		log.info("test2: {}", sampleService.getTest2());

		log.info("session get userId: " + session.getAttribute("id"));

	}

	@RequestMapping(value="/session")
	public String sessionTest(Model model) {

		return "sample/session";
	}

	@RequestMapping(value="/session/add")
	public String sessionTestAdd(@RequestParam Map<String, String> params, HttpSession session, Model model) {

		log.debug("params: {}", params);

		session.setAttribute(params.get("key"), params.get("value"));

		return "redirect:/sample/session";
	}

	@RequestMapping(value="/session/logout")
	public String sessionTestLogout(@RequestParam Map<String, Object> params, HttpSession session, Model model) {

		log.debug("params: {}", params);

		session.invalidate();

		return "redirect:/sample/session";

	}
}

