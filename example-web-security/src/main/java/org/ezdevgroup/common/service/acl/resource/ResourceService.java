package org.ezdevgroup.common.service.acl.resource;


import java.util.ArrayList;
import java.util.List;

import org.ezdevgroup.common.domain.entity.Resource;
import org.ezdevgroup.common.persist.acl.resource.ResourceMapper;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;
import org.springframework.stereotype.Service;

import org.ezdevgroup.ezframework.web.vo.Paging;

@Service
public class ResourceService {
	@javax.annotation.Resource ResourceMapper resourceMapper;
	

	public Paging<Resource> getList(ResourceDto resourceDto) {
		Paging<Resource> paging = new Paging<Resource>();
		paging.setList(resourceMapper.getList(resourceDto));
		paging.setTotalCount(resourceMapper.getListCnt(resourceDto));
		paging.setPaging(resourceDto);
		return paging;
	}

	public int add(ResourceDto resourceDto) {
		return resourceMapper.add(resourceDto);
	}

	public void modifys(ResourceDto resourceDto) {
		if (resourceDto.getResourceCds() != null && resourceDto.getResourceCds().length > 0) {
			List<ResourceDto> resourceDtoList = new ArrayList<ResourceDto>();
			for (int i=0,size=resourceDto.getResourceCds().length; i<size; i++) {
				ResourceDto rDto = new ResourceDto();
				rDto.setResourceCd(resourceDto.getResourceCds()[i]);
				rDto.setResourceNm(resourceDto.getResourceNms()[i]);
				rDto.setResourceDc(resourceDto.getResourceDcs()[i]);
				rDto.setResourceUrl(resourceDto.getResourceUrls()[i]);
				resourceDtoList.add(rDto);
			}
			if (modify(resourceDtoList) == 0) throw new RuntimeException("업데이트 실패");
		} else if (resourceDto.getResourceCd() != null) {
			if (resourceMapper.modify(resourceDto) == 0) throw new RuntimeException("업데이트 실패");
		}
	}
	
	private int modify(List<ResourceDto> resourceDtoList) {
		int updateCnt = 0;
		for (ResourceDto resourceDto : resourceDtoList) {
			updateCnt += resourceMapper.modify(resourceDto);
		}
		
		if (updateCnt != resourceDtoList.size()) throw new RuntimeException("수정 갯수 불일치");
		return updateCnt;
	}

	public int remove(String [] resourceCds) {
		int changeCnt = 0;
		for (String resourceCd : resourceCds) {
			changeCnt += resourceMapper.remove(resourceCd);
		}
		return changeCnt;
	}

}
