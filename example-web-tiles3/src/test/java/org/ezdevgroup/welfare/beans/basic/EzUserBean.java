package org.ezdevgroup.welfare.beans.basic;

import java.io.Serializable;
import java.util.List;


/**
 * 고객사 사용자 Bean
 *
 * @author riverside
 *
 */
public class EzUserBean implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 2185146263818915836L;
	// 사용자아이디
	private String empNum = null;
	
	//사용자아이디 체크
	private String userId = null;
	private String userIdCheck = null;
	// 사용자이름
	private String userNm = null;
	// 주민번호
	private String rrn = null;
	// 사무실전화
	private String officeTel = null;
	// 지점코드
	private String branchCd = null;
	// 자택전화
	private String homeTel = null;
	// 휴대폰
	private String mobile = null;
	// 우편번호
	private String post = null;
	// 기본주소
	private String addr1 = null;
	// 상세주소
	private String addr2 = null;
	// 이메일
	private String email = null;
	// 로그인일시
	private String loginDt = null;
	// 로그인건수
	private String loginCnt = null;
	// 등록일시
	private String regDt = null;
	// 수정일시
	private String modiDt = null;
	// 탈퇴일시
	private String retireCompanyDd = null;
	// 사용여부
	private String useYn = null;
	// 사용자비밀번호
	private String userPwd = null;
	// 부서코드
	private String deptCd = null;
	// 전출일자
	private String moveoutDd = null;
	// 전입일자
	private String moveinDd = null;
	// 휴직일자
	private String restDd = null;
	// 결혼기념일자
	private String wedNotionDd = null;
	// 고객사코드
	private String clientCd = null;
	//휴양콘도고객사코드
	private String weltreeClientCd = null;
	// 팀코드
	private String teamCd = null;
	// 팀명
	private String teamNm = null;
	// 직급코드
	private String gradeCd = null;
	// 직급명
	private String gradeNm = null;
	// 성별
	private String gender = null;
	// 사용자고유식별번호
	private String userKey = null;
	// 메일수신여부
	private String emailRecvYn = null;
	// 문자수신여부
	private String smsRecvYn = null;
	// 자동자유무
	private String carYn = null;
	// 자동차번호
	private String carNum = null;
	// 생일
	private String birthDd = null;
	// 배우자생일
	private String mateBirthDd = null;
	// 사용자상태
	private String userStatus = null;
	// 고용형태
	private String employType = null;
	// 생일(양/음
	private String mateBirthType = null;
	// 배우자 생일(양/음)
	
	//인증 방식(아이핀 or 안심패스)
	private String authString = null;
	
	public String getSmsRecvYn() {
		return smsRecvYn;
	}
	public void setSmsRecvYn(String smsRecvYn) {
		this.smsRecvYn = smsRecvYn;
	}
	// 111228 hglee add
	private String partnerNm = null;
	private String partnerEmail = null;
	private String partnerInfoYn = null;
	private String partnerEmailYn = null;
	
	//가족 관계
	private String fmRelation = null;
	//휴대폰
	private String fmMobile = null;
	
	private String birthType = null;
	// 결혼여부
	private String wedYn = null;
	// 사용자비밀번호질문
	private String userPwdQ = null;
	// 사용자비밀번호답변
	private String userPwdA = null;
	// 테스트계정여부
	private String useType = null;
	// 환급은행명
	private String bankCd = null;
	// 환급계좌번호
	private String bankNum = null;
	// 환급예금주
	private String bankOrder = null;
	// 부양가족수
	private String familyNum = null;
	// 배정타입 1001:일괄재배정,1002:수기입력
	private String assignType = null;
	// 최초로그인여부
	private String firstLoginYn = null;
	// 정보동의여부
	private String infoAttestationYn = null;
	// 급여공제동의여부
	private String deductAttestationYn = null;
	// 비밀번호 변경일시
	private String pwdDt = null;
	// 암호화된 비밀번호
	private String secUserPwd = null;
	// 암호화된 주민번호
	private String secRrn = null;
	// 모두 암호화된 핸드폰 번호
	private String secMoblie = null;
	// 개인별 실링시작월
	private String ceilingStartMonth = null;
	// 개인별 실링적용용여부
	private String ceilingUseYn = null;
	// 파트코드
	private String partCd = null;
	// 파티명
	private String partNm = null;
	// 지점명
	private String branchNm = null;
	// 수정여부
	private String modiYn;
	// 임시비밀번호 발급 여부
	private String pwdImsi = null;
	// 주민번호 앞 8자리
	private String rrnSub = null;

	// 탈퇴사유
	private String retireMemo = null;

	private String cnt = null;
	
	private String useLogin = null;//로그인 사용가능 여부 
	
	
	private String lgLogin = null; //LG 로그인 여부
	
	//CTI 전화주문
	private String loginId	= "";
	private String channel_type	= "";
	private String ipinDi       = "";
	private String ipinCi = null; //IPIN CI값
	
	private String agree2 = "";
	
	// 가족회원 정보 추가 
	private String famKey = "";
	private String famYn = "";
	private String famNm = "";
	private String pointUseYn = "";
	private String famRrn = "";
	
	// 제도오픈(intro) 배너 DISP 여부
	private String banner01;
	private String banner02;
	private String banner03;
	private String banner04;
	private String banner05;
	private String banner06;
	private String banner001Cnt;
	private String banner002Cnt;
	private String banner02Cnt;
	
	
	public String getBanner01() {
		return banner01;
	}
	public void setBanner01(String banner01) {
		this.banner01 = banner01;
	}
	public String getBanner02() {
		return banner02;
	}
	public void setBanner02(String banner02) {
		this.banner02 = banner02;
	}
	public String getBanner03() {
		return banner03;
	}
	public void setBanner03(String banner03) {
		this.banner03 = banner03;
	}
	public String getBanner04() {
		return banner04;
	}
	public void setBanner04(String banner04) {
		this.banner04 = banner04;
	}
	public String getBanner05() {
		return banner05;
	}
	public void setBanner05(String banner05) {
		this.banner05 = banner05;
	}
	public String getBanner06() {
		return banner06;
	}
	public void setBanner06(String banner06) {
		this.banner06 = banner06;
	}
	public String getBanner02Cnt() {
		return banner02Cnt;
	}
	public void setBanner02Cnt(String banner02Cnt) {
		this.banner02Cnt = banner02Cnt;
	}
	public String getAgree2() {
		return agree2;
	}
	public void setAgree2(String agree2) {
		this.agree2 = agree2;
	}
	private String agree3 = "";
	
	public String getIpinCiYn() {
		return ipinCiYn;
	}
	public void setIpinCiYn(String ipinCiYn) {
		this.ipinCiYn = ipinCiYn;
	}
	public String getIpinAuthDt() {
		return ipinAuthDt;
	}
	public void setIpinAuthDt(String ipinAuthDt) {
		this.ipinAuthDt = ipinAuthDt;
	}
	private String ipinCiYn       = "";
	private String ipinAuthDt       = "";
	
	public String getIpinDi() {
		return ipinDi;
	}
	public void setIpinDi(String ipinDi) {
		this.ipinDi = ipinDi;
	}
	public String getIpinCi() {
		return ipinCi;
	}
	public void setIpinCi(String ipinCi) {
		this.ipinCi = ipinCi;
	}
	public String getUserIdCheck() {
		return userIdCheck;
	}
	public void setUserIdCheck(String userIdCheck) {
		this.userIdCheck = userIdCheck;
	}
	public String getUseLogin() {
		return useLogin;
	}
	public void setUseLogin(String useLogin) {
		this.useLogin = useLogin;
	}
	private List<EzUserBean> userList;
	
	
	public List<EzUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<EzUserBean> userList) {
		this.userList = userList;
	}
	public String getModiYn() {
		return modiYn;
	}
	public void setModiYn(String modiYn) {
		this.modiYn = modiYn;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getBranchCd() {
		return branchCd;
	}
	public void setBranchCd(String branchCd) {
		this.branchCd = branchCd;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginDt() {
		return loginDt;
	}
	public void setLoginDt(String loginDt) {
		this.loginDt = loginDt;
	}
	public String getLoginCnt() {
		return loginCnt;
	}
	public void setLoginCnt(String loginCnt) {
		this.loginCnt = loginCnt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModiDt() {
		return modiDt;
	}
	public void setModiDt(String modiDt) {
		this.modiDt = modiDt;
	}
	public String getRetireCompanyDd() {
		return retireCompanyDd;
	}
	public void setRetireCompanyDd(String retireCompanyDd) {
		this.retireCompanyDd = retireCompanyDd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getMoveoutDd() {
		return moveoutDd;
	}
	public void setMoveoutDd(String moveoutDd) {
		this.moveoutDd = moveoutDd;
	}
	public String getMoveinDd() {
		return moveinDd;
	}
	public void setMoveinDd(String moveinDd) {
		this.moveinDd = moveinDd;
	}
	public String getRestDd() {
		return restDd;
	}
	public void setRestDd(String restDd) {
		this.restDd = restDd;
	}
	public String getWedNotionDd() {
		return wedNotionDd;
	}
	public void setWedNotionDd(String wedNotionDd) {
		this.wedNotionDd = wedNotionDd;
	}
	public String getClientCd() {
		return clientCd;
	}
	public void setClientCd(String clientCd) {
		this.clientCd = clientCd;
	}
	public String getWeltreeClientCd() {
		return weltreeClientCd;
	}
	public void setWeltreeClientCd(String weltreeClientCd) {
		this.weltreeClientCd = weltreeClientCd;
	}	
	public String getTeamCd() {
		return teamCd;
	}
	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getEmailRecvYn() {
		return emailRecvYn;
	}
	public void setEmailRecvYn(String emailRecvYn) {
		this.emailRecvYn = emailRecvYn;
	}
	public String getCarYn() {
		return carYn;
	}
	public void setCarYn(String carYn) {
		this.carYn = carYn;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getBirthDd() {
		return birthDd;
	}
	public void setBirthDd(String birthDd) {
		this.birthDd = birthDd;
	}
	public String getMateBirthDd() {
		return mateBirthDd;
	}
	public void setMateBirthDd(String mateBirthDd) {
		this.mateBirthDd = mateBirthDd;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getEmployType() {
		return employType;
	}
	public void setEmployType(String employType) {
		this.employType = employType;
	}
	public String getMateBirthType() {
		return mateBirthType;
	}
	public void setMateBirthType(String mateBirthType) {
		this.mateBirthType = mateBirthType;
	}
	public String getBirthType() {
		return birthType;
	}
	public void setBirthType(String birthType) {
		this.birthType = birthType;
	}
	public String getWedYn() {
		return wedYn;
	}
	public void setWedYn(String wedYn) {
		this.wedYn = wedYn;
	}
	public String getUserPwdQ() {
		return userPwdQ;
	}
	public void setUserPwdQ(String userPwdQ) {
		this.userPwdQ = userPwdQ;
	}
	public String getUserPwdA() {
		return userPwdA;
	}
	public void setUserPwdA(String userPwdA) {
		this.userPwdA = userPwdA;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankOrder() {
		return bankOrder;
	}
	public void setBankOrder(String bankOrder) {
		this.bankOrder = bankOrder;
	}
	public String getFamilyNum() {
		return familyNum;
	}
	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}
	public String getAssignType() {
		return assignType;
	}
	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public String getFirstLoginYn() {
		return firstLoginYn;
	}
	public void setFirstLoginYn(String firstLoginYn) {
		this.firstLoginYn = firstLoginYn;
	}
	public String getInfoAttestationYn() {
		return infoAttestationYn;
	}
	public void setInfoAttestationYn(String infoAttestationYn) {
		this.infoAttestationYn = infoAttestationYn;
	}
	public String getDeductAttestationYn() {
		return deductAttestationYn;
	}
	public void setDeductAttestationYn(String deductAttestationYn) {
		this.deductAttestationYn = deductAttestationYn;
	}
	public String getPwdDt() {
		return pwdDt;
	}
	public void setPwdDt(String pwdDt) {
		this.pwdDt = pwdDt;
	}
	public String getSecUserPwd() {
		return secUserPwd;
	}
	public void setSecUserPwd(String secUserPwd) {
		this.secUserPwd = secUserPwd;
	}
	public String getSecRrn() {
		return secRrn;
	}
	public void setSecRrn(String secRrn) {
		this.secRrn = secRrn;
	}
	public String getSecMoblie() {
		return secMoblie;
	}
	public void setSecMoblie(String secMoblie) {
		this.secMoblie = secMoblie;
	}
	public String getGradeNm() {
		return gradeNm;
	}
	public void setGradeNm(String gradeNm) {
		this.gradeNm = gradeNm;
	}
	public String getBranchNm() {
		return branchNm;
	}
	public void setBranchNm(String branchNm) {
		this.branchNm = branchNm;
	}
	public String getCeilingStartMonth() {
		return ceilingStartMonth;
	}
	public void setCeilingStartMonth(String ceilingStartMonth) {
		this.ceilingStartMonth = ceilingStartMonth;
	}
	public String getCeilingUseYn() {
		return ceilingUseYn;
	}
	public void setCeilingUseYn(String ceilingUseYn) {
		this.ceilingUseYn = ceilingUseYn;
	}
	public String getPartCd() {
		return partCd;
	}
	public void setPartCd(String partCd) {
		this.partCd = partCd;
	}
	public String getPartNm() {
		return partNm;
	}
	public void setPartNm(String partNm) {
		this.partNm = partNm;
	}
	public String getPwdImsi() {
		return pwdImsi;
	}
	public void setPwdImsi(String pwdImsi) {
		this.pwdImsi = pwdImsi;
	}
	public String getRrnSub() {
		return rrnSub;
	}
	public void setRrnSub(String rrnSub) {
		this.rrnSub = rrnSub;
	}
	public String getRetireMemo() {
		return retireMemo;
	}
	public void setRetireMemo(String retireMemo) {
		this.retireMemo = retireMemo;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public void setLgLogin(String lgLogin) {
		this.lgLogin = lgLogin;
	}
	public String getLgLogin() {
		return lgLogin;
	}
	public String getPartnerNm() {
		return partnerNm;
	}
	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}
	public String getPartnerInfoYn() {
		return partnerInfoYn;
	}
	public void setPartnerInfoYn(String partnerInfoYn) {
		this.partnerInfoYn = partnerInfoYn;
	}
	public String getPartnerEmailYn() {
		return partnerEmailYn;
	}
	public void setPartnerEmailYn(String partnerEmailYn) {
		this.partnerEmailYn = partnerEmailYn;
	}
	public String getPartnerEmail() {
		return partnerEmail;
	}
	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFmRelation() {
		return fmRelation;
	}
	
	public void setFmRelation(String fmRelation) {
		this.fmRelation = fmRelation;
	}
	
	public String getFmMobile() {
		return fmMobile;
	}
	public void setFmMobile(String fmMobile) {
		this.fmMobile = fmMobile;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getChannelType() {
		return channel_type;
	}

	public void setChannelType(String channel_type) {
		this.channel_type = channel_type;
	}
	public String getAuthString() {
		return authString;
	}
	public void setAuthString(String authString) {
		this.authString = authString;
	}
	public String getAgree3() {
		return agree3;
	}
	public void setAgree3(String agree3) {
		this.agree3 = agree3;
	}
	public String getPointUseYn() {
		return pointUseYn;
	}
	public String getFamKey() {
		return famKey;
	}
	public void setFamKey(String famKey) {
		this.famKey = famKey;
	}
	public String getFamYn() {
		return famYn;
	}
	public void setFamYn(String famYn) {
		this.famYn = famYn;
	}
	public String getFamNm() {
		return famNm;
	}
	public void setFamNm(String famNm) {
		this.famNm = famNm;
	}
	public void setPointUseYn(String pointUseYn) {
		this.pointUseYn = pointUseYn;
	}
	public String getFamRrn() {
		return famRrn;
	}
	public void setFamRrn(String famRrn) {
		this.famRrn = famRrn;
	}
	public String getBanner001Cnt() {
		return banner001Cnt;
	}
	public void setBanner001Cnt(String banner001Cnt) {
		this.banner001Cnt = banner001Cnt;
	}
	public String getBanner002Cnt() {
		return banner002Cnt;
	}
	public void setBanner002Cnt(String banner002Cnt) {
		this.banner002Cnt = banner002Cnt;
	}
	
}
