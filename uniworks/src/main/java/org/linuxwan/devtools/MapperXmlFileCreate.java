/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @author Park Chungwan
 *
 */
public class MapperXmlFileCreate {
	private StringBuffer strBuffer = new StringBuffer();
	
	/**
	 * Mapper 관련 Xml Header 부분 설정
	 * @param packageName
	 * @param tableName
	 */
	private void makeMapperXmlFileHead(String packageName, String tableName) {
		strBuffer.append("<?xml version=\"" + "1.0" + "\" encoding=\"" + "UTF-8" + "\"?> \r\n");
		strBuffer.append("<!DOCTYPE mapper PUBLIC " + "\"-//mybatis.org//DTD Mapper 3.0//EN\" " + "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"" + "> \r\n");
		strBuffer.append("<mapper namespace=\"" + packageName + "." + firstUpperCaseConvert(tableName) + "Mapper" + "\"> \r\n");
	}
	
	/**
	 * ResultMap 부분 생성.
	 * @param domainPackageName
	 * @param columnProperty
	 */
	private void makeResultMap(String tableName, String domainPackageName, List<ColumnProperty> columnList) {
		strBuffer.append("\t<resultMap id=\"" + "BaseResultMap" + "\" type=\"" + domainPackageName + "." + firstUpperCaseConvert(tableName) + "\"> \r\n");
		
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList); 	
		for (int i = 0; i < columnList.size(); i++) {
			//<result column="CO_ID" jdbcType="VARCHAR" property="coId" />
			ColumnProperty colProperty = columnList.get(i);
			
			String varName = colNameToVarName.getMemberVariableName(colProperty.getColumnName());
			strBuffer.append("\t\t<result column=\"" + colProperty.getColumnName() + "\" " + "jdbcType=\"" + colNameToVarName.getMemberJdbcType(colProperty.getColumnName()) + "\" " + "property=\"" + varName + "\" /> \r\n");
		}
		strBuffer.append("\t</resultMap> \r\n");
		strBuffer.append("\r\n");
	}
	
	/**
	 * 목록을 가져오는 쿼리 생성 - where 조건문은 생성하지 않음. 추후에 추가 필요.
	 * @param tableName
	 * @param columnList
	 */
	private void makeSelect(String tableName, List<ColumnProperty> columnList) {
		strBuffer.append("\t<!-- 목록 조회 --> \r\n");
		strBuffer.append("\t<select id=\"" + "select\" " + "parameterType=\"" + "java.util.Map\" " + "resultMap=\"" + "BaseResultMap\"> \r\n");
		strBuffer.append("\t\tselect  " + getSelectColumns(columnList) + "\r\n");
		strBuffer.append("\t\tfrom   " + tableName + "\r\n");
		strBuffer.append("\t</select> \r\n \r\n");
	}
	
	/**
	 * PrimaryKey로 1건의 데이터를 가져오는 쿼리 생성
	 * @param tableName
	 * @param columnList
	 * @param pk
	 */
	private void makeSelectByPrimaryKey(String tableName, List<ColumnProperty> columnList, List<String> pk) {
		strBuffer.append("\t<!-- 상세 조회 --> \r\n");
		strBuffer.append("\t<select id=\"" + "selectByPrimaryKey\" " + "parameterType=\"" + "java.util.Map\" " + "resultMap=\"" + "BaseResultMap\"> \r\n");
		strBuffer.append("\t\tselect  " + getSelectColumns(columnList) + "\r\n");
		strBuffer.append("\t\tfrom   " + tableName + "\r\n");
		strBuffer.append("\t\twhere  ");
		
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList); 
		for (int i = 0; i < pk.size(); i++) {
			if (i == 0) {
				strBuffer.append(pk.get(i) + "\t=\t#{" + colNameToVarName.getMemberVariableName(pk.get(i)) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(pk.get(i)) + "} \r\n");
			} else {
				strBuffer.append("\t\t  and  " + pk.get(i) + "\t=\t#{" + colNameToVarName.getMemberVariableName(pk.get(i)) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(pk.get(i)) + "} \r\n");				
			}
		}
		
