/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Park Chungwan
 *
 */
public class ServiceInterfaceImplClassCreate {
	private StringBuffer strBuffer = new StringBuffer();
	
	/**
	 * Service Impl Class Head
	 * @param packageName
	 * @param domainPackageName
	 * @param domainClass
	 */
	private void makeServiceInterfaceImplJavaCodeHead(String packageName, String domainPackageName, String mapperPackageName, String servicePackageName, String domainClass) {
		strBuffer.append("/** \r\n");		
		strBuffer.append(" * 박충완(Park Chungwan)이 작성한 코드 입니다. \r\n");
		strBuffer.append(" * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. \r\n");
		strBuffer.append(" * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	\r\n"); 
		strBuffer.append(" */ \r\n");
		strBuffer.append("package " + packageName + "; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import java.util.List; \r\n");
		strBuffer.append("import java.util.Map; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import org.springframework.beans.factory.annotation.Autowired; \r\n");
		strBuffer.append("import org.springframework.stereotype.Service; \r\n");
		strBuffer.append("import org.springframework.transaction.annotation.Propagation; \r\n");
		strBuffer.append("import org.springframework.transaction.annotation.Transactional; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import " + domainPackageName + "." + firstUpperCaseConvert(domainClass) + "; \r\n");
		strBuffer.append("import " + mapperPackageName + "." + firstUpperCaseConvert(domainClass) + "Mapper; \r\n");
		strBuffer.append("import " + servicePackageName + "." + firstUpperCaseConvert(domainClass) + "Service; \r\n");
		strBuffer.append("\r\n");
	}
	
	/**
	 * Service Impl Class Body
	 * @param domainClass
	 */
	private void makeServiceInterfaceImplJavaCodeBody(String domainClass) {
		strBuffer.append("@Service \r\n");
		strBuffer.append("@Transactional(readOnly = true) \r\n");
		strBuffer.append("public class " + firstUpperCaseConvert(domainClass) + "ServiceImpl implements " + firstUpperCaseConvert(domainClass) + "Service { \r\n");
		strBuffer.append("\t@Autowired " + firstUpperCaseConvert(domainClass) + "Mapper " + domainClass + "Mapper; \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 목록을 조회한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\tpublic List<" + firstUpperCaseConvert(domainClass) + "> get" + firstUpperCaseConvert(domainClass) + "List(Map<String, Object> map) { \r\n");
		strBuffer.append("\t\treturn " + domainClass + "Mapper.select(map); \r\n");
		strBuffer.append("\t} \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 조회한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\tpublic " + firstUpperCaseConvert(domainClass) + " get" + firstUpperCaseConvert(domainClass) + "(Map<String, Object> map) { \r\n");
		strBuffer.append("\t\treturn " + domainClass + "Mapper.selectByPrimaryKey(map); \r\n");
		strBuffer.append("\t} \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 등록한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\t@Transactional(readOnly = false, propagation = Propagation.REQUIRED) \r\n");
		strBuffer.append("\tpublic int" + " add" + firstUpperCaseConvert(domainClass) + "(" + firstUpperCaseConvert(domainClass) +  " " + domainClass + ") { \r\n");
		strBuffer.append("\t\treturn " + domainClass + "Mapper.insert(" + domainClass + "); \r\n");
		strBuffer.append("\t} \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 수정한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\t@Transactional(readOnly = false, propagation = Propagation.REQUIRED) \r\n");
		strBuffer.append("\tpublic int" + " update" + firstUpperCaseConvert(domainClass) + "(" + firstUpperCaseConvert(domainClass) + " " + domainClass + ") { \r\n");
		strBuffer.append("\t\treturn " + domainClass + "Mapper.updateByPrimaryKey(" + domainClass + "); \r\n");
		strBuffer.append("\t} \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 삭제한다. \r\n");
		strBuffer.append("\t * \r\n"); 
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\t@Transactional(readOnly = false, propagation = Propagation.REQUIRED) \r\n");
		strBuffer.append("\tpublic int" + " delete" + firstUpperCaseConvert(domainClass) + "(Map<String, Object> map) { \r\n");
		strBuffer.append("\t\treturn " + domainClass + "Mapper.deleteByPrimaryKey(map); \r\n");
		strBuffer.append("\t} \r\n");
	}
	
	/**
	 * Service Impl Class Tail
	 */
	private void makeServiceInterfaceImplJavaCodeTail() {
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
	 * Service Interface Impl Class를 파일로 저장.
	 * @param tableName
	 * @param fileCreatePath
	 * @param encode
	 */
	private void mapperInterfaceImplFileWrite(String tableName, String fileCreatePath, String encode) {
		String fileName = firstUpperCaseConvert(tableName) + "ServiceImpl.java";
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
	 * Service Interface Impl 파일을 생성한다.
	 * @param packageName
	 * @param domainPackageName
	 * @param domainClass
	 * @param fileCreatePath
	 * @param encode
	 */
	public void createrServiceInterface(String packageName, String domainPackageName, String mapperPackageName, String servicePackageName, String domainClass, String fileCreatePath, String encode) {
		makeServiceInterfaceImplJavaCodeHead(packageName, domainPackageName, mapperPackageName, servicePackageName, domainClass);
		makeServiceInterfaceImplJavaCodeBody(domainClass);
		makeServiceInterfaceImplJavaCodeTail();
		mapperInterfaceImplFileWrite(domainClass, fileCreatePath, encode);
	}	
}
