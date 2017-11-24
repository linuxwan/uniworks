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
 *
 */
@XmlRootElement(name = "menuHierarchyInfo")
@XmlType(propOrder = {"menuId1","menuDsplName1","menuId2","menuDsplName2","menuId3","menuDsplName3"})
@AutoProperty
@SuppressWarnings("serial")
public class MenuHierarchyInfo implements Serializable {
	private String menuId1;
	private String menuDsplName1;
	private String menuId2;
	private String menuDsplName2;
	private String menuId3;
	private String menuDsplName3;
	public String getMenuId1() {
		return menuId1;
	}
	public void setMenuId1(String menuId1) {
		this.menuId1 = menuId1;
	}
	public String getMenuDsplName1() {
		return menuDsplName1;
	}
	public void setMenuDsplName1(String menuDsplName1) {
		this.menuDsplName1 = menuDsplName1;
	}
	public String getMenuId2() {
		return menuId2;
	}
	public void setMenuId2(String menuId2) {
		this.menuId2 = menuId2;
	}
	public String getMenuDsplName2() {
		return menuDsplName2;
	}
	public void setMenuDsplName2(String menuDsplName2) {
		this.menuDsplName2 = menuDsplName2;
	}
	public String getMenuId3() {
		return menuId3;
	}
	public void setMenuId3(String menuId3) {
		this.menuId3 = menuId3;
	}
	public String getMenuDsplName3() {
		return menuDsplName3;
	}
	public void setMenuDsplName3(String menuDsplName3) {
		this.menuDsplName3 = menuDsplName3;
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
