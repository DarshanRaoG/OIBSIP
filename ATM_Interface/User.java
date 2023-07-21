
public class User {
    private int uid;
    private int pin;
    private double amount = 0.0;
    
    public User(int uid, int pin) {
        this.setUid(uid);
        this.setPin(pin);
    }

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
