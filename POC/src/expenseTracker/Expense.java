package expenseTracker;

import java.util.Date;

public class Expense {

    private int id;
    private Date date;
    private String description;
    private String category;
    private double amount;
    
    public Expense()
    {
    	
    }
    
	public Expense(int id, Date date, String description, String category, double amount) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.category = category;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", date=" + date + ", description=" + description + ", category=" + category
				+ ", amount=" + amount + "]";
	}
    
}
