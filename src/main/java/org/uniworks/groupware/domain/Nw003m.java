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
 * 관련 테이블 : Nw003m
 */ 
@XmlRootElement(name = "nw003m") 
@XmlType(propOrder = {"boardId", "coId", "crtDate", "clsDate"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw003m implements Serializable {  
	private String boardId; 
	private String coId; 
	private Date crtDate; 
	private Date clsDate; 

	public void setBoardId(String boardId) { 
		this.boardId = boardId; 
	} 
	public String getBoardId() { 
		return this.boardId; 
	} 
	public void setCoId(String coId) { 
		this.coId = coId; 
	} 
	public String getCoId() { 
		return this.coId; 
	} 
	public void setCrtDate(Date crtDate) { 
		this.crtDate = crtDate; 
	} 
	public Date getCrtDate() { 
		return this.crtDate; 
	} 
	public void setClsDate(Date clsDate) { 
		this.clsDate = clsDate; 
	} 
	public Date getClsDate() { 
		return this.clsDate; 
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