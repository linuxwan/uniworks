/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Park Chungwan
 *
 */
public class DomainClassCreate {
	private StringBuffer strBuffer = new StringBuffer();
	private StringBuffer strXmlType = new StringBuffer();
	
	/**
	 * 생성하는 자바 코드의 머리부분을 생성한다.
	 * @param authName
	 * @param packageName
	 * @param tableName
	 */
	private void makeJavaCodeHead(String authName, String packageName, String tableName, List<ColumnProperty> columnList) {
		strBuffer.append("/** \r\n");		
		strBuffer.append(" * 박충완(Park Chungwan)이 작성한 코드 입니다. \r\n");
		strBuffer.append(" * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. \r\n");
		strBuffer.append(" * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	\r\n"); 
		strBuffer.append(" */ \r\n");
		strBuffer.append("package " + packageName + "; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import java.io.Serializable; \r\n");
		
		//패키지 import 구문 생성.
		SqlTypeConvertToJava sqlTypeToJava = new SqlTypeConvertToJava();
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList); 
		Map<String, String> pkgMap = new HashMap<String, String>();
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProperty = columnList.get(i);
			String importPackage = sqlTypeToJava.getJavaTypeName(colProperty.getTypeName());
			
			if (pkgMap.get(colProperty.getTypeName()) != null) continue;
			
			//java.lang 패키지 내의 클래스를 제외한 클래스를 import한다. 
			if (importPackage.lastIndexOf(".") > -1) {
				strBuffer.append("import " + importPackage + "; \r\n");
			}
					
			pkgMap.put(colProperty.getTypeName(), importPackage);
		}
		
		//xmlType의 컬럼 리스트 생성.
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProperty = columnList.get(i);
			//XmlType 값 설정			
			String varName = colNameToVarName.getMemberVariableName(colProperty.getColumnName());

			if (i == (columnList.size() - 1)) {	//마지막 컬럼 값일 경우
				strXmlType.append("\"" + varName + "\"");
			} else {
				strXmlType.append("\"" + varName + "\", ");
			}			
		}
		
		strBuffer.append("\r\n");
		
		strBuffer.append("import javax.xml.bind.annotation.XmlRootElement; \r\n");
		strBuffer.append("import javax.xml.bind.annotation.XmlType; \r\n");
		strBuffer.append("\r\n");
		strBuffer.append("import org.pojomatic.Pojomatic; \r\n");
		strBuffer.append("import org.pojomatic.annotations.AutoProperty; \r\n"); 
		
		strBuffer.append("/** \r\n");
		strBuffer.append(" * @author " + authName + " \r\n"); 
		strBuffer.append(" * 관련 테이블 : " +  firstUpperCaseConvert(tableName) + "\r\n");
		strBuffer.append(" */ \r\n");
		strBuffer.append("@XmlRootElement(name = \"" + tableName + "\") \r\n");
		strBuffer.append("@XmlType(propOrder = {" + strXmlType.toString() + "}) \r\n");
		strBuffer.append("@AutoProperty \r\n");
		strBuffer.append("@SuppressWarnings(\"serial\") \r\n");
	}
	
	/**
	 * 생성하는 자바 코드의 바디부분. 클래스 변수 및 메소드 생성 부분임.
	 * @param tableName
	 * @param columnList
	 */
	private void makeJavaCodeBody(String tableName, List<ColumnProperty> columnList) {
		strBuffer.append("public class " + firstUpperCaseConvert(tableName) + " implements Serializable {  \r\n");
		
		//멤버 변수 선언
		SqlTypeConvertToJava sqlTypeToJava = new SqlTypeConvertToJava();
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList); 
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProperty = columnList.get(i);			
			String varName = colNameToVarName.getMemberVariableName(colProperty.getColumnName());
			strBuffer.append("\tprivate " + sqlTypeToJava.getJavaMemberVariableType(colProperty.getTypeName()) + " " + varName + "; \r\n");			
		}
				
		strBuffer.append("\r\n");
		
		//getter, setter 메소드 생성
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProperty = columnList.get(i);
			String varName = colNameToVarName.getMemberVariableName(colProperty.getColumnName());
			//setter method 생성
			strBuffer.append("\tpublic void " + colNameToVarName.setterMethodName(colProperty.getColumnName(), "_") + "(" + sqlTypeToJava.getJavaMemberVariableType(colProperty.getTypeName()) + " " + varName + ") { \r\n");
			strBuffer.append("\t\tthis." + varName + " = " + varName + "; \r\n");
			strBuffer.append("\t} \r\n");
			
			//getter method 생성
			strBuffer.append("\tpublic " + sqlTypeToJava.getJavaMemberVariableType(colProperty.getTypeName()) + " " + colNameToVarName.getterMethodName(colProperty.getColumnName(), "_") + "() { \r\n");
			strBuffer.append("\t\treturn this." + varName + "; \r\n");
			strBuffer.append("\t} \r\n");
		}		
	}
	
	/**
	 * 생성하는 자바 코드의 Bottom 부분.
	 */
	private void makeJavaCodeTail() {
		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\tpublic boolean equals(Object o) {  \r\n");
		strBuffer.append("\t\treturn Pojomatic.equals(this, o); \r\n");
		strBuffer.append("\t} \r\n");

		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\tpublic int hashCode() { \r\n");
		strBuffer.append("\t\treturn Pojomatic.hashCode(this); \r\n");
		strBuffer.append("\t} \r\n");

		strBuffer.append("\t@Override \r\n");
		strBuffer.append("\tpublic String toString() { \r\n");
		strBuffer.append("\t\treturn Pojomatic.toString(this); \r\n");
		strBuffer.append("\t} \r\n");
		
		strBuffer.append("}");
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
	 * 도메인 클래스를 파일로 저장.
	 * @param tableName
	 * @param fileCreatePath
	 * @param encode
	 */
	private void javaCodeFileWrite(String tableName, String fileCreatePath, String encode) {
		String fileName = firstUpperCaseConvert(tableName) + ".java";
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
	 * XmlType값 - 컬럼 명칭을 ,로 구분해서 표기.
	 * @param columnList
	 * @return
	 */
	private String getXmlType() {					
		return strXmlType.toString();
	}
	
	/**
	 * 생성자
	 * @param authName 코드 작성자
	 * @param packageName 패키지명
	 * @param tableName 테이블명
	 * @param columnList 테이블 컬럼정보 목록
	 * @param fileCreatePath 파일 생성 경로
	 */
	public void createrDomainClasses(String authName, String packageName, String tableName, List<ColumnProperty> columnList, String fileCreatePath, String encode) {
		makeJavaCodeHead(authName, packageName, tableName, columnList);
		makeJavaCodeBody(tableName, columnList);
		makeJavaCodeTail();
		javaCodeFileWrite(tableName, fileCreatePath, encode);
	}
}
