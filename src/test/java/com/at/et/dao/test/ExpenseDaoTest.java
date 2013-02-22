package com.at.et.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.at.et.dao.ExpenseDao;
import com.at.et.dao.ExpenseTypeDao;
import com.at.et.entities.Expense;
import com.at.et.entities.ExpenseType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ExpenseDaoTest  {

	private static final String FLAT_XML_DATASET = "ExpenseTrackingDataSet.xml";
	private static final String DATE_PATTERNS = "yyyy-MM-dd";
	private static Date date;
	@Autowired
	BasicDataSource bds;
	
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private ExpenseTypeDao expenseTypeDao;

	@Before
	public void setUp() throws Exception 
	{
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}
	
	@After  
	public void after() throws Exception
	{  
		// Delete data from the database  
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());  
	}

	/**
	 * This test method fetches all the expenses from the database
	 */
	@Test
	public void testGetAllExpense() 
	{
		List<Expense> list = expenseDao.getAllExpenses();
		assertFalse(list.isEmpty());
	}
	
	/**
	 * This test method creates a new expensetype, then creates a new expense and save this to the db
	 * 
	 */
	@Test
	public void testSaveExpense() 
	{
		ExpenseType expenseType = new ExpenseType("food expense","food",true);
		expenseTypeDao.addExpenseType(expenseType);
		Expense expense = null;
		try {
			expense = new Expense(1, expenseType,parseDate("2012-12-12"),"approved","food expense", true,true,100,new byte[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expenseDao.addExpense(expense);
		
		Expense expenseRes = expenseDao.getExpense(1);
		assertEquals(expenseRes.getId(), expense.getId());		
	}	

	/**
	 * This method fetches the data from the dataset
	 * @return dataset
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception 
	{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}
	
	/**
	 * This method creates a new connection 
	 * @return connection
	 * @throws Exception
	 */
	private IDatabaseConnection getConnection() throws Exception 
	{
		Connection jdbcConnection = bds.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
	
	/**
	 * This method converts String date to Date date
	 * @param expenseDate
	 * @return date
	 * @throws ParseException
	 */
	private static Date parseDate(String expenseDate) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERNS);
		date = sdf.parse(expenseDate);
		return date;
	}
}