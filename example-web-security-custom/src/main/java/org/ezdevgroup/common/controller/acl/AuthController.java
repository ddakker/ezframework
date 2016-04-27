package org.ezdevgroup.common.controller.acl;

import org.ezdevgroup.common.domain.entity.Resource;
import org.ezdevgroup.common.service.acl.auth.AuthHierarchyService;
import org.ezdevgroup.common.service.acl.auth.AuthService;
import org.ezdevgroup.common.service.acl.auth.dto.AuthDto;
import org.ezdevgroup.common.service.acl.auth.dto.AuthHierarchyDto;
import org.ezdevgroup.common.service.acl.resource.ResourceService;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.ezdevgroup.ezframework.web.vo.Paging;

@Controller
@RequestMapping("/acl/auth")
public class AuthController {

	@javax.annotation.Resource  AuthService authService;
	@javax.annotation.Resource  ResourceService resourceService;
	@javax.annotation.Resource  AuthHierarchyService authHierarchyService;

	/**
	 * 권한 리스트
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 1.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(@ModelAttribute("auth") AuthDto authDto, Model model) {
		model.addAttribute("list", authService.getList(authDto));
		return "acl/auth/authList";
	}

	/**
	 * 권한 등록
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 1.
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String add(@ModelAttribute("auth") AuthDto authDto, Model model) {
		authService.add(authDto);
		return "redirect:/acl/auth";
	}

	/**
	 * 권한 수정 N개
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 1.
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String modify(@ModelAttribute("auth") AuthDto authDto, Model model) {
		authService.modifys(authDto);
		return "redirect:/acl/auth?currentPage=" + authDto.getCurrentPage();
	}

	/**
	 * 권한 삭제
	 * @param AuthCds
	 * @return
	 * @auther ddakker 2014. 9. 1.
	 */
	@RequestMapping(value="{authCds}", method=RequestMethod.DELETE)
	public String delete(@PathVariable String [] authCds) {
		authService.remove(authCds);
		return "redirect:/acl/auth";
	}

	/**
	 * 권한에 대한 리소스정보 조회
	 * @param authCd
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 2.
	 */
	@RequestMapping(value="{authCd}/resource", method=RequestMethod.GET)
	public String authResource(@PathVariable String authCd, @ModelAttribute ResourceDto resourceDto, Model model) {
		Paging<Resource> paging = resourceService.getList(resourceDto);

		model.addAttribute("view", authService.getView(authCd));
		model.addAttribute("paging", paging);
		return "acl/auth/authResource";
	}

	/**
	 * 권한에 대한 리소스정보 수정
	 * @param authCd
	 * @param checked
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 2.
	 */
	@RequestMapping(value="{authCd}/resource", method=RequestMethod.PUT)
	public String authResourceModify(@PathVariable String authCd, @RequestParam("checkedIdx") Integer [] checkedIdx,  @ModelAttribute("resource") ResourceDto resourceDto, Model model) {
		authService.addAuthResource(authCd, resourceDto, checkedIdx);
		return "redirect:/acl/auth/" + authCd + "/resource?currentPage=" + resourceDto.getCurrentPage();
	}

	/**
	 * 권한 계층리스트를 불러온다.
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 2.
	 */
	@RequestMapping(value="/hierarchy", method=RequestMethod.GET)
	public String authHierarchyList(@ModelAttribute("authHierarchy") AuthHierarchyDto authHierarchyDto, Model model) {
		model.addAttribute("authList", authService.getList(new AuthDto()));
		model.addAttribute("list", authHierarchyService.getList());
		return "acl/auth/authHierarchy";
	}

	/**
	 * 권한 계층정보 등록
	 * @param authHierarchyDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 2.
	 */
	@RequestMapping(value="/hierarchy", method=RequestMethod.POST)
	public String addAuthHierarchy(@ModelAttribute("authHierarchy") AuthHierarchyDto authHierarchyDto, Model model) {
		authHierarchyService.add(authHierarchyDto);
		return "redirect:/acl/auth/hierarchy";
	}

	@RequestMapping(value="/hierarchy/modify", method=RequestMethod.PUT)
	public String modiryAuthHierarchy(@ModelAttribute("authHierarchy") AuthHierarchyDto authHierarchyDto, Model model) {
		authHierarchyService.add(authHierarchyDto);
		return "redirect:/acl/auth/hierarchy";
	}

	@RequestMapping(value="/hierarchy/{seqs}", method=RequestMethod.DELETE)
	public String removeAuthHierarchy(@PathVariable("seqs") Integer [] seqs, @ModelAttribute("authHierarchy") AuthHierarchyDto authHierarchyDto, Model model) {
		authHierarchyService.remove(seqs);
		return "redirect:/acl/auth/hierarchy";
	}


}