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
public class MapperClassCreate {
	private StringBuffer strBuffer = new StringBuffer();
	
	/**
	 * Mapper Class Head 부분
	 * @param packageName
	 * @param domainPackageName
	 */
	private void makeMapperJavaCodeHead(String authName, String tableName, String packageName, String domainPackageName) {
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
		strBuffer.append("import " + domainPackageName + "." + firstUpperCaseConvert(tableName) + "; \r\n");
		strBuffer.append("\r\n");
		
		strBuffer.append("\r\n");
		strBuffer.append("/** \r\n");
		strBuffer.append(" * @author " + authName + " \r\n"); 
		strBuffer.append(" * 관련 테이블 : " +  firstUpperCaseConvert(tableName) + "\r\n");
		strBuffer.append(" */ \r\n");
	}
	
	/**
	 * Mapper Class Body 브븐
	 * @param tableName
	 */
	private void makeMapperJavaCodeBody(String tableName) {
		strBuffer.append("public interface " + firstUpperCaseConvert(tableName) + "Mapper { \r\n");
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 목록 가져오기 \r\n");
		strBuffer.append("\t * @param map \r\n");
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tList<" + firstUpperCaseConvert(tableName) + "> select(Map<String, Object> map); \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 정보 가져오기 \r\n");
		strBuffer.append("\t * @param map \r\n");
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\t" + firstUpperCaseConvert(tableName) + " selectByPrimaryKey(Map<String, Object> map); \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 등록한다 \r\n");
		strBuffer.append("\t * @param " + tableName + " \r\n");
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint insert(" + firstUpperCaseConvert(tableName) + " " + tableName + "); \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 수정한다 \r\n");
		strBuffer.append("\t * @param " + tableName + " \r\n");
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint updateByPrimaryKey(" + firstUpperCaseConvert(tableName) + " " + tableName + "); \r\n");
		
		strBuffer.append("\t/** \r\n");
		strBuffer.append("\t * 삭제한다 \r\n");
		strBuffer.append("\t * @param map \r\n");
		strBuffer.append("\t * @return \r\n");
		strBuffer.append("\t */ \r\n");
		strBuffer.append("\tint deleteByPrimaryKey(Map<String, Object> map); \r\n");
	}
	
	private void makeMapperJavaCodeTail() {
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
	 * Mapper Class를 파일로 저장.
	 * @param tableName
	 * @param fileCreatePath
	 * @param encode
	 */
	private void mapperXmlFileWrite(String tableName, String fileCreatePath, String encode) {
		String fileName = firstUpperCaseConvert(tableName) + "Mapper.java";
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
	 * Mapper Class 파일을 생성
	 * @param authName
	 * @param tableName
	 * @param packageName
	 * @param domainPackageName
	 * @param fileCreatePath
	 * @param enCode
	 */
	public void createrMapperClassFiles(String authName, String tableName, String packageName, String domainPackageName, String fileCreatePath, String enCode) {
		makeMapperJavaCodeHead(authName, tableName, packageName, domainPackageName);
		makeMapperJavaCodeBody(tableName);
		makeMapperJavaCodeTail();
		mapperXmlFileWrite(tableName, fileCreatePath, enCode);
	}
}
