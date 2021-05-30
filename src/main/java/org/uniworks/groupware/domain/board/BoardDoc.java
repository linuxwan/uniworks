/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.domain.board; 

import java.io.Serializable; 
import java.util.Date; 

import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlType; 

import org.pojomatic.Pojomatic; 
import org.pojomatic.annotations.AutoProperty; 
/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw130m
 */ 
@XmlRootElement(name = "boardDoc") 
@XmlType(propOrder = {"coId", "cntnId", "dcmtRgsrNo", "prntDcmtRgsrNo", "dcmtRgsrDatetime", "authEmpNo", "authEmpName", "authDutyDesc", "authDeptCode", "authDeptDesc", "authTelNo", "prsvTermType", "prsvTerm", "title", "content", "salePrice", "postIndc", "rgsrCnfmUser", "rgsrCnfmUserName", "rgsrCnfmDutyDesc", "rgsrCnfmDeptCode", "rgsrCnfmDeptDesc", "rgsrCnfmDatetime", "viewCnt", "atchIndc", "boardId", "typeCode1", "typeCode2", "typeCode3", "typeCode4", "chngId", "mdfyDateTime"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class BoardDoc implements Serializable {  
	private String coId; 
	private String cntnId; 
	private String dcmtRgsrNo; 
	private String prntDcmtRgsrNo; 
	private Date dcmtRgsrDatetime; 
	private String authEmpNo; 
	private String authEmpName;
	private String authDutyDesc; 
	private String authDeptCode; 
	private String authDeptDesc; 
	private String authTelNo; 
	private String prsvTermType; 
	private Date prsvTerm; 
	private String title; 
	private String content;
	private int salePrice; 
	private String postIndc;
	private String rgsrCnfmUser; 
	private String rgsrCnfmUserName;
	private String rgsrCnfmDutyDesc; 
	private String rgsrCnfmDeptCode; 
	private String rgsrCnfmDeptDesc; 
	private Date rgsrCnfmDatetime;
	private long viewCnt; 
	private String atchIndc; 
	private String boardId; 
	private String typeCode1; 
	private String typeCode2; 
	private String typeCode3; 
	private String typeCode4; 
	private String chngId; 
	private Date mdfyDateTime; 

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
	public void setPrntDcmtRgsrNo(String prntDcmtRgsrNo) { 
		this.prntDcmtRgsrNo = prntDcmtRgsrNo; 
	} 
	public String getPrntDcmtRgsrNo() { 
		return this.prntDcmtRgsrNo; 
	} 
	public void setDcmtRgsrDatetime(Date dcmtRgsrDatetime) { 
		this.dcmtRgsrDatetime = dcmtRgsrDatetime; 
	} 
	public Date getDcmtRgsrDatetime() { 
		return this.dcmtRgsrDatetime; 
	} 
	public void setAuthEmpNo(String authEmpNo) { 
		this.authEmpNo = authEmpNo; 
	} 
	public String getAuthEmpNo() { 
		return this.authEmpNo; 
	} 
	public String getAuthEmpName() {
		return authEmpName;
	}
	public void setAuthEmpName(String authEmpName) {
		this.authEmpName = authEmpName;
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
	public void setAuthTelNo(String authTelNo) { 
		this.authTelNo = authTelNo; 
	} 
	public String getAuthTelNo() { 
		return this.authTelNo; 
	} 
	public void setPrsvTermType(String prsvTermType) { 
		this.prsvTermType = prsvTermType; 
	} 
	public String getPrsvTermType() { 
		return this.prsvTermType; 
	} 
	public void setPrsvTerm(Date prsvTerm) { 
		this.prsvTerm = prsvTerm; 
	} 
	public Date getPrsvTerm() { 
		return this.prsvTerm; 
	} 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public void setPostIndc(String postIndc) {
		this.postIndc = postIndc;
	}
	public String getPostIndc() {
		return this.postIndc;
	}
	public void setRgsrCnfmUser(String rgsrCnfmUser) { 
		this.rgsrCnfmUser = rgsrCnfmUser; 
	} 
	public String getRgsrCnfmUser() { 
		return this.rgsrCnfmUser; 
	} 
	public String getRgsrCnfmUserName() {
		return rgsrCnfmUserName;
	}
	public void setRgsrCnfmUserName(String rgsrCnfmUserName) {
		this.rgsrCnfmUserName = rgsrCnfmUserName;
	}
	public void setRgsrCnfmDutyDesc(String rgsrCnfmDutyDesc) { 
		this.rgsrCnfmDutyDesc = rgsrCnfmDutyDesc; 
	} 
	public String getRgsrCnfmDutyDesc() { 
		return this.rgsrCnfmDutyDesc; 
	} 
	public void setRgsrCnfmDeptCode(String rgsrCnfmDeptCode) { 
		this.rgsrCnfmDeptCode = rgsrCnfmDeptCode; 
	} 
	public String getRgsrCnfmDeptCode() { 
		return this.rgsrCnfmDeptCode; 
	} 
	public void setRgsrCnfmDeptDesc(String rgsrCnfmDeptDesc) { 
		this.rgsrCnfmDeptDesc = rgsrCnfmDeptDesc; 
	} 
	public String getRgsrCnfmDeptDesc() { 
		return this.rgsrCnfmDeptDesc; 
	} 	
	public Date getRgsrCnfmDatetime() {
		return rgsrCnfmDatetime;
	}
	public void setRgsrCnfmDatetime(Date rgsrCnfmDatetime) {
		this.rgsrCnfmDatetime = rgsrCnfmDatetime;
	}
	public void setViewCnt(long viewCnt) { 
		this.viewCnt = viewCnt; 
	} 
	public long getViewCnt() { 
		return this.viewCnt; 
	} 
	public void setAtchIndc(String atchIndc) { 
		this.atchIndc = atchIndc; 
	} 
	public String getAtchIndc() { 
		return this.atchIndc; 
	} 
	public void setBoardId(String boardId) { 
		this.boardId = boardId; 
	} 
	public String getBoardId() { 
		return this.boardId; 
	} 
	public void setTypeCode1(String typeCode1) { 
		this.typeCode1 = typeCode1; 
	} 
	public String getTypeCode1() { 
		return this.typeCode1; 
	} 
	public void setTypeCode2(String typeCode2) { 
		this.typeCode2 = typeCode2; 
	} 
	public String getTypeCode2() { 
		return this.typeCode2; 
	} 
	public void setTypeCode3(String typeCode3) { 
		this.typeCode3 = typeCode3; 
	} 
	public String getTypeCode3() { 
		return this.typeCode3; 
	} 
	public void setTypeCode4(String typeCode4) { 
		this.typeCode4 = typeCode4; 
	} 
	public String getTypeCode4() { 
		return this.typeCode4; 
	} 
	public void setChngId(String chngId) { 
		this.chngId = chngId; 
	} 
	public String getChngId() { 
		return this.chngId; 
	} 
	public void setMdfyDateTime(Date mdfyDateTime) { 
		this.mdfyDateTime = mdfyDateTime; 
	} 
	public Date getMdfyDateTime() { 
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