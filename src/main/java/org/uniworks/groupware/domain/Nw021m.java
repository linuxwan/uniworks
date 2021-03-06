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
 * 관련 테이블 : Nw021m
 */ 
@XmlRootElement(name = "nw021m") 
@XmlType(propOrder = {"menuId", "coId", "locale", "menuDsplName"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw021m implements Serializable {  
	private String menuId; 
	private String coId; 
	private String locale; 
	private String menuDsplName; 

	public void setMenuId(String menuId) { 
		this.menuId = menuId; 
	} 
	public String getMenuId() { 
		return this.menuId; 
	} 
	public void setCoId(String coId) { 
		this.coId = coId; 
	} 
	public String getCoId() { 
		return this.coId; 
	} 
	public void setLocale(String locale) { 
		this.locale = locale; 
	} 
	public String getLocale() { 
		return this.locale; 
	} 
	public void setMenuDsplName(String menuDsplName) { 
		this.menuDsplName = menuDsplName; 
	} 
	public String getMenuDsplName() { 
		return this.menuDsplName; 
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