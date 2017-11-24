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
 * 관련 테이블 : Nw111m
 */ 
@XmlRootElement(name = "nw111m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "apprDgr", "apprEmpNo", "apprDateTime", "apprStus", "apprDeptCode", "apprDeptDesc", "dutyDesc", "pstnDesc", "steadApprEmpNo", "steadApprIndc", "comment"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw111m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private int apprDgr; 
	private String apprEmpNo; 
	private Timestamp apprDateTime; 
	private String apprStus; 
	private String apprDeptCode; 
	private String apprDeptDesc; 
	private String dutyDesc; 
	private String pstnDesc; 
	private String steadApprEmpNo; 
	private String steadApprIndc; 
	private String comment;

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
	public void setApprDgr(int apprDgr) { 
		this.apprDgr = apprDgr; 
	} 
	public int getApprDgr() { 
		return this.apprDgr; 
	} 
	public void setApprEmpNo(String apprEmpNo) { 
		this.apprEmpNo = apprEmpNo; 
	} 
	public String getApprEmpNo() { 
		return this.apprEmpNo; 
	} 
	public void setApprDateTime(Timestamp apprDateTime) { 
		this.apprDateTime = apprDateTime; 
	} 
	public Timestamp getApprDateTime() { 
		return this.apprDateTime; 
	} 
	public void setApprStus(String apprStus) { 
		this.apprStus = apprStus; 
	} 
	public String getApprStus() { 
		return this.apprStus; 
	} 
	public void setApprDeptCode(String apprDeptCode) { 
		this.apprDeptCode = apprDeptCode; 
	} 
	public String getApprDeptCode() { 
		return this.apprDeptCode; 
	} 
	public void setApprDeptDesc(String apprDeptDesc) { 
		this.apprDeptDesc = apprDeptDesc; 
	} 
	public String getApprDeptDesc() { 
		return this.apprDeptDesc; 
	} 
	public void setDutyDesc(String dutyDesc) { 
		this.dutyDesc = dutyDesc; 
	} 
	public String getDutyDesc() { 
		return this.dutyDesc; 
	} 
	public void setPstnDesc(String pstnDesc) { 
		this.pstnDesc = pstnDesc; 
	} 
	public String getPstnDesc() { 
		return this.pstnDesc; 
	} 
	public void setSteadApprEmpNo(String steadApprEmpNo) { 
		this.steadApprEmpNo = steadApprEmpNo; 
	} 
	public String getSteadApprEmpNo() { 
		return this.steadApprEmpNo; 
	} 
	public void setSteadApprIndc(String steadApprIndc) { 
		this.steadApprIndc = steadApprIndc; 
	} 
	public String getSteadApprIndc() { 
		return this.steadApprIndc; 
	} 	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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