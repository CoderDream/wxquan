package com.coderdream.dao;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.coderdream.bean.Logging;
import com.coderdream.util.DBUtil;
import com.coderdream.util.EntitiesHelper;

/**
 * <pre>
 * DBUnit使用步骤
 *  1)下载地址为http://sourceforge.net/projects/dbunit/files/ 
 *  2)导入DBUnit所需两个jar文件(dbunit.jar和slf4j-api.jar)
 *  3)创建DBUnit用到的xml格式的测试数据,xml文件名建议与表名相同 
 *  4)创建DBUnit的Connection和DataSet,然后开始进行各项测试工作
 * 
 * 使用注解@BeforeClass，在globalInit()执行打开数据库操作；
 * 使用注解@AfterClass，在globalDestroy()执行数据库关闭操作；
 * 
 * 使用注解@Before，每次测试执行之前都先执行init()操作；
 * 使用注解@After，每次测试执行之后都会执行destroy()操作；
 * 
 * DBUtil提供数据库操作方法。
 * </pre>
 * 
 * @author CoderDream
 * @date 2014年10月15日
 * 
 */
public class LoggingDaoTest {

	public static String TAG = "LoggingDaoTest";
	private static final Logger logger = LoggerFactory.getLogger(LoggingDaoTest.class);
	private static Connection conn;
	private static IDatabaseConnection dbUnitConn;
	private static String DATA_BACKUP_FILE = "dataBackup_logging.xml";

	private static String LOGGING_DATA_FILE = "logging.xml";

