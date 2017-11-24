/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Park Chungwan
 *
 */
public class ServiceInterfaceCreate {
	private StringBuffer strBuffer = new StringBuffer();
	
	/**
	 * Service Interface Head
	 * @param packageName
	 * @param domainPackageName
	 * @param domainClass
	 */
	private void makeServiceInterfaceJavaCodeHead(String packageName, String domainPackageName, String domainClass) {
		strBuffer.append("/** \r\n");		
		strBuffer.append(" * 박충완(Park Chungwan)이 작성한 코드 입니다. \r\n");
		strBuffer.append(" * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. \r\n");
		strBuffer.append(" * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	\r\n"); 
		strBuffer.append(" */ \r\n");
		strBuffer.append("package " + packageName + "; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import java.util.List; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import " + domainPackageName + "." + firstUpperCaseConvert(domainClass) + "; \r\n");
		strBuffer.append("\r\n");
	}
	
	/**
	 * Service Interface Body
	 * @param domainClass
	 */
	private void makeServiceInterfaceJavaCodeBody(String domainClass) {
		strBuffer.append("public interface " + firstUpperCaseConvert(domainClass) + "Service { \r\n");
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 목록을 조회한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tList<" + firstUpperCaseConvert(domainClass) + "> get" + firstUpperCaseConvert(domainClass) + "List(java.util.Map<String,Object> map); \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 조회한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t" + firstUpperCaseConvert(domainClass) + " get" + firstUpperCaseConvert(domainClass) + "(java.util.Map<String,Object> map); \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 등록한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint" + " add" + firstUpperCaseConvert(domainClass) + "(" + firstUpperCaseConvert(domainClass) +  " " + domainClass + "); \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 수정한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint" + " update" + firstUpperCaseConvert(domainClass) + "(" + firstUpperCaseConvert(domainClass) + " " + domainClass + "); \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 삭제한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint" + " delete" + firstUpperCaseConvert(domainClass) + "(java.util.Map<String,Object> map); \r\n");
	}
	
	/**
	 * Service Interface Tail
	 */
	private void makeServiceInterfaceJavaCodeTail() {
		strBuffer.append("} \r\n");
	}
	
	/**
	 * 첫번째 문자를 대문자로 표기하고 나머지 문자는 모두 소문자로 표기
	 * @param name
	 * @return
	 */
	private String firstUpperCaseConvert(String name) {
		String str = "";
		str = name.substring(0,1).toUpperCase() + name.substring(1, name.length()).toLowerCase();
		return str;
	}
	
	/**
	 * Service Interface Class를 파일로 저장.
	 * @param tableName
	 * @param fileCreatePath
	 * @param encode
	 */
	private void mapperInterfaceFileWrite(String tableName, String fileCreatePath, String encode) {
		String fileName = firstUpperCaseConvert(tableName) + "Service.java";
		String fullPathFileName = fileCreatePath + File.separator + fileName;
		try {
			File dest = new File(fileCreatePath);
			if (!dest.exists()) dest.mkdirs();
			
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fullPathFileName), encode);
			out.write(strBuffer.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Service Interface 파일을 생성한다.
	 * @param packageName
	 * @param domainPackageName
	 * @param domainClass
	 * @param fileCreatePath
	 * @param encode
	 */
	public void createrServiceInterface(String packageName, String domainPackageName, String domainClass, String fileCreatePath, String encode) {
		makeServiceInterfaceJavaCodeHead(packageName, domainPackageName, domainClass);
		makeServiceInterfaceJavaCodeBody(domainClass);
		makeServiceInterfaceJavaCodeTail();
		mapperInterfaceFileWrite(domainClass, fileCreatePath, encode);
	}	
}
