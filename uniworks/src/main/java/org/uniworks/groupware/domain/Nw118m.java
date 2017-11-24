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
import org.springframework.web.multipart.commons.CommonsMultipartFile; 
/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw118m
 */ 
@XmlRootElement(name = "nw118m") 
@XmlType(propOrder = {"fileId", "attchFileName", "attchFileSysName", "fileExt", "fileSize", "tempIndc", "fileData"}) 
@AutoProperty 
@SuppressWarnings("serial") 
public class Nw118m implements Serializable {  
	private String fileId; 
	private String attchFileName; 
	private String attchFileSysName; 
	private String fileExt; 
	private int fileSize; 
	private String tempIndc; 
	private CommonsMultipartFile fileData;	//첨부파일
	
	public void setFileId(String fileId) { 
		this.fileId = fileId; 
	} 
	public String getFileId() { 
		return this.fileId; 
	} 
	public void setAttchFileName(String attchFileName) { 
		this.attchFileName = attchFileName; 
	} 
	public String getAttchFileName() { 
		return this.attchFileName; 
	} 
	public void setAttchFileSysName(String attchFileSysName) { 
		this.attchFileSysName = attchFileSysName; 
	} 
	public String getAttchFileSysName() { 
		return this.attchFileSysName; 
	} 
	public void setFileExt(String fileExt) { 
		this.fileExt = fileExt; 
	} 
	public String getFileExt() { 
		return this.fileExt; 
	} 
	public void setFileSize(int fileSize) { 
		this.fileSize = fileSize; 
	} 
	public int getFileSize() { 
		return this.fileSize; 
	} 
	public void setTempIndc(String tempIndc) { 
		this.tempIndc = tempIndc; 
	} 
	public String getTempIndc() { 
		return this.tempIndc; 
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
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}