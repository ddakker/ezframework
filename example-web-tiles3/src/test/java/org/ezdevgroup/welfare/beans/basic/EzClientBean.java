package org.ezdevgroup.welfare.beans.basic;

import java.io.Serializable;

/**
 * EZ_CLIENT 테이블 과 매핑
 *
 * 고객사정보
 * @author 김정은
 * @version 0.1
 *
 */
public class EzClientBean implements Serializable {
	private static final long	serialVersionUID	= -1643404273561985114L;

	private String clientCd            = null;	/* 고객사 코드 */
	private String clientNm            = null;	/* 고객사 명 */
	private String execNm              = null;	/* 대표자 명 */
	private String bsnsNum             = null;	/* 사업자 번호 */
	private String bsnsKind            = null;	/* 사업자 종류 (코드로 관리됨) */
	private String bcond               = null;	/* 업태 */
	private String bitem               = null;	/* 종목 */
	private String post                = null;	/* 우편번호 */
	private String addr1               = null;	/* 기본주소 */
	private String addr2               = null;	/* 상세주소 */
	private String faxNum              = null;	/* 팩스 번호 */
	private String telNum              = null;	/* 전화 번호 */
	private String pointBringYn        = null;	/* 이월 여부 */
	private String pointBringRatio     = null;	/* 이월 비율 */
	private String calcDd              = null;	/* 정산 일자 */
	private String loginType           = null;	/* 로그인방식(사번 S/주민번호 J) */
	private long basePoint;						/* 기본 포인트 */
	private String designation         = null;	/* 직원호칭 */
	private String centerNm            = null;	/* 센터명 */
	private String offUseYn            = null;	/* 오프라인 사용여부 */
	private String etcCardUseYn        = null;	/* 기타카드 사용여부 */
	private String ceilingType         = null;	/* 실링 적용 유형 (A;전체만, B:메뉴만, C:혼합, X:없음) */
	private String ceilingPoint        = null;	/* 실링 포인트 */
	private String serviceStartDd      = null;	/* 서비스 시작일자 */
	private String serviceEndDd        = null;	/* 서비스 종료일자 */
	private String openDd              = null;	/* 오픈 일자 */
	private String modiDt              = null;	/* 수정 일시 */
	private String useYn               = null;	/* 사용 여부 */
	private String regDt               = null;	/* 등록 일시 */
	private String callTelType         = null;	/* 콜센터 이미지표시 유형 (A-Z) */
	private String ceilingUnit         = null;	/* 실링단위 (10:원, 20:포인트, 30:비율) */
	private String termType            = null;	/* 실링기간유형 (10:일, 20:월, 30:분기, 40:반기) */
	private String menuCeilingYn       = null;	/* 메뉴실링 포함여부 */
	private String promoGu             = null;  /* ?? */
	private String manual			   = null;	/* 메뉴얼			*/
	private String mapUrl			   = null;	/* 지도URL			*/
	private String prvCd			   = null;
	private String prvNm			   = null;
	private String loginImg			   = null;	/* 로그인이미지			*/
	private String loginIcon		   = null;	/* 로그인격언이미지			*/
	private String clientType		   = null;  /* 고객사 타입 */ 

	private String clientCeilingUseYn  = null;  /* 실링 적용 고객사인지 여부 */

	private String serviceCloseYn 	   = null;  /* 제도마감 노출 표시여부 */
	private String offCloseYn  	       = null;  /* 오프라인카드사용 마감 노출 표시여부 */
	private String offReqCloseYn       = null;  /* 오프라인카드사용 신청 마감 노출 표시여부 */
	private String serviceNoticeYn     = null;  /* 제도마감 공지팝업 노출 표시여부 */
	private String closeDate		   = null;  /* 제도마감일 */
	private String lastDate			   = null;  /* 오프라인카드 사용마감일 */
	private String loadDate			   = null;  /* 오프라인카드 사용신청마감(불러오기)일 */
	private String specialPointNm	   = null;  /* 특별포인트 고객사별 명칭 */
	private String benefitPointNm	   = null;  /* 기존복지포인트 고객사별 명칭 */
	private String specialPointNmShort = null;  /* 특별포인트 고객사별 약칭 */
	private String benefitPointNmShort = null;  /* 기존복지포인트 고객사별 약칭 */
	private String receiptAutoYn	   = null;  /* 현금영수증 자동팝업여부 */
	private String receiptPointYn	   = null;  /* 현금영수증 포인트 발행 허용 여부 */
	private String pgType       	   = null;  /* 고객사 PG사 유형 I:이니시스,L:LGU+ */
	private String pageType 		   = null; 	/* M : ez1 B 작년형 D  올해형 */
	private String loginKind		   = null; 	/* 로그인 방식(사번:S, 주민번호:J, I:ID )*/
	private String clientGrp		   = null;  /* 고객사 그룹*/
	//2012.08.06 이홍복 웰트리용 고객사코드 추가
	private String weltreeClientCd  = null;  /* 웰트리용 고객사코드 */ 
	
