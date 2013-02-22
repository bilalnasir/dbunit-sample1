package com.at.et.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.at.et.entities.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
   	protected SessionFactory sessionFactory;
   	
	/**
	 * This dao method saves the expense into the database
	 */
	public void addExpense(Expense expense)
	{
		sessionFactory.getCurrentSession().save(expense);
	}
	/**
	 * This dao method returns a list of all the expenses that are present in the 
	 * database
	 */
	public List<Expense> getAllExpenses()
	{
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from Expense");
		return query.list();
	}
	
	/**
	 * This dao method returns the expense corresponding to the id that it gets from the
	 * params
	 */
	public Expense getExpense(Integer id)
	
	{
		Expense expense = (Expense) sessionFactory.getCurrentSession().get(Expense.class, id);
		return expense;
	}
	
	/**
	 * This dao method deletes the existing expense corresponding to the id that
	 * it gets from the params
	 */
	public void deleteExpense(Integer id)
	{
		Expense expense = (Expense) sessionFactory.getCurrentSession().get(Expense.class, id);

		sessionFactory.getCurrentSession().delete(expense);
	}
}