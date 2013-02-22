package com.at.et.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.at.et.entities.ExpenseType;

@Repository
public class ExpenseTypeDaoImpl implements ExpenseTypeDao {

	@Autowired
   	protected SessionFactory sessionFactory;
   	
	/**
	 * This dao method saves a new expense type in the database
	 */
	public void addExpenseType(ExpenseType expenseType)
	{
		sessionFactory.getCurrentSession().save(expenseType);
	}
	
	/**
	 * This dao method returns the expense type corresponding to the name that it gets
	 * from the params
	 */
	public ExpenseType getExpenseType(String name)
	{
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from ExpenseType where name= :name").setString("name", name);
		return (ExpenseType) query.list().get(0);
	}
	
	/**
	 * This dao method returns a list of all the expense types that are present
	 * in the database
	 */
	public List<ExpenseType> getAllExpenseType()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from ExpenseType");
		return query.list();
	}
	
	/**
	 * This dao method deletes the expense type corresponding to the 
	 * id that it gets from the params
	 */
	public void deleteExpenseType(Integer id)
	{
		ExpenseType expenseType = (ExpenseType) sessionFactory.getCurrentSession().get(ExpenseType.class, id);

		sessionFactory.getCurrentSession().delete(expenseType);
	}
}