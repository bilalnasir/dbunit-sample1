package com.at.et.dao;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.at.et.entities.Expense;
@Transactional
public interface ExpenseDao 
{
	public void addExpense(Expense expense);
	public List<Expense> getAllExpenses();
	public void deleteExpense(Integer id);
	public Expense getExpense(Integer id);
}
