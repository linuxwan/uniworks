/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.linuxwan.devtools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Park Chungwan
 *
 */
public class MyBatisDomainMapperServiceCreater {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisDomainMapperServiceCreater.class);
	private static String SCHEMA = "gw";
	private static String DOMAIN = "org.uniworks.groupware.domain";
	private static String MAPPER = "org.uniworks.groupware.mapper";
	private static String SERVICE = "org.uniworks.groupware.service";
	private static String SERVICE_INTERNAL = "org.uniworks.groupware.service.internal";
	private static String DOMAIN_PATH = "d:/temp/domain";
	private static String MAPPER_PATH = "d:/temp/mapper";
	private static String SERVICE_PATH = "d:/temp/service";
	private static String SERVICE_INTERNAL_PATH = "d:/temp/service/internal";
	Connection jdbcConnection = null;
	
	/**
	 * JDBC Connection 연결
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public Connection getConnection() {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //Oracle
			Class.forName("com.mysql.jdbc.Driver");
			if (jdbcConnection == null) {				 
				jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/gw?useUnicode=true&characterEncoding=utf-8", "gw", "sksahffk");
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException cnfex) {
			System.out.println("ClassNotFoundException: " + cnfex.getMessage());
		}
		
		return jdbcConnection;
	}
	
	/**
	 * 입력 받은 스키마 내의 있는 테이블 명칭을 가져와서 반환한다.
	 * @param schemaName 스키마명
	 * @return
	 */
	public List<String> getTableList(String schemaName) {
		List<String> tableNames = new ArrayList<String>();
		
		DatabaseMetaData m;
		ResultSet tables;
		try {
			m = jdbcConnection.getMetaData();
			tables = m.getTables(null, schemaName, null, new String[] {"TABLE"});			
			
			while (tables.next()) {
				//System.out.println("tables = " + tables.getString("TABLE_NAME") + ", table schem = " + tables.getString("TABLE_SCHEM"));
				tableNames.add(tables.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return tableNames;
	}
	
	/**
	 * 입력받은 테이블명에 포함된 컬럼명칭을 가져와서 반환한다.
	 * @param tableName 테이블명
	 * @return
	 */
	public List<ColumnProperty> getTableColumnList(String tableName) {
		List<ColumnProperty> columnList = new ArrayList<ColumnProperty>();
		
		DatabaseMetaData m;
		ResultSet columns;
		try {
			m = jdbcConnection.getMetaData();
			columns = m.getColumns(null, null, tableName, null);
			
			while (columns.next()) {
				//System.out.println(columns.getString("COLUMN_NAME") + ", " + columns.getString("TYPE_NAME") + ", " + columns.getInt("ORDINAL_POSITION"));
				ColumnProperty colProperty = new ColumnProperty();
				colProperty.setTableSchem(columns.getString("TABLE_SCHEM"));
				colProperty.setTableName(columns.getString("TABLE_NAME"));
				colProperty.setColumnName(columns.getString("COLUMN_NAME"));
				colProperty.setTypeName(columns.getString("TYPE_NAME"));
				colProperty.setColumnSize(columns.getInt("COLUMN_SIZE"));
				colProperty.setOrdinalPosition(columns.getInt("ORDINAL_POSITION"));
				colProperty.setNullable(columns.getInt("NULLABLE"));
				
				columnList.add(colProperty);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return columnList;
	}	
	
	/**
	 * Table의 PK Columne을 가져온다.
	 * @param tableName
	 * @return
	 */
	private List<String> getPKColumnName(String tableName) {
		List<String> pkNameList = new ArrayList<String>();
		DatabaseMetaData dm;
		try {
			dm = jdbcConnection.getMetaData();
			ResultSet rs = dm.getPrimaryKeys(null, null, tableName);

			int i = 0;
			while( rs.next( ) ) 
			{    
			  String pk = rs.getString("COLUMN_NAME");
			  pkNameList.add(i, pk);
			  i++;
			  //logger.debug(tableName + ":" + pk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return pkNameList;
	}
	
	/**
	 * Domain Class 생성.
	 */
	public static void createDomainClasses() {	
		MyBatisDomainMapperServiceCreater dcm = new MyBatisDomainMapperServiceCreater();
		dcm.getConnection();
		List<String> tableList = dcm.getTableList(SCHEMA);
		
		for (int i = 0; i < tableList.size(); i++) {
			String tableName = tableList.get(i);
			List<ColumnProperty> columnNameList = dcm.getTableColumnList(tableName);
			
			DomainClassCreate domainCreater = new DomainClassCreate();
			domainCreater.createrDomainClasses("Park Chungwan", DOMAIN, tableName, columnNameList, DOMAIN_PATH, "utf-8");			
		}
	}
	
	/**
	 * Mapper Xml File 생성.
	 */
	public static void createMapperXmlFiles() {
		MyBatisDomainMapperServiceCreater dcm = new MyBatisDomainMapperServiceCreater();
		dcm.getConnection();
		List<String> tableList = dcm.getTableList(SCHEMA);
		
		for (int i = 0; i < tableList.size(); i++) {
			String tableName = tableList.get(i);
			//if (!tableName.equals("nw100m")) continue;
			List<ColumnProperty> columnNameList = dcm.getTableColumnList(tableName);
			List<String> pk = dcm.getPKColumnName(tableName);
			
			MapperXmlFileCreate mapperXmlCreater = new MapperXmlFileCreate();
			mapperXmlCreater.createrMapperXmlFiles(MAPPER, tableName, DOMAIN, columnNameList, pk, MAPPER_PATH, "utf-8");
		}
	}
	
	/**
	 * Mapper Class 생성.
	 */
	public static void createMapperClassFiles() {
		MyBatisDomainMapperServiceCreater dcm = new MyBatisDomainMapperServiceCreater();
		dcm.getConnection();
		List<String> tableList = dcm.getTableList(SCHEMA);
		
		for (int i = 0; i < tableList.size(); i++) {
			String tableName = tableList.get(i);
			//if (!tableName.equals("nw100m")) continue;
			
			MapperClassCreate mapperClassCreater = new MapperClassCreate();
			mapperClassCreater.createrMapperClassFiles("Park Chungwan", tableName, MAPPER, DOMAIN, MAPPER_PATH, "utf-8");
		}		
	}
	
	/**
	 * Service Interface 생성.
	 */
	public static void createServiceInterfaceFiles() {		
		MyBatisDomainMapperServiceCreater dcm = new MyBatisDomainMapperServiceCreater();
		dcm.getConnection();
		List<String> tableList = dcm.getTableList(SCHEMA);
		
		for (int i = 0; i < tableList.size(); i++) {
			String tableName = tableList.get(i);
			ServiceInterfaceCreate crtServiceInf = new ServiceInterfaceCreate();
			crtServiceInf.createrServiceInterface(SERVICE, DOMAIN, tableName, SERVICE_PATH, "utf-8");
		}
	}
	
	/**
	 * Service Interface Impl 생성.
	 */
	public static void createServiceInterfaceImplFiles() {		
		MyBatisDomainMapperServiceCreater dcm = new MyBatisDomainMapperServiceCreater();
		dcm.getConnection();
		List<String> tableList = dcm.getTableList(SCHEMA);
		
		for (int i = 0; i < tableList.size(); i++) {
			String tableName = tableList.get(i);
			ServiceInterfaceImplClassCreate crtServiceInfInplClass = new ServiceInterfaceImplClassCreate();
			crtServiceInfInplClass.createrServiceInterface(SERVICE_INTERNAL, DOMAIN, MAPPER, SERVICE, tableName, SERVICE_INTERNAL_PATH, "utf-8");
		}
	}
	
	public static void main(String[] args) {
		createDomainClasses();	//도메인 클래스 생성.
		createMapperXmlFiles();	//Mapper xml 파일 생성.
		createMapperClassFiles();	//Mapper Class 생성.
		createServiceInterfaceFiles();	//Service Interface 생성.
		createServiceInterfaceImplFiles();	//Service Interface Impl Class 생성.		
	}
}
