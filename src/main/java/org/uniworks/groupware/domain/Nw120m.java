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
 * 관련 테이블 : Nw120m
 */ 
@XmlRootElement(name = "nw120m") 
@XmlType(propOrder = {"coId", "userId", "seqNo", "lastApprLev", "apprEmpNo1", "apprEmpNo2", "apprEmpNo3", "apprEmpNo4", "apprEmpNo5", "apprEmpNo6", "apprEmpNo7", "apprEmpNo8", "apprEmpNo9", "apprEmpNo10", "apprEmpNo11", "apprEmpNo12"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw120m implements Serializable {  
	private String coId; 
	private String userId; 
	private int seqNo; 
	private int lastApprLev; 
	private String apprEmpNo1; 
	private String apprEmpNo2; 
	private String apprEmpNo3; 
	private String apprEmpNo4; 
	private String apprEmpNo5; 
	private String apprEmpNo6; 
	private String apprEmpNo7; 
	private String apprEmpNo8; 
	private String apprEmpNo9; 
	private String apprEmpNo10; 
	private String apprEmpNo11; 
	private String apprEmpNo12; 

	public void setCoId(String coId) { 
		this.coId = coId; 
	} 
	public String getCoId() { 
		return this.coId; 
	} 
	public void setUserId(String userId) { 
		this.userId = userId; 
	} 
	public String getUserId() { 
		return this.userId; 
	} 
	public void setSeqNo(int seqNo) { 
		this.seqNo = seqNo; 
	} 
	public int getSeqNo() { 
		return this.seqNo; 
	} 
	public void setLastApprLev(int lastApprLev) { 
		this.lastApprLev = lastApprLev; 
	} 
	public int getLastApprLev() { 
		return this.lastApprLev; 
	} 
	public void setApprEmpNo1(String apprEmpNo1) { 
		this.apprEmpNo1 = apprEmpNo1; 
	} 
	public String getApprEmpNo1() { 
		return this.apprEmpNo1; 
	} 
	public void setApprEmpNo2(String apprEmpNo2) { 
		this.apprEmpNo2 = apprEmpNo2; 
	} 
	public String getApprEmpNo2() { 
		return this.apprEmpNo2; 
	} 
	public void setApprEmpNo3(String apprEmpNo3) { 
		this.apprEmpNo3 = apprEmpNo3; 
	} 
	public String getApprEmpNo3() { 
		return this.apprEmpNo3; 
	} 
	public void setApprEmpNo4(String apprEmpNo4) { 
		this.apprEmpNo4 = apprEmpNo4; 
	} 
	public String getApprEmpNo4() { 
		return this.apprEmpNo4; 
	} 
	public void setApprEmpNo5(String apprEmpNo5) { 
		this.apprEmpNo5 = apprEmpNo5; 
	} 
	public String getApprEmpNo5() { 
		return this.apprEmpNo5; 
	} 
	public void setApprEmpNo6(String apprEmpNo6) { 
		this.apprEmpNo6 = apprEmpNo6; 
	} 
	public String getApprEmpNo6() { 
		return this.apprEmpNo6; 
	} 
	public void setApprEmpNo7(String apprEmpNo7) { 
		this.apprEmpNo7 = apprEmpNo7; 
	} 
	public String getApprEmpNo7() { 
		return this.apprEmpNo7; 
	} 
	public void setApprEmpNo8(String apprEmpNo8) { 
		this.apprEmpNo8 = apprEmpNo8; 
	} 
	public String getApprEmpNo8() { 
		return this.apprEmpNo8; 
	} 
	public void setApprEmpNo9(String apprEmpNo9) { 
		this.apprEmpNo9 = apprEmpNo9; 
	} 
	public String getApprEmpNo9() { 
		return this.apprEmpNo9; 
	} 
	public void setApprEmpNo10(String apprEmpNo10) { 
		this.apprEmpNo10 = apprEmpNo10; 
	} 
	public String getApprEmpNo10() { 
		return this.apprEmpNo10; 
	} 
	public void setApprEmpNo11(String apprEmpNo11) { 
		this.apprEmpNo11 = apprEmpNo11; 
	} 
	public String getApprEmpNo11() { 
		return this.apprEmpNo11; 
	} 
	public void setApprEmpNo12(String apprEmpNo12) { 
		this.apprEmpNo12 = apprEmpNo12; 
	} 
	public String getApprEmpNo12() { 
		return this.apprEmpNo12; 
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