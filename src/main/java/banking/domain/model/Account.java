package banking.domain.model;

/**
 * The {@link Account} domain object
 */
public class Account {

	private final long accountNr;
	
	private int balance;

	public Account(long id, int initialBalance) {
		super();
		this.accountNr = id;
		this.balance = initialBalance;
	}

	public int getAmount() {
		return balance;
	}

	public void withDraw(int amount) {
		balance = balance - amount;
	}
	
	public void add(int amount) {
		balance = balance + amount;
	}

	public long getAccountNr() {
		return accountNr;
	}
}
