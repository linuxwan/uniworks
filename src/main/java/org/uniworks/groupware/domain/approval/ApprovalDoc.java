/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.domain.approval;

import java.io.Serializable;
import java.util.Date;

import org.pojomatic.annotations.AutoProperty;

/**
 * @author Chungwan Park
 * ApprovalDoc.java 2012. 4. 23.
 * 사용자들이 작성한 결재문서 정보를 담고 있는 클래스.
 */
@AutoProperty 
@SuppressWarnings("serial")
public class ApprovalDoc implements Serializable {
	private String coId;	//회사 구분
	private String cntnId;	//컨텐츠 ID
	private String cntnName;	//컨텐츠 명칭
	private String dcmtRgsrNo;	//문서등록번호
	private Date dcmtRgsrDatetime;	//문서등록일시
	private String authEmpNo;	//작성자 사번
	private String empName;	//작성자 성명
	private String authDutyDesc;	//작성자 직위명칭
	private String authDeptCode;	//작성자 부서코드
	private String authDeptDesc;	//작성자 부서명칭
	private String authTelNo;	//작성자 전화번호
	private String docNo;	//문서 번호
	private String docTitle;	//문서 제목
	private int maxApprDgr;	//최대 결재 차수
	private String apprStus;	//결재 상태
	private int crntApprDgr;	//현재 결재 차수
	private String content;	//내용
	private String prsvTermType;	//보존연한 유형
	private String prsvTermTypeDesc;	//보존연한 명칭
	private Date prsvTerm;	//보존연한
	private String atchIndc;	//첨부파일 유무
	private String openIndc;	//공개 유무
	private Date confDate;	//회의 일자
	private String confPlace;	//회의 장소
	private String confStartTime;	//회의시작 시간
	private String confEndTime;	//회의종료 시간
	private String apprMstId;	//결재 마스터 ID
	private Date dcmtModDatetime;	//문서수정일자
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getCntnId() {
		return cntnId;
	}
	public void setCntnId(String cntnId) {
		this.cntnId = cntnId;
	}
	public String getCntnName() {
		return cntnName;
	}
	public void setCntnName(String cntnName) {
		this.cntnName = cntnName;
	}
	public String getDcmtRgsrNo() {
		return dcmtRgsrNo;
	}
	public void setDcmtRgsrNo(String dcmtRgsrNo) {
		this.dcmtRgsrNo = dcmtRgsrNo;
	}
	public Date getDcmtRgsrDatetime() {
		return dcmtRgsrDatetime;
	}
	public void setDcmtRgsrDatetime(Date dcmtRgsrDatetime) {
		this.dcmtRgsrDatetime = dcmtRgsrDatetime;
	}
	public String getAuthEmpNo() {
		return authEmpNo;
	}
	public void setAuthEmpNo(String authEmpNo) {
		this.authEmpNo = authEmpNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAuthDutyDesc() {
		return authDutyDesc;
	}
	public void setAuthDutyDesc(String authDutyDesc) {
		this.authDutyDesc = authDutyDesc;
	}
	public String getAuthDeptCode() {
		return authDeptCode;
	}
	public void setAuthDeptCode(String authDeptCode) {
		this.authDeptCode = authDeptCode;
	}
	public String getAuthDeptDesc() {
		return authDeptDesc;
	}
	public void setAuthDeptDesc(String authDeptDesc) {
		this.authDeptDesc = authDeptDesc;
	}
	public String getAuthTelNo() {
		return authTelNo;
	}
	public void setAuthTelNo(String authTelNo) {
		this.authTelNo = authTelNo;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public int getMaxApprDgr() {
		return maxApprDgr;
	}
	public void setMaxApprDgr(int maxApprDgr) {
		this.maxApprDgr = maxApprDgr;
	}
	public String getApprStus() {
		return apprStus;
	}
	public void setApprStus(String apprStus) {
		this.apprStus = apprStus;
	}
	public int getCrntApprDgr() {
		return crntApprDgr;
	}
	public void setCrntApprDgr(int crntApprDgr) {
		this.crntApprDgr = crntApprDgr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPrsvTermType() {
		return prsvTermType;
	}
	public void setPrsvTermType(String prsvTermType) {
		this.prsvTermType = prsvTermType;
	}
	public String getPrsvTermTypeDesc() {
		return prsvTermTypeDesc;
	}
	public void setPrsvTermTypeDesc(String prsvTermTypeDesc) {
		this.prsvTermTypeDesc = prsvTermTypeDesc;
	}
	public Date getPrsvTerm() {
		return prsvTerm;
	}
	public void setPrsvTerm(Date prsvTerm) {
		this.prsvTerm = prsvTerm;
	}
	public String getAtchIndc() {
		return atchIndc;
	}
	public void setAtchIndc(String atchIndc) {
		this.atchIndc = atchIndc;
	}
	public String getOpenIndc() {
		return openIndc;
	}
	public void setOpenIndc(String openIndc) {
		this.openIndc = openIndc;
	}
	public Date getConfDate() {
		return confDate;
	}
	public void setConfDate(Date confDate) {
		this.confDate = confDate;
	}
	public String getConfPlace() {
		return confPlace;
	}
	public void setConfPlace(String confPlace) {
		this.confPlace = confPlace;
	}
	public String getConfStartTime() {
		return confStartTime;
	}
	public void setConfStartTime(String confStartTime) {
		this.confStartTime = confStartTime;
	}
	public String getConfEndTime() {
		return confEndTime;
	}
	public void setConfEndTime(String confEndTime) {
		this.confEndTime = confEndTime;
	}
	public String getApprMstId() {
		return apprMstId;
	}
	public void setApprMstId(String apprMstId) {
		this.apprMstId = apprMstId;
	}
	public Date getDcmtModDatetime() {
		return dcmtModDatetime;
	}
	public void setDcmtModDatetime(Date dcmtModDatetime) {
		this.dcmtModDatetime = dcmtModDatetime;
	}
}