		strBuffer.append("\t</select> \r\n \r\n"); 
	}
	
	/**
	 * Insert 절 생성
	 * @param tableName
	 * @param domainPackageName
	 * @param columnList
	 */
	private void makeInsert(String tableName, String domainPackageName, List<ColumnProperty> columnList) {
		strBuffer.append("\t<!-- 등록 --> \r\n");
		strBuffer.append("\t<insert id=\"" + "insert\" " + "parameterType=\"" + domainPackageName + "." + firstUpperCaseConvert(tableName) + "\"> \r\n");
		strBuffer.append("\t\tinsert	into " + tableName + "(" + getSelectColumns(columnList) + ")" + " \r\n");
		
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList); 
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProp = columnList.get(i);
			if (i == 0) {
				strBuffer.append("\t\tvalues (#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "}");
			} else {
				if (i == (columnList.size() - 1)) {
					if (i > 0 && (i % 6) == 0) {
						strBuffer.append(",#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "})");
					} else {
						strBuffer.append(",#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "})");
					}
					strBuffer.append(" \r\n");
				} else {
					if (i > 0 && (i % 6) == 0) {
						strBuffer.append(",#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "}");
						strBuffer.append(" \r\n");
					} else {
						if (i > 5 && (i % 6) == 1) {
							strBuffer.append("\t\t\t,#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "}");
						} else {
							strBuffer.append(",#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "}");
						}
					}
				}
			}			
		}
		
		strBuffer.append("\t</insert> \r\n \r\n");
	}
	
	/**
	 * Update문 생성
	 * @param tableName 테이블명
	 * @param domainPackageName 도메인 패키지명
	 * @param columnList 컬럼 목록
	 * @param pk PK리스트
	 */
	private void makeUpdateByPrimaryKey(String tableName, String domainPackageName, List<ColumnProperty> columnList, List<String> pk) {
		strBuffer.append("\t<!-- 수정 --> \r\n");
		strBuffer.append("\t<update id=\"" + "updateByPrimaryKey\" parameterType=\"" + domainPackageName + "." + firstUpperCaseConvert(tableName) + "\"> \r\n");
		strBuffer.append("\t\tupdate	" + tableName + " \r\n");
		strBuffer.append("\t\t<trim prefix=\"" + "SET\"" + " suffixOverrides=\"" + ",\"" +  "> \r\n");
		
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList);
		for (int i = 0; i < columnList.size(); i++) {
			boolean skipChk = false;
			ColumnProperty colProp = columnList.get(i);
			for (int j = 0; j < pk.size(); j++) {
				String strPk = pk.get(j);
				if (colProp.getColumnName().equals(strPk)) skipChk = true;
			}
			
			if (skipChk) continue;
			strBuffer.append("\t\t\t<if test=\"" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + " != null\">");
			strBuffer.append(colProp.getColumnName() + "= #{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "},</if> \r\n");
		}
		strBuffer.append("\t\t</trim> \r\n");
		
		for (int i = 0; i < pk.size(); i++) {
			ColumnProperty colProp = columnList.get(i);
			
			if (i == 0) {
				strBuffer.append("\t\twhere	" + colProp.getColumnName() + "\t=\t" + "#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "} \r\n");
			} else {
				strBuffer.append("\t\t  and	" + colProp.getColumnName() + "\t=\t" + "#{" + colNameToVarName.getMemberVariableName(colProp.getColumnName()) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(colProp.getColumnName()) + "} \r\n");
			}
		}
				
		strBuffer.append("\t</update> \r\n \r\n");
	}
	
	private void makeDeleteByPrimaryKey(String tableName, List<ColumnProperty> columnList, List<String> pk) {
		strBuffer.append("\t<!-- 삭제 --> \r\n");
		strBuffer.append("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.util.Map\"> \r\n");
		strBuffer.append("\t\tdelete	from " + tableName + " \r\n");
		strBuffer.append("\t\twhere	");
		
		ColumnNameToMemberVariableName colNameToVarName = new ColumnNameToMemberVariableName(columnList);
		for (int i = 0; i < pk.size(); i++) {
			String strPk = pk.get(i);
			
			if (i == 0) {
				strBuffer.append(strPk + "\t=\t#{" + colNameToVarName.getMemberVariableName(strPk) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(strPk) + "} \r\n");
			} else {
				strBuffer.append("\t\t  and	" + strPk + "\t=\t#{" + colNameToVarName.getMemberVariableName(strPk) + ",jdbcType=" + colNameToVarName.getMemberJdbcType(strPk) + "} \r\n");
			}
		}
		
		strBuffer.append("\t</delete> \r\n");
	}
	
	private void makeMapperXmlFileTail() {
		strBuffer.append("</mapper>");
	}
	
	/**
	 * select, insert 절에서 사용할 필드 문자열을 반환한다.
	 * @param columnList
	 * @return
	 */
	private String getSelectColumns(List<ColumnProperty> columnList) {
		StringBuffer strBuf = new StringBuffer();
		
		for (int i = 0; i < columnList.size(); i++) {
			ColumnProperty colProperty = columnList.get(i);
			
			if (i == (columnList.size() - 1)) {
				if (i > 7 && (i % 8) == 1) {
					strBuf.append("\r\n\t\t\t\t" + colProperty.getColumnName());
				} else {
					strBuf.append(colProperty.getColumnName());
				}
			} else {
				if (i > 7 && (i % 8) == 1) {
					strBuf.append("\r\n\t\t\t\t" + colProperty.getColumnName() + ", ");
				} else {
					strBuf.append(colProperty.getColumnName() + ", ");
				}
			}
		}
		
		return strBuf.toString();
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
	 * Mapper Xml을 파일로 저장.
	 * @param tableName
	 * @param fileCreatePath
	 * @param encode
	 */
	private void mapperXmlFileWrite(String tableName, String fileCreatePath, String encode) {
		String fileName = firstUpperCaseConvert(tableName) + "Mapper.xml";
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
	 * MyBatis용 Mapper Xml File을 생성한다.
	 * @param packageName  Mapper Class의 패키지명
	 * @param tableName 테이블 명 
	 * @param domainPackageName 도메인 클래스의 패키지명
	 * @param columnList 컬럼 리스트
	 * @param pk PK List
	 */
	public void createrMapperXmlFiles(String packageName, String tableName, String domainPackageName, List<ColumnProperty> columnList, List<String> pk, String fileCreatePath, String enCode) {
		makeMapperXmlFileHead(packageName, tableName);
		makeResultMap(tableName, domainPackageName, columnList);
		makeSelect(tableName, columnList);
		makeSelectByPrimaryKey(tableName, columnList, pk);
		makeInsert(tableName, domainPackageName, columnList);
		makeUpdateByPrimaryKey(tableName, domainPackageName, columnList, pk);
		makeDeleteByPrimaryKey(tableName, columnList, pk);
		makeMapperXmlFileTail();
		mapperXmlFileWrite(tableName, fileCreatePath, enCode);
	}
}
