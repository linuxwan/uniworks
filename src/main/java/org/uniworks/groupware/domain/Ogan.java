/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.pojomatic.annotations.AutoProperty;

/**
 * @author Park Chungwan
 *
 */
@XmlRootElement(name = "ogan")
@XmlType(propOrder = {"coId","oganCode","oganLev","oganEstbDate","oganName","rescKey","oganClsDate","highOganCode","highOganLev","oganType","oganDesc"})
@AutoProperty 
@SuppressWarnings("serial")
public class Ogan implements Serializable {
	private String coId;
	private String oganCode;
	private String oganLev;
	private String oganEstbDate;
	private String oganName;
	private String rescKey;
	private String oganClsDate;
	private String highOganCode;
	private String highOganLev;
	private String oganType;
	private String oganDesc;
	
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getOganCode() {
		return oganCode;
	}
	public void setOganCode(String oganCode) {
		this.oganCode = oganCode;
	}
	public String getOganLev() {
		return oganLev;
	}
	public void setOganLev(String oganLev) {
		this.oganLev = oganLev;
	}
	public String getOganEstbDate() {
		return oganEstbDate;
	}
	public void setOganEstbDate(String oganEstbDate) {
		this.oganEstbDate = oganEstbDate;
	}
	public String getOganName() {
		return oganName;
	}
	public void setOganName(String oganName) {
		this.oganName = oganName;
	}
	public String getRescKey() {
		return rescKey;
	}
	public void setRescKey(String rescKey) {
		this.rescKey = rescKey;
	}
	public String getOganClsDate() {
		return oganClsDate;
	}
	public void setOganClsDate(String oganClsDate) {
		this.oganClsDate = oganClsDate;
	}
	public String getHighOganCode() {
		return highOganCode;
	}
	public void setHighOganCode(String highOganCode) {
		this.highOganCode = highOganCode;
	}
	public String getHighOganLev() {
		return highOganLev;
	}
	public void setHighOganLev(String highOganLev) {
		this.highOganLev = highOganLev;
	}
	public String getOganType() {
		return oganType;
	}
	public void setOganType(String oganType) {
		this.oganType = oganType;
	}
	public String getOganDesc() {
		return oganDesc;
	}
	public void setOganDesc(String oganDesc) {
		this.oganDesc = oganDesc;
	}
}
