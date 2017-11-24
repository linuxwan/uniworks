/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * @author Park Chungwan
 * Menu.java 2011. 7. 8.
 * Menu 정보를 담기 위한 클래스 (nw020m)
 */
@XmlRootElement(name = "menu")
@XmlType(propOrder = {"coId", "menuId", "highMenuId", "menuDsplName", "menuLevel", "menuOrd", "topUrl", "leftUrl", "bodyUrl", "cntnId", "cntnName", "menuDsplIndc", "cntnLinkIndc", "linkType", "menuDesc", "dfltMenuIndc", "dlgtMenuIndc", "myMenuSetIndc", "iconFileUrl", "crtDate", "crtId", "chngDate", "chngId"})
@AutoProperty 
@SuppressWarnings("serial")
public class Menu implements Serializable {
	private String coId;	//회사 구
    private String menuId;	//메뉴 ID
    private String highMenuId;	//상위메뉴 ID
    private String menuDsplName;	//메뉴명
    private Integer menuLevel;	//메뉴 레벨
    private Integer menuOrd;	//메뉴 순서
    private String topUrl;	//Top URL
    private String leftUrl;	//Left URL
    private String bodyUrl;	//Body URL
    private String cntnId;	//컨텐츠 ID
    private String cntnName;	//컨텐츠 명칭
    private String menuDsplIndc;	//메뉴 Display 여부
    private String cntnLinkIndc;	//컨텐츠 연결 여부
    private String linkType;	//연결 유형
    private String menuDesc;	//메뉴 상세
    private String dfltMenuIndc;	//기본 메뉴 여부
    private String dlgtMenuIndc;	//메뉴 위임 여부
    private String myMenuSetIndc;	//My Menu 설정 여부
	private String iconFileUrl;	//아이콘 파일 URL
    private Date crtDate;	//생성 일자
    private String crtId;	//생성자 ID
    private Date chngDate;	//변경 일자
    private String chngId;	//변경자 ID
    
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getHighMenuId() {
		return highMenuId;
	}
	public void setHighMenuId(String highMenuId) {
		this.highMenuId = highMenuId;
	}
	public String getMenuDsplName() {
		return menuDsplName;
	}
	public void setMenuDsplName(String menuDsplName) {
		this.menuDsplName = menuDsplName;
	}
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public Integer getMenuOrd() {
		return menuOrd;
	}
	public void setMenuOrd(Integer menuOrd) {
		this.menuOrd = menuOrd;
	}
	public String getTopUrl() {
		return topUrl;
	}
	public void setTopUrl(String topUrl) {
		this.topUrl = topUrl;
	}
	public String getLeftUrl() {
		return leftUrl;
	}
	public void setLeftUrl(String leftUrl) {
		this.leftUrl = leftUrl;
	}
	public String getBodyUrl() {
		return bodyUrl;
	}
	public void setBodyUrl(String bodyUrl) {
		this.bodyUrl = bodyUrl;
	}
	public String getCntnId() {
		return cntnId;
	}
	public void setCntnId(String cntnId) {
		this.cntnId = cntnId;
	}
	public String getCntnName() {
		return cntnName;
	}
	public void setCntnName(String cntnName) {
		this.cntnName = cntnName;
	}
	public String getMenuDsplIndc() {
		return menuDsplIndc;
	}
	public void setMenuDsplIndc(String menuDsplIndc) {
		this.menuDsplIndc = menuDsplIndc;
	}
	public String getCntnLinkIndc() {
		return cntnLinkIndc;
	}
	public void setCntnLinkIndc(String cntnLinkIndc) {
		this.cntnLinkIndc = cntnLinkIndc;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getDfltMenuIndc() {
		return dfltMenuIndc;
	}
	public void setDfltMenuIndc(String dfltMenuIndc) {
		this.dfltMenuIndc = dfltMenuIndc;
	}
	public String getDlgtMenuIndc() {
		return dlgtMenuIndc;
	}
	public void setDlgtMenuIndc(String dlgtMenuIndc) {
		this.dlgtMenuIndc = dlgtMenuIndc;
	}
	public String getMyMenuSetIndc() {
		return myMenuSetIndc;
	}
	public void setMyMenuSetIndc(String myMenuSetIndc) {
		this.myMenuSetIndc = myMenuSetIndc;
	}
	public String getIconFileUrl() {
		return iconFileUrl;
	}
	public void setIconFileUrl(String iconFileUrl) {
		this.iconFileUrl = iconFileUrl;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public String getCrtId() {
		return crtId;
	}
	public void setCrtId(String crtId) {
		this.crtId = crtId;
	}
	public Date getChngDate() {
		return chngDate;
	}
	public void setChngDate(Date chngDate) {
		this.chngDate = chngDate;
	}
	public String getChngId() {
		return chngId;
	}
	public void setChngId(String chngId) {
		this.chngId = chngId;
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
