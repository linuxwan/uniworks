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
 * @author Park Chungwan
 * LineApprover.java 2012. 5. 13.
 * 라인 결재자 정보. 결재 마스터 정보에서 정의된 최대 결재자 수를 넘어서 라인 결재자를 생성할 수 없다.
 * 라인 결재자 생성 시에 최대 결재차수와 라인 결재자 수를 항상 확인해야 함.
 */
@AutoProperty 
@SuppressWarnings("serial")
public class LineApprover implements Serializable {
	private String coId;	//회사 구분
	private String cntnId;	//콘텐츠 ID
	private String dcmtRgsrNo;	//문서등록번호
	private int apprDgr;	//결재차수
	private String apprEmpNo;	//결재자 사번
	private String apprEmpName;	//결재자 성명
	private Date apprDateTime;	//결재일시
	private String apprStus;	//결재상태 - 0:상태무, 1:대기, 5:반려, 7:승인, 9:대결 승인 
	private String apprDeptCode;	//결재자 부서코드
	private String apprDeptDesc;	//결재자 부서명칭
	private String dutyDesc;	//직위(직책) 명칭
	private String pstnDesc;	//보직 명칭
	private String steadApprEmpNo;	//대결재자 사번
	private String steadApprEmpName;	//대결재자 성명
	private String steadApprIndc;	//대결재 유무
	private String comment;	//Comment
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
	public String getDcmtRgsrNo() {
		return dcmtRgsrNo;
	}
	public void setDcmtRgsrNo(String dcmtRgsrNo) {
		this.dcmtRgsrNo = dcmtRgsrNo;
	}
	public int getApprDgr() {
		return apprDgr;
	}
	public void setApprDgr(int apprDgr) {
		this.apprDgr = apprDgr;
	}
	public String getApprEmpNo() {
		return apprEmpNo;
	}
	public void setApprEmpNo(String apprEmpNo) {
		this.apprEmpNo = apprEmpNo;
	}
	public String getApprEmpName() {
		return apprEmpName;
	}
	public void setApprEmpName(String apprEmpName) {
		this.apprEmpName = apprEmpName;
	}
	public Date getApprDateTime() {
		return apprDateTime;
	}
	public void setApprDateTime(Date apprDateTime) {
		this.apprDateTime = apprDateTime;
	}
	public String getApprStus() {
		return apprStus;
	}
	public void setApprStus(String apprStus) {
		this.apprStus = apprStus;
	}
	public String getApprDeptCode() {
		return apprDeptCode;
	}
	public void setApprDeptCode(String apprDeptCode) {
		this.apprDeptCode = apprDeptCode;
	}
	public String getApprDeptDesc() {
		return apprDeptDesc;
	}
	public void setApprDeptDesc(String apprDeptDesc) {
		this.apprDeptDesc = apprDeptDesc;
	}
	public String getDutyDesc() {
		return dutyDesc;
	}
	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}
	public String getPstnDesc() {
		return pstnDesc;
	}
	public void setPstnDesc(String pstnDesc) {
		this.pstnDesc = pstnDesc;
	}
	public String getSteadApprEmpNo() {
		return steadApprEmpNo;
	}
	public void setSteadApprEmpNo(String steadApprEmpNo) {
		this.steadApprEmpNo = steadApprEmpNo;
	}
	public String getSteadApprEmpName() {
		return steadApprEmpName;
	}
	public void setSteadApprEmpName(String steadApprEmpName) {
		this.steadApprEmpName = steadApprEmpName;
	}
	public String getSteadApprIndc() {
		return steadApprIndc;
	}
	public void setSteadApprIndc(String steadApprIndc) {
		this.steadApprIndc = steadApprIndc;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
