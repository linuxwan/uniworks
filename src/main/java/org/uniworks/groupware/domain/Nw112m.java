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
 * 관련 테이블 : Nw112m
 */ 
@XmlRootElement(name = "nw112m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "apprDgr", "seqNo", "cprtEmpNo", "apprDateTime", "apprStus", "deptCode", "deptDesc", "dutyDesc", "pstnDesc", "steadApprEmpNo", "steadApprIndc", "comment"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw112m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private int apprDgr; 
	private int seqNo; 
	private String cprtEmpNo; 
	private Timestamp apprDateTime; 
	private String apprStus; 
	private String deptCode; 
	private String deptDesc; 
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
	public void setSeqNo(int seqNo) { 
		this.seqNo = seqNo; 
	} 
	public int getSeqNo() { 
		return this.seqNo; 
	} 
	public void setCprtEmpNo(String cprtEmpNo) { 
		this.cprtEmpNo = cprtEmpNo; 
	} 
	public String getCprtEmpNo() { 
		return this.cprtEmpNo; 
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
	public void setDeptCode(String deptCode) { 
		this.deptCode = deptCode; 
	} 
	public String getDeptCode() { 
		return this.deptCode; 
	} 
	public void setDeptDesc(String deptDesc) { 
		this.deptDesc = deptDesc; 
	} 
	public String getDeptDesc() { 
		return this.deptDesc; 
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