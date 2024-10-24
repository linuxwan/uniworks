/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.domain.approval;

import java.io.Serializable;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * @author Chungwan Park
 * ApprovalLevel.java 2012. 2. 17.
 */
@AutoProperty 
@SuppressWarnings("serial")
public class ApprovalLevel implements Serializable {
	private String coId;	//회사구분
	private String apprMstId;	//결재 Master ID
	private String crtDate;	//시작일자
	private int apprLevel;	//결재차수
	private String clsDate;	//종료일자
	
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getApprMstId() {
		return apprMstId;
	}
	public void setApprMstId(String apprMstId) {
		this.apprMstId = apprMstId;
	}
	public String getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	public int getApprLevel() {
		return apprLevel;
	}
	public void setApprLevel(int apprLevel) {
		this.apprLevel = apprLevel;
	}
	public String getClsDate() {
		return clsDate;
	}
	public void setClsDate(String clsDate) {
		this.clsDate = clsDate;
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
