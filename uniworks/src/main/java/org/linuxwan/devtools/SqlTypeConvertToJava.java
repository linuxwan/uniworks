/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Park Chungwan
 * Database Data Type을 Java Data Type으로 변환.
 */
public class SqlTypeConvertToJava {
	private Map<String, String> map = new HashMap<String, String>();
	
	public SqlTypeConvertToJava() {
		map.put("CHAR", "String");
		map.put("VARCHAR", "String");
		map.put("VARCHAR2", "String");
		map.put("LONGVARCHAR", "String");
		map.put("NUMBER", "java.math.BigDecimal");
		map.put("NUMERIC", "java.math.BigDecimal");
		map.put("DECIMAL", "java.math.BigDecimal");
		map.put("BIT", "boolean");	//length 1이면 Boolean, 1보다 크면 byte[] 
		map.put("TINYINT", "byte");
		map.put("SMALLINT", "short");
		map.put("INTEGER", "int");	
		map.put("INT", "int");
		map.put("INT UNSIGNED", "long");
		map.put("BIGINT", "long");
		map.put("BIGINT UNSIGNED", "long");
		map.put("REAL", "float");
		map.put("FLOAT", "double");
		map.put("DOUBLE", "double");
		map.put("BINARY", "byte[]");
		map.put("VARBINARY", "byte[]");
		map.put("LONGVARBINARY", "byte[]");
		map.put("MEDIUMTEXT", "String");
		map.put("LONGTEXT", "String");
		map.put("DATE", "java.sql.Date");
		map.put("TIME", "java.sql.Time");
		map.put("TIMESTAMP", "java.sql.Timestamp");
		map.put("DATETIME", "java.sql.Timestamp");
		map.put("CLOB", "java.sql.Clob");
		map.put("BLOB", "java.sql.Blob");
		map.put("MEDIUMBLOB", "byte[]");		
		map.put("LONGBLOB", "byte[]");
		map.put("ARRAY", "java.sql.Array");
		map.put("REF", "java.sql.Ref");
		map.put("STRUCT", "java.sql.Struct");
	}
	
	/**
	 * 입력 받은 SQL DataType을 Java DataType으로 반환.
	 * Java Code 생성 시에 import 구문에서 사용.
	 * @param sqlType
	 * @return
	 */
	public String getJavaTypeName(String sqlType) {
		String javaType = map.get(sqlType);
		
		return javaType;
	}
	
	/**
	 * 입력받은 SQL DataType을 JDBC DataType으로 반환.
	 * @param sqlType
	 * @return
	 */
	public String getJdbcTypeName(String sqlType) {
		String javaType = map.get(sqlType);
		
		if (sqlType.equalsIgnoreCase("DATETIME")) {
			javaType = "TIMESTAMP";
		}
		
		return javaType;
	}
	
	/**
	 * Java Code 생성 시에 변수 타입으로 사용.
	 * @param sqlType
	 * @return
	 */
	public String getJavaMemberVariableType(String sqlType) {
		String varName = map.get(sqlType);				
		int pos = varName.lastIndexOf(".");
		//java.lang 패키지 내의 클래스가 아닌 경우 패키지 명을 포함한 데이터 타입을 가져옴.
		if (pos > 0) {	//java에서 제공하는 기본 타입이 아닐 경우
			varName = varName.substring(pos+1);
		}
		
		return varName;
	}
}
