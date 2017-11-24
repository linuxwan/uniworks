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
@XmlRootElement(name = "menu")
@XmlType(propOrder = {"coId","coName","stDate","finDate","prntCoClsf","prntCoCode","useIndc","bizRgsrNo","rprsntName","zipCode","coAddr","coPhonNo","coFaxNo","homePageUrl","baseOganLev","sprtLang"})
@AutoProperty 
@SuppressWarnings("serial")
public class Company implements Serializable {
	private String coId;	//회사구분
	private String coName;	//회사명칭
	private String stDate;	//시작일자
	private String finDate;	//종료일자. 디폴트 '99991231'
	private String prntCoClsf;	//모/자회사 구분, 모회사:Y, 자회사:N
	private String prntCoCode;	//모회사 코드
	private String useIndc;	//사용유무
	private String bizRgsrNo;	//사업자등록번호
	private String rprsntName;	//대표자명
	private String zipCode;	//우편번호
	private String coAddr;	//회사주소
	private String coPhonNo;	//대표전화
	private String coFaxNo;	//대표팩스번호
	private String homePageUrl;	//홈페이지
	private String baseOganLev;	//기준조직레벨
	private String sprtLang;	//지원언어
	
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getFinDate() {
		return finDate;
	}
	public void setFinDate(String finDate) {
		this.finDate = finDate;
	}
	public String getPrntCoClsf() {
		return prntCoClsf;
	}
	public void setPrntCoClsf(String prntCoClsf) {
		this.prntCoClsf = prntCoClsf;
	}
	public String getPrntCoCode() {
		return prntCoCode;
	}
	public void setPrntCoCode(String prntCoCode) {
		this.prntCoCode = prntCoCode;
	}
	public String getUseIndc() {
		return useIndc;
	}
	public void setUseIndc(String useIndc) {
		this.useIndc = useIndc;
	}
	public String getBizRgsrNo() {
		return bizRgsrNo;
	}
	public void setBizRgsrNo(String bizRgsrNo) {
		this.bizRgsrNo = bizRgsrNo;
	}
	public String getRprsntName() {
		return rprsntName;
	}
	public void setRprsntName(String rprsntName) {
		this.rprsntName = rprsntName;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCoAddr() {
		return coAddr;
	}
	public void setCoAddr(String coAddr) {
		this.coAddr = coAddr;
	}
	public String getCoPhonNo() {
		return coPhonNo;
	}
	public void setCoPhonNo(String coPhonNo) {
		this.coPhonNo = coPhonNo;
	}
	public String getCoFaxNo() {
		return coFaxNo;
	}
	public void setCoFaxNo(String coFaxNo) {
		this.coFaxNo = coFaxNo;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getBaseOganLev() {
		return baseOganLev;
	}
	public void setBaseOganLev(String baseOganLev) {
		this.baseOganLev = baseOganLev;
	}
	public String getSprtLang() {
		return sprtLang;
	}
	public void setSprtLang(String sprtLang) {
		this.sprtLang = sprtLang;
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
