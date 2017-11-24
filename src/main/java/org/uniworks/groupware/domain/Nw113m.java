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
 * 관련 테이블 : Nw113m
 */ 
@XmlRootElement(name = "nw113m") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "seqNo", "rcptOganGrpCode", "rcptOganGrpName"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw113m implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private int seqNo; 
	private String rcptOganGrpCode; 
	private String rcptOganGrpName; 

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
	public void setRcptOganGrpCode(String rcptOganGrpCode) { 
		this.rcptOganGrpCode = rcptOganGrpCode; 
	} 
	public String getRcptOganGrpCode() { 
		return this.rcptOganGrpCode; 
	} 
	public void setRcptOganGrpName(String rcptOganGrpName) { 
		this.rcptOganGrpName = rcptOganGrpName; 
	} 
	public String getRcptOganGrpName() { 
		return this.rcptOganGrpName; 
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