/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common;

import java.io.Serializable;

/**
 * @author Park Chungwan
 *
 */
public class HiddenField implements Serializable {	
	private static final long serialVersionUID = 1L;
	private String menuId;
	private int menuLevel = 0;
	private String headMenuId;
	private String cntnId;
	private String apprMstId;
	private String dcmtRgsrNo;
	private String bodyUrl;
	private int crntPage = 0;
	private String apprStus;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getHeadMenuId() {
		return headMenuId;
	}
	public void setHeadMenuId(String headMenuId) {
		this.headMenuId = headMenuId;
	}
	public String getCntnId() {
		return cntnId;
	}
	public void setCntnId(String cntnId) {
		this.cntnId = cntnId;
	}
	public String getApprMstId() {
		return apprMstId;
	}
	public void setApprMstId(String apprMstId) {
		this.apprMstId = apprMstId;
	}
	public String getDcmtRgsrNo() {
		return dcmtRgsrNo;
	}
	public void setDcmtRgsrNo(String dcmtRgsrNo) {
		this.dcmtRgsrNo = dcmtRgsrNo;
	}
	public String getBodyUrl() {
		return bodyUrl;
	}
	public void setBodyUrl(String bodyUrl) {
		this.bodyUrl = bodyUrl;
	}
	public int getCrntPage() {
		return crntPage;
	}
	public void setCrntPage(int crntPage) {
		this.crntPage = crntPage;
	}
	public String getApprStus() {
		return apprStus;
	}
	public void setApprStus(String apprStus) {
		this.apprStus = apprStus;
	}
}
