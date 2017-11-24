/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common;

/**
 * @author Park Chungwan
 *
 */
public class UserSession {
	private String coId;	//회사구분
	private String userId;	//사용자 ID
	private String sysUserId;	//시스템 사용자 ID
	private String empName;		//사용자 이름
	private String dutyCode;	//직위 코드
	private String dutyDesc;	//직위 명칭
	private String pstnCode;	//보직 코드
	private String pstnDesc;	//보직 명칭
	private String deptCode;	//부서코드 - 각 회사별 기준이 되는 부서 코드
	private String deptDesc;	//부서명칭 - 각 회사별 기준이 되는 부서 명칭
	private String offcTelNo;		//사무실 전화번호
	private String moblPhoneNo;		//모바일 번호
	private String asgnOganCode1;	//소속조직코드1
	private String asgnOganDesc1;	//소속조직코드1
	private String asgnOganCode2;	//소속조직코드2
	private String asgnOganDesc2;	//소속조직코드2
	private String asgnOganCode3;	//소속조직코드3
	private String asgnOganDesc3;	//소속조직코드3
	private String asgnOganCode4;	//소속조직코드4
	private String asgnOganDesc4;	//소속조직코드4
	private String asgnOganCode5;	//소속조직코드5
	private String asgnOganDesc5;	//소속조직코드5
	private String asgnOganCode6;	//소속조직코드6
	private String asgnOganDesc6;	//소속조직코드6
	private String asgnOganCode7;	//소속조직코드7
	private String asgnOganDesc7;	//소속조직코드7
	private String asgnOganCode8;	//소속조직코드8
	private String asgnOganDesc8;	//소속조직코드8
	private String asgnOganCode9;	//소속조직코드9
	private String asgnOganDesc9;	//소속조직코드9
	private String asgnOganCode10;	//소속조직코드10
	private String asgnOganDesc10;	//소속조직코드10
	private String asgnOganCode11;	//소속조직코드11
	private String asgnOganDesc11;	//소속조직코드11
	private String asgnOganCode12;	//소속조직코드12
	private String asgnOganDesc12;	//소속조직코드12
	private String internalMailAddr;	//사내 메일 주소
	private String outsideMailAddr;		//사외 메일 주소
	private String pswd;	//로그인 비밀번호
	private String cnfmPswd;	//본인확인 비밀번호
	private String locale;	//로케일 정보(대문자)
	private String language;	//사용 언어
	private String baseOganLev; //기준조직레벨
	private String lowCaseLocale; //소문자 로케일 정보
	private String allOganCode; //소속된 조직코드를 모두 나열. 구분자는 ,로 한다. 특정 조직에 속하는지를 쉽게 판단하기 위해서 만든 필드.
	
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getDutyDesc() {
		return dutyDesc;
	}
	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}
	public String getPstnCode() {
		return pstnCode;
	}
	public void setPstnCode(String pstnCode) {
		this.pstnCode = pstnCode;
	}
	public String getPstnDesc() {
		return pstnDesc;
	}
	public void setPstnDesc(String pstnDesc) {
		this.pstnDesc = pstnDesc;
	}
	public String getOffcTelNo() {
		return offcTelNo;
	}
	public void setOffcTelNo(String offcTelNo) {
		this.offcTelNo = offcTelNo;
	}
	public String getMoblPhoneNo() {
		return moblPhoneNo;
	}
	public void setMoblPhoneNo(String moblPhoneNo) {
		this.moblPhoneNo = moblPhoneNo;
	}
	public String getAsgnOganCode1() {
		return asgnOganCode1;
	}
	public void setAsgnOganCode1(String asgnOganCode1) {
		this.asgnOganCode1 = asgnOganCode1;
	}
	public String getAsgnOganDesc1() {
		return asgnOganDesc1;
	}
	public void setAsgnOganDesc1(String asgnOganDesc1) {
		this.asgnOganDesc1 = asgnOganDesc1;
	}
	public String getAsgnOganCode2() {
		return asgnOganCode2;
	}
	public void setAsgnOganCode2(String asgnOganCode2) {
		this.asgnOganCode2 = asgnOganCode2;
	}
	public String getAsgnOganDesc2() {
		return asgnOganDesc2;
	}
	public void setAsgnOganDesc2(String asgnOganDesc2) {
		this.asgnOganDesc2 = asgnOganDesc2;
	}
	public String getAsgnOganCode3() {
		return asgnOganCode3;
	}
	public void setAsgnOganCode3(String asgnOganCode3) {
		this.asgnOganCode3 = asgnOganCode3;
	}
	public String getAsgnOganDesc3() {
		return asgnOganDesc3;
	}
	public void setAsgnOganDesc3(String asgnOganDesc3) {
		this.asgnOganDesc3 = asgnOganDesc3;
	}
	public String getAsgnOganCode4() {
		return asgnOganCode4;
	}
	public void setAsgnOganCode4(String asgnOganCode4) {
		this.asgnOganCode4 = asgnOganCode4;
	}
	public String getAsgnOganDesc4() {
		return asgnOganDesc4;
	}
	public void setAsgnOganDesc4(String asgnOganDesc4) {
		this.asgnOganDesc4 = asgnOganDesc4;
	}
	public String getAsgnOganCode5() {
		return asgnOganCode5;
	}
	public void setAsgnOganCode5(String asgnOganCode5) {
		this.asgnOganCode5 = asgnOganCode5;
	}
	public String getAsgnOganDesc5() {
		return asgnOganDesc5;
	}
	public void setAsgnOganDesc5(String asgnOganDesc5) {
		this.asgnOganDesc5 = asgnOganDesc5;
	}
	public String getAsgnOganCode6() {
		return asgnOganCode6;
	}
	public void setAsgnOganCode6(String asgnOganCode6) {
		this.asgnOganCode6 = asgnOganCode6;
	}
	public String getAsgnOganDesc6() {
		return asgnOganDesc6;
	}
	public void setAsgnOganDesc6(String asgnOganDesc6) {
		this.asgnOganDesc6 = asgnOganDesc6;
	}
	public String getAsgnOganCode7() {
		return asgnOganCode7;
	}
	public void setAsgnOganCode7(String asgnOganCode7) {
		this.asgnOganCode7 = asgnOganCode7;
	}
	public String getAsgnOganDesc7() {
		return asgnOganDesc7;
	}
	public void setAsgnOganDesc7(String asgnOganDesc7) {
		this.asgnOganDesc7 = asgnOganDesc7;
	}
	public String getAsgnOganCode8() {
		return asgnOganCode8;
	}
	public void setAsgnOganCode8(String asgnOganCode8) {
		this.asgnOganCode8 = asgnOganCode8;
	}
	public String getAsgnOganDesc8() {
		return asgnOganDesc8;
	}
	public void setAsgnOganDesc8(String asgnOganDesc8) {
		this.asgnOganDesc8 = asgnOganDesc8;
	}
	public String getAsgnOganCode9() {
		return asgnOganCode9;
	}
	public void setAsgnOganCode9(String asgnOganCode9) {
		this.asgnOganCode9 = asgnOganCode9;
	}
	public String getAsgnOganDesc9() {
		return asgnOganDesc9;
	}
	public void setAsgnOganDesc9(String asgnOganDesc9) {
		this.asgnOganDesc9 = asgnOganDesc9;
	}
	public String getAsgnOganCode10() {
		return asgnOganCode10;
	}
	public void setAsgnOganCode10(String asgnOganCode10) {
		this.asgnOganCode10 = asgnOganCode10;
	}
	public String getAsgnOganDesc10() {
		return asgnOganDesc10;
	}
	public void setAsgnOganDesc10(String asgnOganDesc10) {
		this.asgnOganDesc10 = asgnOganDesc10;
	}
	public String getAsgnOganCode11() {
		return asgnOganCode11;
	}
	public void setAsgnOganCode11(String asgnOganCode11) {
		this.asgnOganCode11 = asgnOganCode11;
	}
	public String getAsgnOganDesc11() {
		return asgnOganDesc11;
	}
	public void setAsgnOganDesc11(String asgnOganDesc11) {
		this.asgnOganDesc11 = asgnOganDesc11;
	}
	public String getAsgnOganCode12() {
		return asgnOganCode12;
	}
	public void setAsgnOganCode12(String asgnOganCode12) {
		this.asgnOganCode12 = asgnOganCode12;
	}
	public String getAsgnOganDesc12() {
		return asgnOganDesc12;
	}
	public void setAsgnOganDesc12(String asgnOganDesc12) {
		this.asgnOganDesc12 = asgnOganDesc12;
	}
	public String getInternalMailAddr() {
		return internalMailAddr;
	}
	public void setInternalMailAddr(String internalMailAddr) {
		this.internalMailAddr = internalMailAddr;
	}
	public String getOutsideMailAddr() {
		return outsideMailAddr;
	}
	public void setOutsideMailAddr(String outsideMailAddr) {
		this.outsideMailAddr = outsideMailAddr;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getCnfmPswd() {
		return cnfmPswd;
	}
	public void setCnfmPswd(String cnfmPswd) {
		this.cnfmPswd = cnfmPswd;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getBaseOganLev() {
		return baseOganLev;
	}
	public void setBaseOganLev(String baseOganLev) {
		this.baseOganLev = baseOganLev;
	}
	public void setLowCaseLocale(String locale) {
		this.lowCaseLocale = locale.toLowerCase();
	}
	public String getLowCaseLocale() {
		lowCaseLocale = locale.toLowerCase();
		return lowCaseLocale;
	}
	public String getAllOganCode() {
		return allOganCode;
	}
	public void setAllOganCode(String allOganCode) {
		this.allOganCode = allOganCode;
	}
}