	@BeforeClass
	public static void globalInit() throws IOException {
		conn = DBUtil.getConnection();
		System.out.println("DB-Unit Get Connection: " + conn);
		try {
			// DBUnit中用来操作数据文件的Connection需依赖于数据库连接的Connection
			dbUnitConn = new DatabaseConnection(conn);
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void globalDestroy() {
		DBUtil.close(conn);
		if (null != dbUnitConn) {
			try {
				dbUnitConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 备份数据库中所有表的数据，同时将logging.xml的数据插入到数据库中
	 */
	// @Before
	// public void initAll() throws Exception {
	// logger.debug("Before #### initAll");
	// // 此时所创建的DataSet包含了数据库中所有表的数据
	// IDataSet backupDataSet = dbUnitConn.createDataSet();
	// // 备份数据库中所有表的数据
	// FlatXmlDataSet.write(backupDataSet, new FileWriter(DATA_BACKUP_FILE));
	//
	// // FlatXmlDataSet用来获取基于属性存储的属性值,XmlDataSet用来获取基于节点类型存储的属性值
	// IDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(LoggingDaoTest4.class.getClassLoader()
	// .getResourceAsStream("logging.xml"))));
	// DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);
	// }

	/**
	 * 备份数据库中某一张或某几张表的数据，同时将xml文件中的数据插入到数据库中
	 */
	@Before
	public void init() throws Exception {
		logger.debug("Before #### init");
		// 通过QueryDataSet可以有效的选择要处理的表来作为DataSet
		QueryDataSet dataSet = new QueryDataSet(dbUnitConn);
		// 这里指定只备份t_logging表中的数据,如果想备份多个表,那就再addTable(tableName)即可
		dataSet.addTable("logging");
		FlatXmlDataSet.write(dataSet, new FileWriter(DATA_BACKUP_FILE));
	}

	/**
	 * 还原表数据
	 */
	@After
	public void destroy() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(
						new InputSource(new FileInputStream(DATA_BACKUP_FILE))));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);
	}

	/**
	 * <pre>
	 * 测试查询方法
	 * 		 DatabaseOperation类的几个常量值
	 * 		 CLEAN_INSERT----先删除数据库中的所有数据,然后将xml中的数据插入数据库
	 * 		 DELETE----------如果数据库存在与xml记录的相同的数据,则删除数据库中的该条数据
	 * 		 DELETE_ALL------删除数据库中的所有数据
	 * 		 INSERT----------将xml中的数据插入数据库
	 * 		 NONE------------nothing to do
	 * 		 REFRESH---------刷新数据库中的数据
	 * 		 TRUNCATE_TABLE--清空表中的数据
	 * 		 UPDATE----------将数据库中的那条数据更新为xml中的数据
	 * </pre>
	 */
	@Test
	public void testFindLogging() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(LoggingDaoTest.class.getClassLoader()
						.getResourceAsStream(LOGGING_DATA_FILE))));
		DatabaseOperation.TRUNCATE_TABLE.execute(dbUnitConn, dataSet);
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);

		// 下面开始数据测试
		LoggingDao loggingDao = new LoggingDao();
		Logging logging = loggingDao.findLogging(1);
		
		// 预想结果和实际结果的比较
		EntitiesHelper.assertLogging(logging);
	}

	/**
	 * 更新，添加，删除等方法，可以利用Assertion.assertEquals() 方法，拿表的整体来比较。
	 */
	@Test
	public void testAddLogging() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(LoggingDaoTest.class.getClassLoader()
						.getResourceAsStream(LOGGING_DATA_FILE))));
		DatabaseOperation.TRUNCATE_TABLE.execute(dbUnitConn, dataSet);
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String time = df.format(new Date(1413993830024l));
		// Timestamp ts = Timestamp.valueOf(time);
		String logStr = "FirstLogging";
		// 被追加的记录
		Logging newLogging = new Logging(time, "DEBUG", TAG, logStr);

		// 执行追加 addLogging 方法
		LoggingDao loggingDao = new LoggingDao();
		int result = loggingDao.addLogging(newLogging);
		Assert.assertEquals(1, result);

		// 预期结果取得
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(LoggingDaoTest.class
						.getClassLoader().getResourceAsStream("logging_add.xml"))));
		ITable expectedTable = expectedDataSet.getTable("logging");

		// 实际结果取得(取此时数据库中的数据)
		// Creates a dataset corresponding to the entire database
		IDataSet databaseDataSet = dbUnitConn.createDataSet();
		ITable actualTable = databaseDataSet.getTable("logging");

		// 预想结果和实际结果的比较
		Assertion.assertEquals(expectedTable, actualTable);
	}

	/**
	 * 更新，添加，删除等方法，可以利用Assertion.assertEquals() 方法，拿表的整体来比较。
	 */
	// @Test
	// public void testUpdateLogging() throws Exception {
	// // 被更新的记录
	// Logging logging = new Logging("0002", "王翠花", "f", "1981-08-09");
	// // 执行追加 addLogging 方法
	// LoggingDao loggingDao = new LoggingDao();
	// int result = loggingDao.updateLogging(logging);
	// Assert.assertEquals(1, result);
	//
	// // 预期结果取得
	// IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new
	// InputSource(LoggingDaoTest4.class.getClassLoader()
	// .getResourceAsStream("logging_update.xml"))));
	// ITable expectedTable = expectedDataSet.getTable("logging");
	//
	// // 实际结果取得(取此时数据库中的数据)
	// // Creates a dataset corresponding to the entire database
	// IDataSet databaseDataSet = dbUnitConn.createDataSet();
	// ITable actualTable = databaseDataSet.getTable("logging");
	//
	// // 预想结果和实际结果的比较
	// Assertion.assertEquals(expectedTable, actualTable);
	// }

	/**
	 * 更新，添加，删除等方法，可以利用Assertion.assertEquals() 方法，拿表的整体来比较。
	 */
	// @Test
	// public void testDeleteLogging() throws Exception {
	// // 被删除记录的id
	// String id = "0001";
	// // 执行删除方法
	// LoggingDao loggingDao = new LoggingDao();
	// int result = loggingDao.deleteLogging(id);
	// Assert.assertEquals(1, result);
	//
	// // 预期结果取得
	// IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new
	// InputSource(LoggingDaoTest4.class.getClassLoader()
	// .getResourceAsStream("logging_delete.xml"))));
	// ITable expectedTable = expectedDataSet.getTable("logging");
	//
	// // 实际结果取得(取此时数据库中的数据)
	// // Creates a dataset corresponding to the entire database
	// IDataSet databaseDataSet = dbUnitConn.createDataSet();
	// ITable actualTable = databaseDataSet.getTable("logging");
	//
	// // 预想结果和实际结果的比较
	// Assertion.assertEquals(expectedTable, actualTable);
	// }
}
