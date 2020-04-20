/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.domain; 

import java.io.Serializable; 
import java.util.Date; 

import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlType; 

import org.pojomatic.Pojomatic; 
import org.pojomatic.annotations.AutoProperty; 
/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw132m
 */ 
@XmlRootElement(name = "nw132m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "authEmpNo", "rgsrDateTime", "authDutyDesc", "authDeptCode", "authDeptDesc", "score"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw132m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private String authEmpNo; 
	private Date rgsrDateTime; 
	private String authDutyDesc; 
	private String authDeptCode; 
	private String authDeptDesc; 
	private long score; 

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
	public void setAuthEmpNo(String authEmpNo) { 
		this.authEmpNo = authEmpNo; 
	} 
	public String getAuthEmpNo() { 
		return this.authEmpNo; 
	} 
	public void setRgsrDateTime(Date rgsrDateTime) { 
		this.rgsrDateTime = rgsrDateTime; 
	} 
	public Date getRgsrDateTime() { 
		return this.rgsrDateTime; 
	} 
	public void setAuthDutyDesc(String authDutyDesc) { 
		this.authDutyDesc = authDutyDesc; 
	} 
	public String getAuthDutyDesc() { 
		return this.authDutyDesc; 
	} 
	public void setAuthDeptCode(String authDeptCode) { 
		this.authDeptCode = authDeptCode; 
	} 
	public String getAuthDeptCode() { 
		return this.authDeptCode; 
	} 
	public void setAuthDeptDesc(String authDeptDesc) { 
		this.authDeptDesc = authDeptDesc; 
	} 
	public String getAuthDeptDesc() { 
		return this.authDeptDesc; 
	} 
	public void setScore(long score) { 
		this.score = score; 
	} 
	public long getScore() { 
		return this.score; 
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