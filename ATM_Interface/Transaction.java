import java.util.*;

public class Transaction {
	private double balance;
	private double amountUsed;
	private String transactionType;
	private Date date;
	
	Transaction(String type,double amtUsed,double bal,Date date)
	{
		setTransactionType(type);
		setAmountUsed(amtUsed);
		setBalance(bal);
		setDate(date);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(double amountUsed) {
		this.amountUsed = amountUsed;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
