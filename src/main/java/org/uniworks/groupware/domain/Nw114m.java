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
 * 관련 테이블 : Nw114m
 */ 
@XmlRootElement(name = "nw114m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "seqNo", "rfncType", "rfncCode", "rfncCodeName"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw114m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private int seqNo; 
	private String rfncType; 
	private String rfncCode; 
	private String rfncCodeName; 

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
	public void setSeqNo(int seqNo) { 
		this.seqNo = seqNo; 
	} 
	public int getSeqNo() { 
		return this.seqNo; 
	} 
	public void setRfncType(String rfncType) { 
		this.rfncType = rfncType; 
	} 
	public String getRfncType() { 
		return this.rfncType; 
	} 
	public void setRfncCode(String rfncCode) { 
		this.rfncCode = rfncCode; 
	} 
	public String getRfncCode() { 
		return this.rfncCode; 
	} 
	public void setRfncCodeName(String rfncCodeName) { 
		this.rfncCodeName = rfncCodeName; 
	} 
	public String getRfncCodeName() { 
		return this.rfncCodeName; 
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