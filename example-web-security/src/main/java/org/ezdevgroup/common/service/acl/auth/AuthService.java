package org.ezdevgroup.common.service.acl.auth;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.Auth;
import org.ezdevgroup.common.persist.acl.auth.AuthMapper;
import org.ezdevgroup.common.service.acl.auth.dto.AuthDto;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Resource AuthMapper authMapper;

	public List<Auth> getList() {
		return authMapper.getList(new AuthDto());
	}
	
	@Cacheable(value="BANNER")
	public List<Auth> getListCacheTest() {
		return authMapper.getList(new AuthDto());
	}
	
	public List<Auth> getList(AuthDto authDto) {
		return authMapper.getList(authDto);
	}

	@Cacheable(value="EVENT")
	public List<Auth> getListCacheTest(AuthDto authDto) {
		return authMapper.getList(authDto);
	}

	public Auth getView(String authCd) {
		return authMapper.getView(authCd);
	}

	public int add(AuthDto authDto) {
		return authMapper.add(authDto);
	}

	/**
	 * 권한 수정
	 * @param authDto
	 * @auther ddakker 2014. 9. 1.
	 */
	public void modifys(AuthDto authDto) {
		if (authDto.getAuthCds() != null && authDto.getAuthCds().length > 0) {
			List<AuthDto> authDtoList = new ArrayList<AuthDto>();
			for (int i=0,size=authDto.getAuthCds().length; i<size; i++) {
				AuthDto aDto = new AuthDto();
				aDto.setAuthCd(authDto.getAuthCds()[i]);
				aDto.setAuthNm(authDto.getAuthNms()[i]);
				aDto.setAuthDc(authDto.getAuthDcs()[i]);
				authDtoList.add(aDto);
			}
			if (modify(authDtoList) == 0) throw new RuntimeException("업데이트 실패");
		} else {
			if (authMapper.modify(authDto) == 0) throw new RuntimeException("업데이트 실패");
		}
	}
	
	private int modify(List<AuthDto> authDtoList) {
		int updateCnt = 0;
		for (AuthDto authDto : authDtoList) {
			updateCnt += authMapper.modify(authDto);
		}
		
		if (updateCnt != authDtoList.size()) throw new RuntimeException("수정 갯수 불일치");
		
		return updateCnt;
	}

	/**
	 * 권한 삭제
	 * @param authCds
	 * @auther ddakker 2014. 9. 1.
	 */
	public void remove(String [] authCds) {
		for (String authCd : authCds) {
			ResourceDto resourceDto = new ResourceDto();
			resourceDto.setAuthCd(authCd);
			authMapper.removeAuthResource(resourceDto);
			
			if (authMapper.remove(authCd) != 1) throw new RuntimeException("삭제 실패");
		}
	}
	
	/**
	 * 권한 삭제
	 * @param authCd
	 * @auther ddakker 2014. 9. 1.
	 */
	public int remove(String authCd) {
		return authMapper.remove(authCd);
	}

	/**
	 * 권한에 대한 리소스 등록/삭제
	 * @param authResourceList
	 * @auther ddakker 2014. 9. 1.
	 */
	public void addAuthResource(String authCd, ResourceDto resourceDto, Integer [] checkedIdx) {
		for (int idx : checkedIdx) {
			ResourceDto rDto = new ResourceDto();
			rDto.setAuthCd(authCd);
			rDto.setResourceCd(resourceDto.getResourceCds()[idx]);
			rDto.setAuthResourceYn(resourceDto.getAuthResourceYns()[idx]);
			
			
			if ("Y".equals(rDto.getAuthResourceYn()) && authMapper.getAuthResourceCnt(rDto) == 0) {
				authMapper.addAuthResource(rDto);
			} else if ("N".equals(rDto.getAuthResourceYn())) {
				authMapper.removeAuthResource(rDto);
			}
		}
		
	}
}
