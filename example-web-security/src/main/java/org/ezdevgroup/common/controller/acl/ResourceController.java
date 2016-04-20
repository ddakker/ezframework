package org.ezdevgroup.common.controller.acl;

import org.ezdevgroup.common.domain.entity.Resource;
import org.ezdevgroup.common.service.acl.resource.ResourceService;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.ezdevgroup.ezframework.web.vo.Paging;

@Controller
@RequestMapping("/acl/resource")
public class ResourceController {

	@javax.annotation.Resource ResourceService resourceService;

	/**
	 * 리소스 리스트
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 8. 29.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(@ModelAttribute("resourceDto") ResourceDto resourceDto, Model model) {
		Paging<Resource> paging = resourceService.getList(resourceDto);

		model.addAttribute("paging", paging);
		return "acl/resource/resourceList";
	}

	/**
	 * 리소스 등록
	 * @param resourceDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 8. 29.
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String add(@ModelAttribute("resourceDto") ResourceDto resourceDto, Model model) {
		resourceService.add(resourceDto);
		
		return "redirect:/acl/resource";
	}

	/**
	 * 리소스 수정 N개
	 * @param resourceDto
	 * @param model
	 * @auther ddakker 2014. 8. 29.
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String modifys(@ModelAttribute("resourceDto") ResourceDto resourceDto, Model model) {
		resourceService.modifys(resourceDto);
		return "redirect:/acl/resource?currentPage=" + resourceDto.getCurrentPage();
	}

	/**
	 * 리소스 삭제
	 * @param resourceCds
	 * @return
	 * @auther ddakker 2014. 8. 29.
	 */
	@RequestMapping(value="{resourceCds}", method=RequestMethod.DELETE)
	public String delete(@PathVariable String [] resourceCds) {
		resourceService.remove(resourceCds);
		return "redirect:/acl/resource";
	}

}