	private String onlineCardCd  = null;  /* 온라인 결제 카드코드 */
	
	private String donateLimitAmt = null; /* 더가족 기부고객사 결제수단 */
	
	/**
	 * 가족회원 추가 서비스여부, 인트로 서비스 여부
	 */
	private String famSvcYn = null;
	private String introSvcYn = null;
	private String famUsePointYn = null;

	
	
	/** ######################## 모바일 복지관 ######################## **/
	//2012.11.07 전민형 모바일 복지관 사용여부 추가
	private String mobileappYn = null;
	
	public String getMobileappYn() {
		return mobileappYn;
	}
	public void setMobileappYn(String mobileappYn) {
		this.mobileappYn = mobileappYn;
	}
	/** ######################## 모바일 복지관 ######################## **/
	
	
	
	public String getLoginKind() {
		return loginKind;
	}
	public void setLoginKind(String loginKind) {
		this.loginKind = loginKind;
	}
	public String getPageType() {
	return pageType;
	}
	public void setPageType(String pageType) {
	this.pageType = pageType;
	}
	public String getReceiptAutoYn() {
		return receiptAutoYn;
	}
	public void setReceiptAutoYn(String receiptAutoYn) {
		this.receiptAutoYn = receiptAutoYn;
	}
	public String getSpecialPointNmShort() {
		return specialPointNmShort;
	}
	public void setSpecialPointNmShort(String specialPointNmShort) {
		this.specialPointNmShort = specialPointNmShort;
	}
	public String getBenefitPointNmShort() {
		return benefitPointNmShort;
	}
	public void setBenefitPointNmShort(String benefitPointNmShort) {
		this.benefitPointNmShort = benefitPointNmShort;
	}
	public String getSpecialPointNm() {
		return specialPointNm;
	}
	public void setSpecialPointNm(String specialPointNm) {
		this.specialPointNm = specialPointNm;
	}
	public String getBenefitPointNm() {
		return benefitPointNm;
	}
	public void setBenefitPointNm(String benefitPointNm) {
		this.benefitPointNm = benefitPointNm;
	}
	public String getPrvCd() {
		return prvCd;
	}
	public void setPrvCd(String prvCd) {
		this.prvCd = prvCd;
	}
	public String getPrvNm() {
		return prvNm;
	}
	public void setPrvNm(String prvNm) {
		this.prvNm = prvNm;
	}
	public String getManual() {
		return manual;
	}
	public void setManual(String manual) {
		this.manual = manual;
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
	public long getBasePoint() {
		return basePoint;
	}
	public void setBasePoint(long basePoint) {
		this.basePoint = basePoint;
	}
	public String getBcond() {
		return bcond;
	}
	public void setBcond(String bcond) {
		this.bcond = bcond;
	}
	public String getBitem() {
		return bitem;
	}
	public void setBitem(String bitem) {
		this.bitem = bitem;
	}
	public String getBsnsKind() {
		return bsnsKind;
	}
	public void setBsnsKind(String bsnsKind) {
		this.bsnsKind = bsnsKind;
	}
	public String getBsnsNum() {
		return bsnsNum;
	}
	public void setBsnsNum(String bsnsNum) {
		this.bsnsNum = bsnsNum;
	}
	public String getCalcDd() {
		return calcDd;
	}
	public void setCalcDd(String calcDd) {
		this.calcDd = calcDd;
	}
	public String getCallTelType() {
		return callTelType;
	}
	public void setCallTelType(String callTelType) {
		this.callTelType = callTelType;
	}
	public String getCeilingPoint() {
		return ceilingPoint;
	}
	public void setCeilingPoint(String ceilingPoint) {
		this.ceilingPoint = ceilingPoint;
	}
	public String getCeilingType() {
		return ceilingType;
	}
	public void setCeilingType(String ceilingType) {
		this.ceilingType = ceilingType;
	}
	public String getCeilingUnit() {
		return ceilingUnit;
	}
	public void setCeilingUnit(String ceilingUnit) {
		this.ceilingUnit = ceilingUnit;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}
	public String getClientCd() {
		return clientCd;
	}
	public void setClientCd(String clientCd) {
		this.clientCd = clientCd;
	}
	public String getClientNm() {
		return clientNm;
	}
	public void setClientNm(String clientNm) {
		this.clientNm = clientNm;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEtcCardUseYn() {
		return etcCardUseYn;
	}
	public void setEtcCardUseYn(String etcCardUseYn) {
		this.etcCardUseYn = etcCardUseYn;
	}
	public String getExecNm() {
		return execNm;
	}
	public void setExecNm(String execNm) {
		this.execNm = execNm;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getMenuCeilingYn() {
		return menuCeilingYn;
	}
	public void setMenuCeilingYn(String menuCeilingYn) {
		this.menuCeilingYn = menuCeilingYn;
	}
	public String getModiDt() {
		return modiDt;
	}
	public void setModiDt(String modiDt) {
		this.modiDt = modiDt;
	}
	public String getOffUseYn() {
		return offUseYn;
	}
	public void setOffUseYn(String offUseYn) {
		this.offUseYn = offUseYn;
	}
	public String getOpenDd() {
		return openDd;
	}
	public void setOpenDd(String openDd) {
		this.openDd = openDd;
	}
	public String getPointBringRatio() {
		return pointBringRatio;
	}
	public void setPointBringRatio(String pointBringRatio) {
		this.pointBringRatio = pointBringRatio;
	}
	public String getPointBringYn() {
		return pointBringYn;
	}
	public void setPointBringYn(String pointBringYn) {
		this.pointBringYn = pointBringYn;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPromoGu() {
		return promoGu;
	}
	public void setPromoGu(String promoGu) {
		this.promoGu = promoGu;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getServiceEndDd() {
		return serviceEndDd;
	}
	public void setServiceEndDd(String serviceEndDd) {
		this.serviceEndDd = serviceEndDd;
	}
	public String getServiceStartDd() {
		return serviceStartDd;
	}
	public void setServiceStartDd(String serviceStartDd) {
		this.serviceStartDd = serviceStartDd;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getClientCeilingUseYn() {
		return clientCeilingUseYn;
	}
	public void setClientCeilingUseYn(String clientCeilingUseYn) {
		this.clientCeilingUseYn = clientCeilingUseYn;
	}
	public String getLoginImg() {
		return loginImg;
	}
	public void setLoginImg(String loginImg) {
		this.loginImg = loginImg;
	}
	public String getLoginIcon() {
		return loginIcon;
	}
	public void setLoginIcon(String loginIcon) {
		this.loginIcon = loginIcon;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getLoadDate() {
		return loadDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getOffCloseYn() {
		return offCloseYn;
	}
	public void setOffCloseYn(String offCloseYn) {
		this.offCloseYn = offCloseYn;
	}
	public String getOffReqCloseYn() {
		return offReqCloseYn;
	}
	public void setOffReqCloseYn(String offReqCloseYn) {
		this.offReqCloseYn = offReqCloseYn;
	}
	public String getServiceCloseYn() {
		return serviceCloseYn;
	}
	public void setServiceCloseYn(String serviceCloseYn) {
		this.serviceCloseYn = serviceCloseYn;
	}
	public String getServiceNoticeYn() {
		return serviceNoticeYn;
	}
	public void setServiceNoticeYn(String serviceNoticeYn) {
		this.serviceNoticeYn = serviceNoticeYn;
	}
	public String getReceiptPointYn() {
		return receiptPointYn;
	}
	public void setReceiptPointYn(String receiptPointYn) {
		this.receiptPointYn = receiptPointYn;
	}
	public String getPgType() {
		return pgType;
	}
	public void setPgType(String pgType) {
		this.pgType = pgType;
	}
	public String getClientGrp() {
		return clientGrp;
	}
	public void setClientGrp(String clientGrp) {
		this.clientGrp = clientGrp;
	}
	public String getWeltreeClientCd() {
		return weltreeClientCd;
	}
	public void setWeltreeClientCd(String weltreeClientCd) {
		this.weltreeClientCd = weltreeClientCd;
	}
	public String getOnlineCardCd() {
		return onlineCardCd;
	}
	public void setOnlineCardCd(String onlineCardCd) {
		this.onlineCardCd = onlineCardCd;
	}
	public String getDonateLimitAmt() {
		return donateLimitAmt;
	}
	public void setDonateLimitAmt(String donateLimitAmt) {
		this.donateLimitAmt = donateLimitAmt;
	}
	public String getFamSvcYn() {
		return famSvcYn;
	}
	public void setFamSvcYn(String famSvcYn) {
		this.famSvcYn = famSvcYn;
	}
	public String getIntroSvcYn() {
		return introSvcYn;
	}
	public void setIntroSvcYn(String introSvcYn) {
		this.introSvcYn = introSvcYn;
	}
	public String getFamUsePointYn() {
		return famUsePointYn;
	}
	public void setFamUsePointYn(String famUsePointYn) {
		this.famUsePointYn = famUsePointYn;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	
}
