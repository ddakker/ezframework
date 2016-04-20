package org.ezdevgroup.common.service.acl.auth;

import java.util.List;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.AuthHierarchy;
import org.ezdevgroup.common.persist.acl.auth.AuthHierarchyMapper;
import org.ezdevgroup.common.service.acl.auth.dto.AuthHierarchyDto;
import org.springframework.stereotype.Service;

@Service
public class AuthHierarchyService {

	@Resource AuthHierarchyMapper authHierarchyMapper;

	public List<AuthHierarchy> getList() {
		return authHierarchyMapper.getList();
	}

	public int add(AuthHierarchyDto authHierarchyDto) {
		if (authHierarchyMapper.getAuthHierarchyCnt(authHierarchyDto) != 0) {
			throw new RuntimeException("이미 존재합니다.");
		}
		return authHierarchyMapper.add(authHierarchyDto);
	}

	public void remove(Integer [] seqs) {
		for (Integer seq : seqs) {
			authHierarchyMapper.remove(seq);
		}
	}

}
