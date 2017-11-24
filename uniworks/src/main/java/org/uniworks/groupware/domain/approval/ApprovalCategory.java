/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.domain.approval;

import java.io.Serializable;
import java.util.List;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;


/**
 * @author Chungwan Park
 * ApprovalCategory.java 2012. 1. 18.
 */
@AutoProperty 
@SuppressWarnings("serial")
public class ApprovalCategory implements Serializable {
	private String coId;	//회사 구분
	private String apprItemId;	//결재 항목 ID
	private String apprItemName;	//결재 항목 명칭
	private int seqNo;	//일련번호
	private String createDate;	//생성일자
	private String imgUrl;	//이미지 URL
	private List<ApprovalItem> apprItemList; //카테고리 속해 있는 항목리스트
	
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getApprItemId() {
		return apprItemId;
	}
	public void setApprItemId(String apprItemId) {
		this.apprItemId = apprItemId;
	}
	public String getApprItemName() {
		return apprItemName;
	}
	public void setApprItemName(String apprItemName) {
		this.apprItemName = apprItemName;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public List<ApprovalItem> getApprItemList() {
		return apprItemList;
	}
	public void setApprItemList(List<ApprovalItem> apprItemList) {
		this.apprItemList = apprItemList;
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
