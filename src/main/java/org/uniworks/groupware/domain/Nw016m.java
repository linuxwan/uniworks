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
 * 관련 테이블 : Nw016m
 */ 
@XmlRootElement(name = "nw016m") 
@XmlType(propOrder = {"coId", "cntnId", "docFormNo", "seqNo", "docForm", "authEmpNo", "rgsrDateTime", "mdfyEmpNo", "mdfyDateTime"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw016m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String docFormNo; 
	private int seqNo; 
	private String docForm; 
	private String authEmpNo; 
	private Timestamp rgsrDateTime; 
	private String mdfyEmpNo; 
	private Timestamp mdfyDateTime; 

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
	public void setDocFormNo(String docFormNo) { 
		this.docFormNo = docFormNo; 
	} 
	public String getDocFormNo() { 
		return this.docFormNo; 
	} 
	public void setSeqNo(int seqNo) { 
		this.seqNo = seqNo; 
	} 
	public int getSeqNo() { 
		return this.seqNo; 
	} 
	public void setDocForm(String docForm) { 
		this.docForm = docForm; 
	} 
	public String getDocForm() { 
		return this.docForm; 
	} 
	public void setAuthEmpNo(String authEmpNo) { 
		this.authEmpNo = authEmpNo; 
	} 
	public String getAuthEmpNo() { 
		return this.authEmpNo; 
	} 
	public void setRgsrDateTime(Timestamp rgsrDateTime) { 
		this.rgsrDateTime = rgsrDateTime; 
	} 
	public Timestamp getRgsrDateTime() { 
		return this.rgsrDateTime; 
	} 
	public void setMdfyEmpNo(String mdfyEmpNo) { 
		this.mdfyEmpNo = mdfyEmpNo; 
	} 
	public String getMdfyEmpNo() { 
		return this.mdfyEmpNo; 
	} 
	public void setMdfyDateTime(Timestamp mdfyDateTime) { 
		this.mdfyDateTime = mdfyDateTime; 
	} 
	public Timestamp getMdfyDateTime() { 
		return this.mdfyDateTime; 
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