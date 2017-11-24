/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */

/**
 * @author Park Chungwan
 * 데이터베이스 테이블의 컬럼명을 자바의 멤버변수 명으로 변환.
 */
public class ColumnNameToMemberVariableName {
	Map<String, String> varNames = new HashMap<String, String>();
	Map<String, String> varTypes = new HashMap<String, String>();
	
	public ColumnNameToMemberVariableName(List<ColumnProperty> list) {
		setColumnVarNameAndVarType(list);
	}
	
	/**
	 * ColumnProperty의 컬럼 명칭을 자바 클래스 변수명으로 변환해서 해쉬맵에 저장하고,
	 * ColumnProperty의 컬럼 타입을 varTypes 해쉬맵에 저장.
	 * @param map
	 */
	private void setColumnVarNameAndVarType(List<ColumnProperty> list) {
		for (int i = 0; i < list.size(); i++) {
			ColumnProperty colProp = list.get(i);
			String memberVariableName = convertDBColumnNameToMemberVariableName(colProp.getColumnName(), "_");
			
			varNames.put(colProp.getColumnName(), memberVariableName);
			varTypes.put(colProp.getColumnName(), colProp.getTypeName());
		}
	}
	
	/**
	 * Database 상의 컬럼 명칭을 Java 변수명으로 변환.
	 * @param columnName 필드 명칭
	 * @param delimiter 단어 구분자. 주로 _를 많이 사용함.
	 * @return
	 */
	private String convertDBColumnNameToMemberVariableName(String columnName, String delimiter) {
		if (columnName == null) return null;
		
		StringTokenizer st = new StringTokenizer(columnName, delimiter);
		String[] names = new String[st.countTokens()];
		
		for (int i = 0; i < names.length; i++) {
			names[i] = st.nextToken().trim();
		}

		String memberVariableName = "";
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {	//컬럼의 첫번째 문자를 모두 소문자로 변환
				String firstName = names[0].toLowerCase();
				memberVariableName += firstName;
			} else {	//첫글자만 대문자로 나머지는 모두 소문자로 변환 
				String name = names[i].toLowerCase();
				memberVariableName += firstUpperCaseConvert(name);
			}
		}
		
		return memberVariableName;
	}
	
	/**
	 * Database의 컬럼 명칭을 Java get Method로 변환
	 * @param columnName
	 * @param delimiter
	 * @return
	 */
	public String getterMethodName(String columnName, String delimiter) {
		if (columnName == null) return null;
		
		StringTokenizer st = new StringTokenizer(columnName, delimiter);
		String[] names = new String[st.countTokens()];
		
		for (int i = 0; i < names.length; i++) {
			names[i] = st.nextToken().trim();
		}
		
		String methodName = "get";
		for (int i = 0; i < names.length; i++) {
			String name = names[i].toLowerCase();
			methodName += firstUpperCaseConvert(name);
		}
		
		return methodName;
	}
	
	/**
	 * Database의 컬럼 명칭을 Java set Method로 변환
	 * @param columnName
	 * @param delimiter
	 * @return
	 */
	public String setterMethodName(String columnName, String delimiter) {
		if (columnName == null) return null;
		
		StringTokenizer st = new StringTokenizer(columnName, delimiter);
		String[] names = new String[st.countTokens()];
		
		for (int i = 0; i < names.length; i++) {
			names[i] = st.nextToken().trim();
		}
		
		String methodName = "set";
		for (int i = 0; i < names.length; i++) {
			String name = names[i].toLowerCase();
			methodName += firstUpperCaseConvert(name);
		}
		
		return methodName;
	}
	
	/**
	 * Member Variable 명칭으로 변경. 첫글자는 소문자로 시작하고 다음 단어의 첫 글자는 대문자로 표기
	 * @param columnName
	 * @return
	 */
	public String getMemberVariableName(String columnName) {
		String varName = this.varNames.get(columnName);
		return varName;
	}
	
	/**
	 * Column의 데이터 타입을 반환.
	 * @param columnName
	 * @return
	 */
	public String getMemberJavaVariableType(String columnName) {
		String varType = this.varTypes.get(columnName);
			
		return varType;
	}
	
	/**
	 * Column의 데이터 타입을 반환.
	 * @param columnName
	 * @return
	 */
	public String getMemberJdbcType(String columnName) {
		String varType = this.varTypes.get(columnName);
	
		if (varType.equalsIgnoreCase("DATETIME")) {
			varType = "TIMESTAMP";
		} else if (varType.equalsIgnoreCase("INT")) {
			varType = "INTEGER";
		} else if (varType.equalsIgnoreCase("LONGTEXT")) {
			varType = "VARCHAR";
		} else if (varType.equalsIgnoreCase("INT UNSIGNED")) {
			varType = "INTEGER";
		} else if (varType.equalsIgnoreCase("BIGINT UNSIGNED")) {
			varType = "BIGINT";
		}
		
		return varType;
	}
	
	/**
	 * 입력된 문자의 첫글자만 대문자로 나머지는 소문자로 변환.
	 * @param name
	 * @return
	 */
	private String firstUpperCaseConvert(String name) {
		String str = "";
		str = name.substring(0,1).toUpperCase() + name.substring(1, name.length()).toLowerCase();
		return str;
	}		
}
