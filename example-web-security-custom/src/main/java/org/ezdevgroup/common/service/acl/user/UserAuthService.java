package org.ezdevgroup.common.service.acl.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.UserAuth;
import org.ezdevgroup.common.persist.acl.user.UserAuthMapper;
import org.ezdevgroup.common.service.acl.user.dto.UserAuthDto;
import org.springframework.stereotype.Service;

import org.ezdevgroup.ezframework.web.vo.Paging;

@Service
public class UserAuthService {
	@Resource
	private UserAuthMapper userAuthMapper;



	/**
	 * 유저 리스트 및 검색
	 * @param userAuthDto
	 * @return
	 * @author JerryKim 2014. 7. 21.
	 */
	public Paging<UserAuth> getList(UserAuthDto userAuthDto) {
		Paging<UserAuth> paging = new Paging<UserAuth>();
		paging.setList(userAuthMapper.getList(userAuthDto));
		paging.setTotalCount(userAuthMapper.getListCnt(userAuthDto));

		paging.setPaging(userAuthDto);
		return paging;
	}

	/**
	 * 유저 권한 수정
	 * @param userAuthDto
	 * @return
	 * @author JerryKim 2014. 7. 21.
	 */
	public int modify(UserAuthDto userAuthDto) {
		List<UserAuthDto> userAuthDtoList = new ArrayList<UserAuthDto>();
		if (userAuthDto.getUserAuthKeyArr() != null && userAuthDto.getUserAuthKeyArr().length > 0) {
			for (int i=0,size=userAuthDto.getUserAuthKeyArr().length; i<size; i++) {
				UserAuthDto uaDto = new UserAuthDto();
				uaDto.setUserAuthKey(userAuthDto.getUserAuthKeyArr()[i]);
				uaDto.setAuthCd(userAuthDto.getAuthCdArr()[i]);
				userAuthDtoList.add(uaDto);
			}
		} else {
			userAuthDtoList.add(userAuthDto);
		}

		return modiryUserAuth(userAuthDtoList);
	}

	private int modiryUserAuth(List<UserAuthDto> userAuthDtoList) {
		int changeCnt = 0;
		for (UserAuthDto userAuthDto : userAuthDtoList) {
			if (userAuthMapper.getUserAuthCnt(userAuthDto.getUserAuthKey()) == 0)
				changeCnt += userAuthMapper.insertUserAuth(userAuthDto);
			else
				changeCnt += userAuthMapper.modiryUserAuth(userAuthDto);
		}

		return changeCnt;
	}

}
