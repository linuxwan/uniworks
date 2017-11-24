/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.domain; 

import java.io.Serializable; 

import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlType; 

import org.pojomatic.Pojomatic; 
import org.pojomatic.annotations.AutoProperty; 
/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw119m
 */ 
@XmlRootElement(name = "nw119m") 
@XmlType(propOrder = {"coId", "cntnId", "deptCode", "year", "docNo"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw119m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String deptCode; 
	private String year; 
	private int docNo; 

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
	public void setDeptCode(String deptCode) { 
		this.deptCode = deptCode; 
	} 
	public String getDeptCode() { 
		return this.deptCode; 
	} 
	public void setYear(String year) { 
		this.year = year; 
	} 
	public String getYear() { 
		return this.year; 
	} 
	public void setDocNo(int docNo) { 
		this.docNo = docNo; 
	} 
	public int getDocNo() { 
		return this.docNo; 
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