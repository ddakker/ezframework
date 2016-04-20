package org.ezdevgroup.common.controller.acl;

import javax.annotation.Resource;

import org.ezdevgroup.common.service.acl.auth.AuthService;
import org.ezdevgroup.common.service.acl.user.UserAuthService;
import org.ezdevgroup.common.service.acl.user.dto.UserAuthDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/acl/authUser")
public class AuthUserController {
	@Resource UserAuthService userAuthService;
	@Resource AuthService authService;


	/**
	 * 리스트
	 * @param userAuthDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 15.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(@ModelAttribute("userAuthDto") UserAuthDto userAuthDto, Model model) {

		model.addAttribute("authList", authService.getList());
		model.addAttribute("paging", userAuthService.getList(userAuthDto));

		return "acl/authUser/authUserList";
	}

	/**
	 * 수정
	 * @param userAuthDto
	 * @param model
	 * @return
	 * @auther ddakker 2014. 9. 15.
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String modify(@ModelAttribute("userAuthDto") UserAuthDto userAuthDto, Model model) {
		if (userAuthDto.getUserAuthKeyArr() == null) throw new RuntimeException("수정 내역 정보가 없습니다.");
		
		userAuthService.modify(userAuthDto);
		return "redirect:/acl/authUser";
	}
}
