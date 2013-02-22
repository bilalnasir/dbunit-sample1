package com.at.et.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "expense")
public class Expense 
{
	public Expense() {
        super();
    }

    private static final long serialVersionUID = -5527566248002296042L;
    public Expense(Integer id,ExpenseType expenseType,Date date, String status, String title, boolean paid, boolean reimbursable,int total,byte[] image) {
        this.id = id;
        this.title = title;
        this.paid = paid;
        this.total = total;
        this.image = image;
        this.expenseType = expenseType;
        this.date = date;
        this.status = status;
        this.reimbursable = reimbursable;
    }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_id", nullable = false)
	private ExpenseType expenseType;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "reimbursable", columnDefinition="BIT")
	private Boolean reimbursable;
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "paid", columnDefinition="BIT")
	private Boolean paid;
	
	@Column(name = "image")
	private byte[] image;
	
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Boolean getReimbursable()
	{
		return reimbursable;
	}
	
	public void setReimbursable(Boolean reimbursable)
	{
		this.reimbursable = reimbursable;
	}
	
	public Integer getTotal()
	{
		return total;
	}
	
	public void setTotal(Integer total)
	{
		this.total = total;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public Boolean getPaid()
	{
		return paid;
	}
	
	public void setPaid(Boolean paid)
	{
		this.paid = paid;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	public void setImage(byte[] image)
	{
		this.image = image;
	}
	
	
	public ExpenseType getExpenseType() {
		return this.expenseType;
	}
	
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	
	@Override
	public  String toString()
	{
		return "Details of the expense-> id:" + this.id+" title:"+this.title +" Reimbursable: "+this.reimbursable+" total:"+this.total+" date: "+this.date+" status: "+this.status+" paid:"+this.paid +"image: "+this.image;
	}
}
