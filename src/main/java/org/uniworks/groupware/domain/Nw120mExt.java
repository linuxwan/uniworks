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
@XmlType(propOrder = {"coId", "userId", "seqNo", "lastApprLev", "apprEmpNo1", "apprEmpName1", "apprEmpNo2", "apprEmpName2", "apprEmpNo3", "apprEmpName3", "apprEmpNo4", "apprEmpName4", "apprEmpNo5", "apprEmpName5", "apprEmpNo6", "apprEmpName6", "apprEmpNo7", "apprEmpName7", "apprEmpNo8", "apprEmpName8", "apprEmpNo9", "apprEmpName9", "apprEmpNo10", "apprEmpName10", "apprEmpNo11", "apprEmpName11", "apprEmpNo12", "apprEmpName12"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw120mExt implements Serializable {  
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
	private String apprEmpName1;
	private String apprEmpName2;
	private String apprEmpName3;
	private String apprEmpName4;
	private String apprEmpName5;
	private String apprEmpName6;
	private String apprEmpName7;
	private String apprEmpName8;
	private String apprEmpName9;
	private String apprEmpName10;
	private String apprEmpName11;
	private String apprEmpName12;

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
	public String getApprEmpName1() {
		return apprEmpName1;
	}
	public void setApprEmpName1(String apprEmpName1) {
		this.apprEmpName1 = apprEmpName1;
	}
	public String getApprEmpName2() {
		return apprEmpName2;
	}
	public void setApprEmpName2(String apprEmpName2) {
		this.apprEmpName2 = apprEmpName2;
	}
	public String getApprEmpName3() {
		return apprEmpName3;
	}
	public void setApprEmpName3(String apprEmpName3) {
		this.apprEmpName3 = apprEmpName3;
	}
	public String getApprEmpName4() {
		return apprEmpName4;
	}
	public void setApprEmpName4(String apprEmpName4) {
		this.apprEmpName4 = apprEmpName4;
	}
	public String getApprEmpName5() {
		return apprEmpName5;
	}
	public void setApprEmpName5(String apprEmpName5) {
		this.apprEmpName5 = apprEmpName5;
	}
	public String getApprEmpName6() {
		return apprEmpName6;
	}
	public void setApprEmpName6(String apprEmpName6) {
		this.apprEmpName6 = apprEmpName6;
	}
	public String getApprEmpName7() {
		return apprEmpName7;
	}
	public void setApprEmpName7(String apprEmpName7) {
		this.apprEmpName7 = apprEmpName7;
	}
	public String getApprEmpName8() {
		return apprEmpName8;
	}
	public void setApprEmpName8(String apprEmpName8) {
		this.apprEmpName8 = apprEmpName8;
	}
	public String getApprEmpName9() {
		return apprEmpName9;
	}
	public void setApprEmpName9(String apprEmpName9) {
		this.apprEmpName9 = apprEmpName9;
	}
	public String getApprEmpName10() {
		return apprEmpName10;
	}
	public void setApprEmpName10(String apprEmpName10) {
		this.apprEmpName10 = apprEmpName10;
	}
	public String getApprEmpName11() {
		return apprEmpName11;
	}
	public void setApprEmpName11(String apprEmpName11) {
		this.apprEmpName11 = apprEmpName11;
	}
	public String getApprEmpName12() {
		return apprEmpName12;
	}
	public void setApprEmpName12(String apprEmpName12) {
		this.apprEmpName12 = apprEmpName12;
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