package com.at.et.dao;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.at.et.entities.ExpenseType;

@Transactional
public interface ExpenseTypeDao
{
	public void addExpenseType(ExpenseType expenseType);
	public ExpenseType getExpenseType(String name);
	public List<ExpenseType> getAllExpenseType();
	public void deleteExpenseType(Integer id);
}