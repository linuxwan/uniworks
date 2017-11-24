/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.domain; 

import java.io.Serializable; 
import java.sql.Timestamp; 

import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlType; 

import org.pojomatic.Pojomatic; 
import org.pojomatic.annotations.AutoProperty; 
/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw110m
 */ 
@XmlRootElement(name = "nw110m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "dcmtRgsrDatetime", "authEmpNo", "authDutyDesc", "authDeptCode", "authDeptDesc", "authTelNo", "docNo", "docTitle", "maxApprDgr", "apprStus", "crntApprDgr", "content", "prsvTermType", "prsvTerm", "atchIndc", "openIndc", "confDate", "confPlace", "confStartTime", "confEndTime", "apprMstId", "dcmtModDatetime"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw110m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private Timestamp dcmtRgsrDatetime; 
	private String authEmpNo; 
	private String authDutyDesc; 
	private String authDeptCode;
	private String authDeptDesc; 
	private String authTelNo; 
	private String docNo; 
	private String docTitle; 
	private int maxApprDgr; 
	private String apprStus; 
	private int crntApprDgr; 
	private String content; 
	private String prsvTermType; 
	private Timestamp prsvTerm; 
	private String atchIndc; 
	private String openIndc; 
	private Timestamp confDate; 
	private String confPlace; 
	private String confStartTime; 
	private String confEndTime; 
	private String apprMstId; 
	private Timestamp dcmtModDatetime; 

	public void setCoId(String coId) { 
		this.coId = coId; 
	} 
	public String getCoId() { 
		return this.coId; 
	} 
	public void setCntnId(String cntnId) { 
		this.cntnId = cntnId; 
	} 
	public String getCntnId() { 
		return this.cntnId; 
	} 
	public void setDcmtRgsrNo(String dcmtRgsrNo) { 
		this.dcmtRgsrNo = dcmtRgsrNo; 
	} 
	public String getDcmtRgsrNo() { 
		return this.dcmtRgsrNo; 
	} 
	public void setDcmtRgsrDatetime(Timestamp dcmtRgsrDatetime) { 
		this.dcmtRgsrDatetime = dcmtRgsrDatetime; 
	} 
	public Timestamp getDcmtRgsrDatetime() { 
		return this.dcmtRgsrDatetime; 
	} 
	public void setAuthEmpNo(String authEmpNo) { 
		this.authEmpNo = authEmpNo; 
	} 
	public String getAuthEmpNo() { 
		return this.authEmpNo; 
	} 
	public void setAuthDutyDesc(String authDutyDesc) { 
		this.authDutyDesc = authDutyDesc; 
	} 
	public String getAuthDutyDesc() { 
		return this.authDutyDesc; 
	} 
	public String getAuthDeptCode() {
		return authDeptCode;
	}
	public void setAuthDeptCode(String authDeptCode) {
		this.authDeptCode = authDeptCode;
	}
	public void setAuthDeptDesc(String authDeptDesc) { 
		this.authDeptDesc = authDeptDesc; 
	} 
	public String getAuthDeptDesc() { 
		return this.authDeptDesc; 
	} 
	public void setAuthTelNo(String authTelNo) { 
		this.authTelNo = authTelNo; 
	} 
	public String getAuthTelNo() { 
		return this.authTelNo; 
	} 
	public void setDocNo(String docNo) { 
		this.docNo = docNo; 
	} 
	public String getDocNo() { 
		return this.docNo; 
	} 
	public void setDocTitle(String docTitle) { 
		this.docTitle = docTitle; 
	} 
	public String getDocTitle() { 
		return this.docTitle; 
	} 
	public void setMaxApprDgr(int maxApprDgr) { 
		this.maxApprDgr = maxApprDgr; 
	} 
	public int getMaxApprDgr() { 
		return this.maxApprDgr; 
	} 
	public void setApprStus(String apprStus) { 
		this.apprStus = apprStus; 
	} 
	public String getApprStus() { 
		return this.apprStus; 
	} 
	public void setCrntApprDgr(int crntApprDgr) { 
		this.crntApprDgr = crntApprDgr; 
	} 
	public int getCrntApprDgr() { 
		return this.crntApprDgr; 
	} 
	public void setContent(String content) { 
		this.content = content; 
	} 
	public String getContent() { 
		return this.content; 
	} 
	public void setPrsvTermType(String prsvTermType) { 
		this.prsvTermType = prsvTermType; 
	} 
	public String getPrsvTermType() { 
		return this.prsvTermType; 
	} 
	public void setPrsvTerm(Timestamp prsvTerm) { 
		this.prsvTerm = prsvTerm; 
	} 
	public Timestamp getPrsvTerm() { 
		return this.prsvTerm; 
	} 
	public void setAtchIndc(String atchIndc) { 
		this.atchIndc = atchIndc; 
	} 
	public String getAtchIndc() { 
		return this.atchIndc; 
	} 
	public void setOpenIndc(String openIndc) { 
		this.openIndc = openIndc; 
	} 
	public String getOpenIndc() { 
		return this.openIndc; 
	} 
	public void setConfDate(Timestamp confDate) { 
		this.confDate = confDate; 
	} 
	public Timestamp getConfDate() { 
		return this.confDate; 
	} 
	public void setConfPlace(String confPlace) { 
		this.confPlace = confPlace; 
	} 
	public String getConfPlace() { 
		return this.confPlace; 
	} 
	public void setConfStartTime(String confStartTime) { 
		this.confStartTime = confStartTime; 
	} 
	public String getConfStartTime() { 
		return this.confStartTime; 
	} 
	public void setConfEndTime(String confEndTime) { 
		this.confEndTime = confEndTime; 
	} 
	public String getConfEndTime() { 
		return this.confEndTime; 
	} 
	public void setApprMstId(String apprMstId) { 
		this.apprMstId = apprMstId; 
	} 
	public String getApprMstId() { 
		return this.apprMstId; 
	} 
	public void setDcmtModDatetime(Timestamp dcmtModDatetime) { 
		this.dcmtModDatetime = dcmtModDatetime; 
	} 
	public Timestamp getDcmtModDatetime() { 
		return this.dcmtModDatetime; 
	} 
	@Override 
	public boolean equals(Object o) {  
		return Pojomatic.equals(this, o); 
	} 
	@Override 
	public int hashCode() { 
		return Pojomatic.hashCode(this); 
	} 
	@Override 
	public String toString() { 
		return Pojomatic.toString(this); 
	} 
}